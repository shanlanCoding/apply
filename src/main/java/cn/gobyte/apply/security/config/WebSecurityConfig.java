package cn.gobyte.apply.security.config;

import cn.gobyte.apply.security.handler.MyAuthenticationFailureHandler;
import cn.gobyte.apply.security.handler.MyAuthenticationSucessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * TODO: 安全配置类；这里面配置了各个路径的访问权限。
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/3/31 23:58
 */
@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSucessHandler authenticationSucessHandler;
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

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
     * TODO: 安全访问配置；Spring Security
     *
     * @param http
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/4/5 1:18
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*http
                .authorizeRequests()
                .antMatchers("/", "/favicon.ico", "/index", "/css/**", "/js/**", "/img/**", "/static/**").permitAll()//主页、静态资源放行,"/favicon.ico"
                .anyRequest().authenticated()//任何请求都需要认证
                .and()

                .formLogin()// 基于From表单验证
                .loginPage("/index")// 登录页面，未认证的时候跳转到该页面
                .loginProcessingUrl("/login") // 处理表单登录 URL
                .failureUrl("/login-error")//自定义登录页面和登陆失败需要跳转的页面
                .defaultSuccessUrl("/home")//登录成功后默认跳转到
                .and()

                .logout().permitAll() //注销行为任意访问
                .logoutSuccessUrl("/index")//退出登录后的默认url是"/login"
                .permitAll()
                .and()

                .csrf().disable(); //关闭csrf防御机制;*/
        http
                .formLogin() // 表单登录
//                 http.httpBasic() // HTTP Basic
                .loginPage("/index") // 跳转到登录页面的请求URL,注意！这只是url，最终该url打开什么还需要在control层里设置
                .loginProcessingUrl("/login") // 对应登录页面form表单的action="/login"
                .successHandler(authenticationSucessHandler) // 处理登录成功
                .failureHandler(authenticationFailureHandler)// 处理登录失败
                .and()
                .authorizeRequests() // 授权配置
//                .antMatchers("/", "/favicon.ico", "/index", "/css/**", "/js/**", "/img/**", "/static/**").permitAll() // 登录跳转 URL 无需认证
                .antMatchers("/", "/signin", "/index", "/css/**", "/js/**", "/img/**", "/register","favicon.ico").permitAll() // 登录跳转 URL 无需认证
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and().csrf().disable();//解决非thymeleaf的form表单提交被拦截问题

//        http.csrf().disable();

        //解决中文乱码问题
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//        http.addFilterBefore(filter, CsrfFilter.class);

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
                        .roles("user")
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
/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // withDefaultPasswordEncoder被弃用，用以下方式
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
//                .withUser(oldUser.withDefaultPasswordEncoder().username("admin")
                .withUser("MenuMapper")
                .password(encoder.encode("MenuMapper")).roles("user");
    }*/
}