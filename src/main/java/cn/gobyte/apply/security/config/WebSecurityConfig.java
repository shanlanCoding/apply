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

        http
                .formLogin() // 表单登录
//                 http.httpBasic() // HTTP Basic
                .loginPage("/login") // 跳转到登录页面的请求URL,注意！这只是url，最终该url打开什么还需要在control层里设置
                .loginProcessingUrl("/login") // 对应登录页面form表单的action="/login"
                .successHandler(authenticationSucessHandler) // 处理登录成功
                .failureHandler(authenticationFailureHandler)// 处理登录失败
                .and()
                .authorizeRequests() // 授权配置
//                .antMatchers("/", "/favicon.ico", "/index", "/css/**", "/js/**", "/img/**", "/static/**").permitAll() // 登录跳转 URL 无需认证
                .antMatchers("/static/**", "/css/**", "/js/**", "/img/**", "/favicon.ico",
                        "/", "/login", "/register","/user/selectGrande","/user/retrievePassword").permitAll() // 登录跳转 URL 无需认证
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and().csrf().disable();//解决非thymeleaf的form表单提交被拦截问题
    }

}