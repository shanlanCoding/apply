package cn.gobyte.apply.controller;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主页，用户页面的所有操作都是从这里开始的
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/10 1:56
 */

@Controller
public class index {

    //注入业务实现类，所有的业务方法都是调用该类
//    @Autowired
//    private UserServiceImpl us;

    /**
     * TODO:默认访问首页
     *
     * @return java.lang.String
     * @author shanLan misterchou@qq.com
     * @date 2019/3/29 22:44
     */
    @GetMapping("/")
    public String toRootIndex() {
        return "index";
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
        return "index";
    }

    @GetMapping("/index")
    public String toIndex() {
        return "index";
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
        return "home";
    }

    /*
     * 注册学生的账号
     *
     * @param oldUser 传递进来的学生对象
     * @return java.lang.String
     * @author shanLan misterchou@qq.com
     * @date 2019/3/29 22:47
     */
/*    @PostMapping("/register")
    public String registerUser(oldUser oldUser) {
        System.err.println("oldUser：" + oldUser.toString());
//        us.register(oldUser);

        //forward 内部跳转,redirect
        return "index";
    }*/

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /*
     * 登陆
     * * @param modelAndView
     *
     * @param user
     * @return org.springframework.web.servlet.ModelAndView
     * @author shanLan misterchou@qq.com
     * @date 2019/4/5 0:34
     */

    /*@RequestMapping("/logins")
    public ModelAndView logins(ModelAndView modelAndView, String username, String password) {

        if (binding Result.hasErrors()) {
            modelAndView.addObject("error", bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("index");
            return modelAndView;
        }
        System.err.println("进来了");

        // .取出账户名、密码，去除所有空格
        String username = user.getId().replace(" ", "");
        String password = user.getPassword().replace(" ", "");

        // 1.判断是身份证号 | 邮箱
        String regexp = "^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$";
        if (Pattern.matches(regexp, username)) {
            // 用户名为身份证号，判断身份证号是否存在
            if (!"421127199909098888".equals(username)) {
                modelAndView.addObject("error", "身份证号不存在！");
                modelAndView.setViewName("index");
                return modelAndView;
            }
        }
        // 判断是否用户名为邮箱
        else if (!username.equals("291637732@qq.com")) {
            modelAndView.addObject("error", "邮箱不存在！");
            modelAndView.setViewName("index");
            return modelAndView;
        }

        // 3.登陆验证，判断账号或者密码

//        System.err.println("MenuMapper------" + user.toString());


        if (password != us.queryPassword) {
            modelAndView.addObject("error", "密码错误！");
            modelAndView.setViewName("index");
            return modelAndView;
        }

        modelAndView.addObject("userName", username);
        modelAndView.setViewName("home");
        return modelAndView;
    }*/

}
