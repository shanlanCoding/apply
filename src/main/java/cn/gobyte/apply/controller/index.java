package cn.gobyte.apply.controller;

import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.Course;
import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.security.pojo.myUserDetails;
import cn.gobyte.apply.service.user.CourseService;
import cn.gobyte.apply.service.user.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主页，用户的所有操作都是从这里开始
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/10 1:56
 */

@Controller
public class index extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    //注入业务实现类，所有的业务方法都是调用该类
    @Autowired
    private UserServiceImpl us;

    // 报考科目
    @Autowired
    private CourseService cs;

    // 密码操作
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * TODO:默认访问首页
     *
     * @return java.lang.String
     * @author shanLan misterchou@qq.com
     * @date 2019/3/29 22:44
     */
    @GetMapping("/")
    public String toRootIndex() {
        return "login";
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
        return "login";
    }

    /**
     * TODO:登陆成功后页面
     *
     * @return java.lang.String
     * @author shanLan misterchou@qq.com
     * @date 2019/3/27 23:33
     */
    @GetMapping("/index")
    public String toHome(Authentication authentication, Model md) {
        Object principal = authentication.getPrincipal();
        // 从principal获取的信息，类型为MyDetails
        md.addAttribute("user", principal);
        myUserDetails myUser = (myUserDetails) principal;
        /**
         * 把从数据库得到的user，传递给页面。d = data ,数据库的意思。duser可以理解为数据库的user
         */
        User user = us.findByEmailOrIdNumber(myUser.getId());
        md.addAttribute("duser", user);
        System.err.println(user.toString() + "----" + this.getClass().getName());
        return "index";
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
                // 开始调用注册方法
                return this.us.register(user);
            }
        } catch (Exception e) {
            log.error("注册失败", e);
            return ResponseBo.error("注册失败，请联系网站管理员！");
        }

        //forward 内部跳转,redirect
        //return "redirect:index";
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
        return "hello";
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

    /**
     * TODO: 打印申请表页面
     *
     * @param
     * @return java.lang.String:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/23 2:12
     */
    @GetMapping("/printApplicationForm")
    public String printApplicationForm(Authentication authentication, Model md) {
        Object principal = authentication.getPrincipal();
        myUserDetails myUserDetails = (myUserDetails) principal;
        // 根据myUserDetails获取登陆信息，例如身份证号或者email，接着可以使用这些信息去数据库查询用户
        User user = this.us.findByEmailOrIdNumber(myUserDetails.getId());

        // 格式化一下生日
        String year = user.getId().substring(6, 10);
        String month = user.getId().substring(10, 12);
        String day = user.getId().substring(12, 14);
        String birthday = year + "/" + month + "/" + day;

        user.setBirthd(birthday);
        md.addAttribute("user", user);

        System.err.println(user.toString() + "----" + this.getClass().getName() + "\r\n");
        return "sqb";
    }

    /**
     * TODO: 准考证
     *
     * @param
     * @return java.lang.String:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/23 9:43
     */
    @GetMapping("/zkz")
    public String zkz(Authentication authentication, Model md) {
        try {
            Object principal = authentication.getPrincipal();
            myUserDetails myUserDetails = (myUserDetails) principal;
            // 根据myUserDetails获取登陆信息，例如身份证号或者email，接着可以使用这些信息去数据库查询用户
            User user = this.us.findByEmailOrIdNumber(myUserDetails.getId());
            md.addAttribute("user", user);
            // 报考科目
            Course course = cs.selectExamCourseByMajor(user.getBkmajor());
            if (course != null) {
                md.addAttribute("course", course);
            }
//        System.err.println(user.toString() + "----" + this.getClass().getName() + "\r\n");
//        System.err.println(course.toString() + "----" + this.getClass().getName() + "\r\n");
        } catch (Exception e) {
            log.error("渲染准考证失败", e);
        }
        return "zkz";
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
            System.err.println("访问了" + "----" + this.getClass().getName());
            User user = this.us.findById(id);
//            if (user != null) {
//            }
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return ResponseBo.error("获取用户失败，请联系网站管理员！");
        }
    }

    /**
     * TODO:修改用户信息
     *
     * @param user
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/25 20:11
     */
    @RequestMapping("/user/edit")
    @ResponseBody
    public ResponseBo editUser(User user) {
        try {
            return this.us.updateUser(user);
        } catch (Exception e) {
            log.error("修改用户失败", e);
            return ResponseBo.error("修改出错，请联系网站管理员！");
        }
    }

    /**
     * TODO: 检查密码是否正确
     *
     * @param password
     * @return boolean:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/26 1:14
     */
    @RequestMapping("user/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        User currentUser = super.getCurrentUser();
        return this.passwordEncoder.matches(password, currentUser.getPassword());
    }

    /**
     * TODO: 修改用户密码
     *
     * @param password  旧密码
     * @param password1 新密码1
     * @param password2 新密码2
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/26 0:35
     */
    @RequestMapping("/user/updatePassword")
    @ResponseBody
    public ResponseBo updatePassword(String password, String password1, String password2) {
        try {
            // 获取当前用户
            User user = super.getCurrentUser();

            // 判断两次密码是否相同

            if (!password1.equals(password2)) {
                return ResponseBo.error("两次密码不相同");
            }

            // 判断旧密码是否正确
            if (this.passwordEncoder.matches(password, user.getPassword())) {
                return this.us.updatePassword(password1, user.getId());
            } else {
                return ResponseBo.error("旧密码不正确");
            }

        } catch (Exception e) {
            log.error("修改用户失败", e);
            return ResponseBo.error("修改出错，请联系网站管理员！");
        }
    }











    /*
    代码结束
     */
}
