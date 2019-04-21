package cn.gobyte.apply.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
TODO: 权限页面控制器；该类负责主页、登录页面跳转的
* @author shanLan misterchou@qq.com
* @date 2019/4/1 0:07
*/
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("homes");
        registry.addViewController("/").setViewName("indexs");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("indexs");
    }

}