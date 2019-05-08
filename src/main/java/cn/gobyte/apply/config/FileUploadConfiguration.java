package cn.gobyte.apply.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * 文件上传配置
 *
 * @author 单红宇(CSDN CATOOP)
 * @create 2017年3月11日
 */
@Configuration
public class FileUploadConfiguration {
    @Value("${file.uploadFolder}")
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
        System.err.println("路径地址=" + uploadFilePath);
        // 设置路径有毒；如果路径不存在就会报错。如果路径存在则写入到项目根目录了。
//        factory.setLocation(uploadFilePath);
        return factory.createMultipartConfig();
    }
}
