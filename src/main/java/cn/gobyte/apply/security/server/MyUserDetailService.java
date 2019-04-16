package cn.gobyte.apply.security.server;

import cn.gobyte.apply.dao.page.MenuMapper;
import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.security.pojo.myUserDetails;
import cn.gobyte.apply.service.user.UserService;
import cn.gobyte.apply.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

/**
 * 自定义认证必须要实现UserDetailService接口
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/10 23:50
 */
@Configuration
public class MyUserDetailService implements UserDetailsService {
    // 注入方法类；idea不推荐使用autowried进行注入，推荐使用构造方法注入
    private final UserService userService;
    private final MenuMapper menuService;
    private final PasswordEncoder passwordEncoder;

    public MyUserDetailService(UserService userService, MenuMapper menuService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.menuService = menuService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * TODO: Security默认的表单登陆方法，一定要覆盖。
     *
     * @param s 传入的用户名
     * @return org.springframework.security.core.userdetails.UserDetails: 返回一个用户信息对象，Security需要的。
     * @author shanLan misterchou@qq.com
     * @date 2019/4/15 18:34
     * <p>
     * 程序流程：
     * 1. 根据表单提交的用户名，去数据库查询
     * 2. 如果查到了，则把改用户封装成对象，返回给Security。具体该用户里应该包含基本信息，还有权限，如拥有哪些按钮等；如果没查到默认就是null
     * 3.
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 1. 根据表单提交的用户名，去数据库查询查user
        User user = userService.findByEmailOrIdNumber(s);
        if (user != null) {
            // 一定要把密码加密，否则登陆失败
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));

            // 查询权限、按钮列表
//            List<Menu> permissions = this.menuService.findUserPermissions(user.getId());

            // 用户状态锁，true正常；false锁定
            boolean notLocked = false;
            if (StringUtils.equals(User.STATUS_VALID, user.getAccountStatus())) {
                // 用户有效
                notLocked = true;
            }

            //  设置用户信息
            // 这里我们使用了org.springframework.security.core.userdetails.User类包含7个参数的构造器，其还包含一个三个参数的构造器User(String username, String password,Collection<? extends GrantedAuthority> authorities)，
            // 由于权限参数不能为空，所以这里先使用AuthorityUtils.commaSeparatedStringToAuthorityList方法模拟一个admin的权限，该方法可以将逗号分隔的字符串转换为权限集合。

            // 把用户的email设置为登录名
            myUserDetails userDetails = new myUserDetails(user.getEmail(), user.getPassword(), true, true, true,
                    notLocked, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

            userDetails.setEmail(user.getEmail());
            userDetails.setPassword(user.getPassword());
            userDetails.setLoginTime(DateUtil.getDateFormat(new Date(), DateUtil.FULL_DATE_FORMAT));

            return userDetails;
        } else {
            throw new UsernameNotFoundException("用户没有找到");
        }

    }

}

/*
  登陆成功的类，该类负责设置用户的资料，该类一定要实现，这个是Security必须的。

  @author shanLan misterchou@qq.com
 * @date 2019/4/15 18:18
 */
/*@Configuration
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        // 模拟一个用户，替代数据库获取逻辑
        MyUser user = new MyUser();
        user.setUserName("421127199909098888");

        System.err.println("id----" + id);

        user.setPassword(this.passwordEncoder.encode(" "));

        // 输出加密后的密码
//        System.out.println(user.getPassword());

        return new User(id, user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }*/


