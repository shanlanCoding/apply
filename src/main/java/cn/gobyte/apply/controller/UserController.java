package cn.gobyte.apply.controller;

import cn.gobyte.apply.annotation.Log;
import cn.gobyte.apply.domain.QueryRequest;
import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * TODO: 管理员用户类，该类为管理员提供用户管理
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/5/2 21:15
 */
@Controller
public class UserController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * TODO: 获取用户的信息
     *
     * @param model
     * @return java.lang.String:
     * @author shanLan misterchou@qq.com
     * @date 2019/5/2 21:16
     */

    @RequestMapping("/user")
    @PreAuthorize("hasAuthority('user:list')")
    public String index(Model model) {
        return "system/user/user";
    }

    /**
     * TODO: 获取用户列表
     *
     * @param request
     * @param user
     * @return java.util.Map<java.lang.String, java.lang.Object>:
     * @author shanLan misterchou@qq.com
     * @date 2019/5/2 22:03
     */
    @Log("获取用户列表")
    @RequestMapping("user/list")
    @PreAuthorize("hasAuthority('user:list')")
    @ResponseBody
    public Map<String, Object> userList(QueryRequest request, User user) {
//        System.err.println(request);
        // 调用父类方法，根据页面数字大小查询
        return super.selectByPageNumSize(request, () -> this.userService.findUserByUsernameOrIdNumber(user));
    }

    /**
     * TODO: 获取用户信息
     *
     * @param userId
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/5/5 0:35
     */
    /**
     * TODO: 获取用户信息
     *
     * @param
     * @return java.lang.String:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/25 1:29
     */
    @RequestMapping("/user/getUser")
    @ResponseBody
    public ResponseBo getUser(String id) {
        try {
//            System.err.println("访问了" + "----" + this.getClass().getName());
            User user = this.userService.findById(id);
//            if (user != null) {
//            }
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return ResponseBo.error("获取用户失败，请联系网站管理员！");
        }
    }
}
