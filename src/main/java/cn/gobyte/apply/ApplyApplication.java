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


    /**
     * TODO: 配置文件上传路径
     */
    /*@Value("${file.uploadFolder}")
    private String uploadFilePath;

    @Bean
    public MultipartConfigElement multipartConfigElement() {

        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制 ,超出设置页面会抛出异常信息，
        // 这样在文件上传的地方就需要进行异常信息的处理了;

        // ,10MB
        factory.setMaxFileSize(DataSize.ofMegabytes(10));

        /// 设置总上传数据总大小,10MB
        factory.setMaxRequestSize(DataSize.ofMegabytes(10));

        // Sets the directory location where files will be stored.
        System.err.println("路径地址Mian里面;路径="+uploadFilePath);
        factory.setLocation(uploadFilePath);
        return factory.createMultipartConfig();
    }*/

}


