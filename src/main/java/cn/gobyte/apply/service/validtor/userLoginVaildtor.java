package cn.gobyte.apply.service.validtor;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 登陆请求参数校验实体；为登陆的参数进行校验的类
 * @Description:
 * @Author:libin
 * @Date: Created in 15:34 2017/11/13
 */
public class userLoginVaildtor {

    @NotEmpty(message="用户名不能为空！")
    private String username;

    //身份证号，不能为空，18位
    @NotEmpty(message = "身份证号不能为空")
    @Pattern(regexp = "^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message = "身份证格式错误")
    private String id;

    //密码，非空，6-18位
    @NotEmpty(message = "密码不能为空")
    @Length(min = 1, max = 18, message = "密码长度必须位于6到18之间")
    private String password;

    @Override
    public String toString() {
        return "userLoginVaildtor{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public userLoginVaildtor() {

    }

    public userLoginVaildtor(@NotEmpty(message = "用户名不能为空！") String username, @NotEmpty(message = "身份证号不能为空") @Pattern(regexp = "^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message = "身份证格式错误") String id, @NotEmpty(message = "密码不能为空") @Length(min = 1, max = 18, message = "密码长度必须位于6到18之间") String password) {
        this.username = username;
        this.id = id;
        this.password = password;
    }
}