package cn.gobyte.apply.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: 日志类，在需要使用日志记录的时候，直接写@log即可
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/4 21:08
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";
}
