package cn.gobyte.apply;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan(basePackages = "cn.gobyte.apply.dao")
@ComponentScan(basePackages = {"cn.gobyte.apply.controller", "cn.gobyte.apply.service", "cn.gobyte.apply.security.config"})

public class ApplyApplication {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplyApplication.class, args);
    }

}


