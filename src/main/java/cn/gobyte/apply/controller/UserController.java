package cn.gobyte.apply.controller;

import cn.gobyte.apply.annotation.Log;
import cn.gobyte.apply.domain.QueryRequest;
import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.service.user.UserService;
import cn.gobyte.apply.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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
//        System.err.println("userList====" + user);
        // 调用父类方法，根据页面数字大小查询
        return super.selectByPageNumSize(request, () -> this.userService.findUserByUsernameOrIdNumber(user));
    }

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
            User user = this.userService.findById(id);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return ResponseBo.error("获取用户失败，请联系网站管理员！");
        }
    }

    @Log("删除用户")
    @PreAuthorize("hasAuthority('user:delete')")
    @RequestMapping("/user/delete")
    @ResponseBody
    public ResponseBo deleteUsers(String ids) {
        try {
            this.userService.deleteUsers(ids);
            return ResponseBo.ok("删除用户成功！");
        } catch (Exception e) {
            log.error("删除用户失败", e);
            return ResponseBo.error("删除用户失败，请联系网站管理员！");
        }
    }

    /**
     * TODO: 导出表格，下载xlsx表格
     *
     * @param user
     * @return cc.mrbird.common.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/5/5 16:44
     */
    @RequestMapping("/user/excel")
    @ResponseBody
    public ResponseBo userExcel(User user) {
        try {
            List<User> list = this.userService.findUserByUsernameOrIdNumber(user);
//            return FileUtils.createExcelByPOIKit("用户表", list, MyUser.class);
        } catch (Exception e) {
            log.error("导出用户信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
        return null;
    }

    /**
     * TODO: 导出表格，下载csv表格
     *
     * @param user
     * @return cc.mrbird.common.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/5/5 16:43
     */
    @RequestMapping("/user/csv")
    @ResponseBody
    public ResponseBo userCsv(User user) {
        try {
            List<User> list = this.userService.findUserByUsernameOrIdNumber(user);
            return FileUtils.createCsv("用户表", list,  User.class);
        } catch (Exception e) {
            log.error("导出用户信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
//        return null;
    }
}
