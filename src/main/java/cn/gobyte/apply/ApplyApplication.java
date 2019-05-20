package cn.gobyte.apply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"cn.gobyte.apply.controller", "cn.gobyte.apply.service",
        "cn.gobyte.apply.security","cn.gobyte.apply.config"})

@MapperScan(basePackages = {"cn.gobyte.apply.dao.page", "cn.gobyte.apply.dao.user"})

//@ComponentScan(basePackages = {"cn.gobyte.apply.controller", "cn.gobyte.apply.service.user", "cn.gobyte.apply.service.page",
//        "cn.gobyte.apply.security.*"})

public class ApplyApplication {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplyApplication.class, args);
    }

}


