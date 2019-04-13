package cn.gobyte.apply.service;

import cn.gobyte.apply.pojo.User;
import cn.gobyte.apply.pojo.oldUser;
import cn.gobyte.apply.pojo.UserVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {

    /**
     * TODO: 注册用户到数据库
     *
     * @param oldUser 需要注册的对象，保存到数据库内
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/3/28 21:53
     */
    public void register(oldUser oldUser);

    /**
     * TODO:登陆
     *
     * @param user
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/4/5 21:34
     */
    public void login(UserVo user);

    /**
     * TODO:使用邮箱或者身份证号查找
     *
     * @param username 登录名，可以是身份证号或者邮箱
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/4/11 1:28
     */
    public User findByEmailOrIdNumber(String username);

}
