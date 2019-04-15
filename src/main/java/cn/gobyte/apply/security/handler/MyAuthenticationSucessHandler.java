package cn.gobyte.apply.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功处理类
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/14 22:51
 */
@Component
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper mapper;

    // Spring Security提供的用于缓存请求的对象，通过调用它的getRequest方法可以获取到本次请求的HTTP信息。
    private RequestCache requestCache = new HttpSessionRequestCache();
    // Spring Security提供的用于处理重定向的方法
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * TODO: 自定义默认的处理登陆成功的逻辑
     *
     * @param request
     * @param response
     * @param authentication 包含了认证请求的一些信息，比如IP，请求的SessionId等，也包含了用户信息，即前面提到的User对象。
     *                       通过上面这个配置，用户登录成功后页面将打印出Authentication对象的信息。
     * @return void:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/15 19:53
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        // 请求哪个页面登陆成功后，再打开该页面
//        redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        // 不管请求哪个页面，登陆成功后仅打开指定页面index
        redirectStrategy.sendRedirect(request, response, "/home");
        System.err.println("登陆成功" + this.getClass().getName());
    }
}
