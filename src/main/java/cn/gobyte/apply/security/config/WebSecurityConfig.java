package cn.gobyte.apply.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/*
TODO: 安全配置类；这里面配置了各个路径的访问权限。
* @author shanLan misterchou@qq.com
* @date 2019/3/31 23:58
*/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * TODO: 密码加密储存
     *
     * @return org.springframework.security.crypto.password.PasswordEncoder
     * @author shanLan misterchou@qq.com
     * @date 2019/4/5 1:14
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
    TODO: 安全访问配置；Spring Security
    * @param http
    * @return void
    * @author shanLan misterchou@qq.com
    * @date 2019/4/5 1:18
    */@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/**", "/js/**", "/img/**").permitAll()//主页、静态资源放行
                .anyRequest().authenticated()//任何请求都需要认证
                .and()

                .formLogin()// 基于From表单验证
                .loginPage("/index").failureUrl("/login-error")//自定义登录页面和登陆失败需要跳转的页面
                .defaultSuccessUrl("/home")//登录成功后默认跳转到
                .and()

                .logout().permitAll() //注销行为任意访问
                .logoutSuccessUrl("/index")//退出登录后的默认url是"/login"
                .permitAll()
                .and()

                .csrf().disable(); //关闭csrf防御机制;

        //解决非thymeleaf的form表单提交被拦截问题
        http.csrf().disable();

        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

/*
       http.formLogin()// 表单登录  来身份认证
                .loginPage("/myLogin.html")// 自定义登录页面
                .loginProcessingUrl("/authentication/form")// 自定义登录路径
                .and()
                .authorizeRequests()// 对请求授权
                // error  127.0.0.1 将您重定向的次数过多
                .antMatchers("/myLogin.html", "/authentication/require",
                        "/authentication/form").permitAll()// 这些页面不需要身份认证,其他请求需要认证
                .anyRequest() // 任何请求
                .authenticated()//; // 都需要身份认证
                .and()
                .csrf().disable();// 禁用跨站攻击
*/
    }

/*    @Bean
    @Override
    // withDefaultPasswordEncoder 弃用了，原因是不安全
    public UserDetailsService userDetailsService() {
        UserDetails user =
                oldUser.withDefaultPasswordEncoder()
                        .username("MenuMapper")
                        .password("MenuMapper")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/

    /**
     * 在内存中配置一个用户，admin/admin分别是用户名和密码，这个用户拥有USER角色。
     * withDefaultPasswordEncoder 被遗弃，原因是不安全，只能在例子中使用
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // withDefaultPasswordEncoder被弃用，用以下方式
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
//                .withUser(oldUser.withDefaultPasswordEncoder().username("admin")
                .withUser("MenuMapper")
                .password(encoder.encode("MenuMapper")).roles("USER");
    }
}