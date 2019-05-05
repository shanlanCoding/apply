package cn.gobyte.apply.controller;

import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.Course;
import cn.gobyte.apply.pojo.user.Role;
import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.pojo.user.userGrande;
import cn.gobyte.apply.security.pojo.myUserDetails;
import cn.gobyte.apply.service.user.CourseService;
import cn.gobyte.apply.service.user.UserService;
import cn.gobyte.apply.service.user.impl.GrandeServiceImpl;
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

import java.util.List;

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
    private UserService us;

    // 报考科目
    @Autowired
    private CourseService cs;

    // 密码操作
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 查询成绩
    @Autowired
    private GrandeServiceImpl gs;

    // 角色查询
    @Autowired
    private cn.gobyte.apply.dao.user.RoleMapper RoleMapper;

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

    @GetMapping("/admin")
    public String toAdmin(Authentication authentication, Model md) {
        // 1. 获取当前登陆的用户
        myUserDetails myUserDetails = (myUserDetails) authentication.getPrincipal();
//        md.addAttribute("user", super.getCurrentUser());

        // 2. 查询角色名
        List<Role> role = this.RoleMapper.findUserRole(myUserDetails.getId());
        md.addAttribute("user", myUserDetails);

        // 3. 判断是否为管理员,是则跳转到admin
        if (!role.isEmpty() && role.get(0).getRoleName().equals("管理员")) {
            return "adminIndex";
        }

        // 4. 不是管理员用户，重定向到普通界面
        return "redirect:index";
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

        // 把从数据库得到的user，传递给页面。d = data ,数据库的意思。duser可以理解为数据库的user
        User user = us.findByEmailOrIdNumber(myUser.getId());
        md.addAttribute("duser", user);

        // 传递成绩到页面,切记传递过去的对象不能为null和空字符串，否则页面会报错
        userGrande grande = this.gs.selectGrande(user.getId());
        if (grande != null) {
            md.addAttribute("grande", grande);
        } else {
            md.addAttribute("grande", new userGrande());
        }

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

    //    /user/selectGrande
    @RequestMapping("/user/selectGrande")
    @ResponseBody
    public ResponseBo selectGrande(String name, String id) {
        try {
            ResponseBo responseBo = this.gs.selectGrande(name, id);
            return responseBo;

        } catch (Exception e) {
            log.error("查询成绩失败", e);
            return ResponseBo.error("查询成绩失败，请联系网站管理员！");
        }
    }

    /**
     * TODO: 通过姓名、身份证号和问题重设密码
     *
     * @param name      姓名
     * @param id        身份证号
     * @param answer    问题答案
     * @param password1 新密码1
     * @param password2 新密码2
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/27 20:18
     */
    @RequestMapping("/user/retrievePassword")
    @ResponseBody
    public ResponseBo retrievePassword(String name, String id, String answer, String password1, String password2) {
        /*
            1. 提交身份证号，验证，返回问题
            2. 提交问题答案，进行验证，返回密码窗口
            3. 提交密码，设置到数据库
        */
        try {
            //
            if (name != null && name != "" && id != null && id != "" && answer != null && answer != "" && password1 != null && password1 != "" && password2 != null && password2 != "") {
                // System.err.println("3验证信息，然后修改密码");
                // 3验证信息，然后修改密码
                return this.us.updatePassword(name, id, answer, password1, password2);

            } else if (name != null && name != "" && id != null && id != "" && answer != null && answer != "") {
                // System.err.println("2验证答案");
                // 2验证答案，为了安全依然需要判断姓名、身份证号，返回提示：填写新密码
                return this.us.seleteAnswer(name, id, answer);

            } else if (name != null && name != "" && id != null && id != "") {
                // System.err.println("1验证姓名和身份证号");
                // 1验证姓名和身份证号，返回问题
                return this.us.seleteAnswer(name, id);
            }
        } catch (Exception e) {
            log.error("查询成绩失败", e);
            return ResponseBo.error("查询成绩失败，请联系网站管理员！");
        }
        return ResponseBo.error("操作失败，请检查信息是否正确.");
    }























    /*
    代码结束
     */
}
