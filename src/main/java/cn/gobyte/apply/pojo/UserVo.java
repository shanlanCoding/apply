package cn.gobyte.apply.pojo;

import lombok.*;

/**
 * 登陆请求参数校验实体；为登陆的参数进行校验的类
 *
 * @Description:
 * @Author:libin
 * @Date: Created in 15:34 2017/11/13
 */

//使用插件进行set、get等配置
@Data
public class UserVo {

    //用户名
    private String id;

    //密码，非空，6-18位
    private String password;

    //验证码；captcha
    private String captcha;
}