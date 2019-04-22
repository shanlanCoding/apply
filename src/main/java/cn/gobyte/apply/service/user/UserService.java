package cn.gobyte.apply.service.user;

import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.pojo.user.UserVo;
import cn.gobyte.apply.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {

    /**
     * TODO: 注册用户到数据库；需要保证身份证号和邮箱号没有被注册过
     *
     * @param user 需要注册的对象，保存到数据库内
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/3/28 21:53
     */
    void register(User user);

    /**
     * TODO:登陆
     *
     * @param user
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/4/5 21:34
     */
    void login(UserVo user);

    /**
     * TODO:使用邮箱或者身份证号查找
     *
     * @param username 登录名，可以是身份证号或者邮箱
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/4/11 1:28
     */
    User findByEmailOrIdNumber(String username);

    /**
     * TODO: 查找身份证号和邮箱是否存在。
     *
     * @param id 身份证号
     * @param email 邮箱号
     * @return void:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/21 21:45
     */
    void findByNameAndIdNumber(String id, String email);

    /**
    * TODO: 更新用户登陆时间
    *
    * @param userName 需要更新的用户名
    * @return void:
    * @author shanLan misterchou@qq.com
    * @date 2019/4/23 0:01
    */
    void updateLoginTimeByIdNumber(String userName);
}
