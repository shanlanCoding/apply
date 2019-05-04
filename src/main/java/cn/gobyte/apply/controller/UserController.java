package cn.gobyte.apply.controller;

import cn.gobyte.apply.domain.QueryRequest;
import cn.gobyte.apply.security.pojo.MyUser;
import cn.gobyte.apply.service.user.UserService;
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
    @Autowired
    private UserService userService;

    /**
     * TODO: 获取用户列表
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
     * TODO: 获取用户的信息
     *
     * @param request
     * @param user
     * @return java.util.Map<java.lang.String, java.lang.Object>:
     * @author shanLan misterchou@qq.com
     * @date 2019/5/2 22:03
     */
    @RequestMapping("user/list")
    @PreAuthorize("hasAuthority('user:list')")
    @ResponseBody
    public Map<String, Object> userList(QueryRequest request, MyUser user) {
        return super.selectByPageNumSize(request, () -> this.userService.findUserWithDept(user));
    }
}
