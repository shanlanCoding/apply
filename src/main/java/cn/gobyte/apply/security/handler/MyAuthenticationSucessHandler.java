package cn.gobyte.apply.security.handler;

import cn.gobyte.apply.dao.user.RoleMapper;
import cn.gobyte.apply.domain.FebsConstant;
import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.Role;
import cn.gobyte.apply.security.pojo.myUserDetails;
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
import java.util.List;

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

    @Autowired
    private RoleMapper RoleMapper;

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
        // 请求哪个页面登陆成功后，再打开该页面
//        redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        // 不管请求哪个页面，登陆成功后仅打开指定页面index
//        redirectStrategy.sendRedirect(request, response, "/index");
//        System.err.println("登陆成功" + this.getClass().getName());

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        response.setContentType(FebsConstant.JSON_UTF8);

        if (savedRequest != null) {
            // 跳转到之前引发跳转的url
            String url = savedRequest.getRedirectUrl();
            String messsage = "成功";
            response.getWriter().write(mapper.writeValueAsString(ResponseBo.ok(messsage, url)));
        } else {
            /*
                 查询用户的角色，若用户为管理员，则跳转到管理员的页面，否则跳转到普通用户的页面
             */

            // 1. 取得当前登录的用户
            myUserDetails user = (myUserDetails) authentication.getPrincipal();

            // 2. 从该对象中取到身份证号 user.id，并返回查询到的角色列表,默认返回第一个
            List<Role> role = this.RoleMapper.findUserRole(user.getId().toString());

            // 3. 判断角色名是否为“管理员”，否则默认为普通用户
            if (!role.isEmpty() && role.get(0).getRoleName().equals("管理员")) {

                String url = "/admin";
                // 4. 跳转到管理员的页面
                response.getWriter().write(mapper.writeValueAsString(ResponseBo.ok("欢迎，登陆成功", url)));
            } else {
                // 5. 否则跳转普通用户的页面
                response.getWriter().write(mapper.writeValueAsString(ResponseBo.ok()));
            }

        }

    }
}
