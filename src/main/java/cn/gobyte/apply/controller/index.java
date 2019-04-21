package cn.gobyte.apply.controller;

import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.service.user.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主页，用户页面的所有操作都是从这里开始的
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/10 1:56
 */

@Controller
public class index {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    //注入业务实现类，所有的业务方法都是调用该类
    @Autowired
    private UserServiceImpl us;

    /**
     * TODO:默认访问首页
     *
     * @return java.lang.String
     * @author shanLan misterchou@qq.com
     * @date 2019/3/29 22:44
     */
    @GetMapping("/")
    public String toRootIndex() {
        return "indexs";
    }

    /**
     * TODO: 登录页面
     *
     * @return java.lang.String:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/16 16:10
     */
    @GetMapping("/signin")
    public String toLogin() {
        return "indexs";
    }

    @GetMapping("/index")
    public String toIndex() {
        return "indexs";
    }

    /**
     * TODO:登陆成功后页面
     *
     * @return java.lang.String
     * @author shanLan misterchou@qq.com
     * @date 2019/3/27 23:33
     */
    @GetMapping("/home")
    public String toHome() {
        return "homes";
    }

    /**
     * TODO: 注册学生的账号
     *
     * @param user
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/21 21:41
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseBo registerUser(User user) {
        // 1.注册之前，先判断邮箱、身份证号是否存在于数据库。如果存在则不允许注册。
        try {
            // 通过身份证号进行查找，看看是否存在
            user.setId(user.getId().trim());
            User idUser = this.us.findByEmailOrIdNumber(user.getId());

            // 通过邮箱
            user.setEmail(user.getEmail().trim());
            User emailUser = this.us.findByEmailOrIdNumber(user.getEmail());
            if (idUser != null) {
                // 身份证号已经注册过
                return ResponseBo.warn("该身份证号已经被注册！");
            } else if (emailUser != null) {
                // email号已经注册过
                return ResponseBo.warn("该email已经被注册！");
            } else {
                System.err.println("User：" + user.toString() + "\r\n" + this.getClass().getName());
                us.register(user);
                return ResponseBo.ok();
            }

        } catch (Exception e) {
            log.error("注册失败", e);
            return ResponseBo.error("注册失败，请联系网站管理员！");
        }

        //forward 内部跳转,redirect
//        return "redirect:index";
    }

    /**
     * TODO: 打开测试页面
     *
     * @param user
     * @return java.lang.String:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/17 1:47
     */
    @RequestMapping("/t")
    public String test(User user) {
        return "Test";
    }

    /**
     * TODO: 登陆；由于登陆被Security接管了，所以我们不再需要自定义登陆功能了。
     *
     * @param username 从登陆的from获取的用户名
     * @param password 获取过来的密码
     * @return java.lang.String:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/21 21:16
     */
    @RequestMapping("/login")
    public String login(String username, String password) {
        return "/login";
    }
}
