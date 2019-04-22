package cn.gobyte.apply.security.handler;

import cn.gobyte.apply.domain.FebsConstant;
import cn.gobyte.apply.domain.ResponseBo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: 登陆失败处理器
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/15 20:59
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper mapper;

    /**
     * TODO: 重写Security默认的登陆失败处理方法
     *
     * @param request
     * @param response
     * @param exception 抽象类
     * @return void:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/15 20:59
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        String message;
        if (exception instanceof UsernameNotFoundException) {
            message = "该用户不存在";
        } else if (exception instanceof BadCredentialsException) {
            message = "账户或者密码错误";
        } else if (exception instanceof LockedException) {
            message = "用户已被锁定！";
        } else if (exception instanceof DisabledException) {
            message = "用户不可用！";
        } else if (exception instanceof AccountExpiredException) {
            message = "账户已过期！";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "用户密码已过期！";
        } else {
            message = "认证失败，请联系网站管理员！";
        }
        /*
            验证码异常
        */
//        else if (exception instanceof ValidateCodeException || exception instanceof FebsCredentialExcetion) {
//            message = exception.getMessage();
//        }

        // 服务器状态码
        //response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        // response.getWriter().write(mapper.writeValueAsString("返回的消息：" + exception.getMessage()));

        response.setContentType(FebsConstant.JSON_UTF8);
        response.getWriter().write(mapper.writeValueAsString(ResponseBo.error(message)));
    }
}
