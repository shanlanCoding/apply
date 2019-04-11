package cn.gobyte.apply.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

    /**
     * 配置 sql打印拦截器
     * application.yml中 febs.showsql: true 时生效
     *
     * @return SqlStatementInterceptor
     */
//    @Bean
//    @ConditionalOnProperty(name = "febs.showsql", havingValue = "true")
//    SqlStatementInterceptor sqlStatementInterceptor() {
//        return new SqlStatementInterceptor();
//    }
}
