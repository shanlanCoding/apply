package cn.gobyte.apply.security;

import cn.gobyte.apply.dao.page.MenuMapper;
import cn.gobyte.apply.pojo.page.Menu;
import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.service.UserService;
import cn.gobyte.apply.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.List;

/**
 * 自定义认证必须要实现UserDetailService接口
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/10 23:50
 */
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuMapper menuService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 从数据库查user
        User user = userService.findByEmailOrIdNumber(s);

        if( user != null )
        {
            // 查询权限、按钮列表
            List<Menu> permissions = this.menuService.findUserPermissions(user.getId());

            // 用户状态锁，true正常；false锁定
            boolean notLocked = false;
            if (StringUtils.equals(User.STATUS_VALID, user.getState())){
                // 有效
                notLocked = true;
            }

            //  设置用户信息
            /*这里我们使用了org.springframework.security.core.userdetails.User类包含7个参数的构造器，其还包含一个三个参数的构造器User(String username, String password,Collection<? extends GrantedAuthority> authorities)，
            由于权限参数不能为空，所以这里先使用AuthorityUtils.commaSeparatedStringToAuthorityList方法模拟一个admin的权限，该方法可以将逗号分隔的字符串转换为权限集合。
             */
            myUserDetails userDetails = new myUserDetails(user.getName(), user.getPassword(), true, true, true,
                    notLocked, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
            userDetails.setEmail(user.getEmail());
            userDetails.setPassword(user.getPassword());
            userDetails.setLoginTime(DateUtil.getDateFormat(new Date(), DateUtil.FULL_DATE_FORMAT));
        }


        return null;
    }

}
