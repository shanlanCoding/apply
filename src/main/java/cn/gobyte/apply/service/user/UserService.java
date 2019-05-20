package cn.gobyte.apply.service.user;

import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.pojo.user.UserVo;
import cn.gobyte.apply.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO: 用户方法类
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/24 2:03
 */

@Service
public interface UserService extends IService<User> {

    /**
     * TODO: 注册用户到数据库；需要保证身份证号和邮箱号没有被注册过
     *
     * @param user 需要注册的对象，保存到数据库内
     * @return boolean 成功true，失败fasle
     * @author shanLan misterchou@qq.com
     * @date 2019/3/28 21:53
     */
    ResponseBo register(User user);

    /**
     * TODO:登陆
     *
     * @param user 用户
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
     * TODO: 更新用户登陆时间
     *
     * @param userName 需要更新的用户名
     * @author shanLan misterchou@qq.com
     * @date 2019/4/23 0:01
     */
    void updateLoginTimeByIdNumber(String userName);

    /**
     * TODO: 根据id，更新user登陆次数
     *
     * @param id     身份号
     * @param number 次数
     * @author shanLan misterchou@qq.com
     * @date 2019/4/24 20:09
     */
    void updateLoginTotal(String id, String number);

    /**
     * TODO: 根据id获取用户信息
     *
     * @param id 身份证号
     * @author shanLan misterchou@qq.com
     * @date 2019/4/25 1:32
     */
    User findById(String id);

    /**
     * TODO: 修改用户资料
     *
     * @param user 用户
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/25 20:12
     */
    ResponseBo updateUser(User user);

    /**
     * TODO: 修改用户密码
     *
     * @param password 新的密码
     * @param id       用户名，一般是身份证号
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/26 0:59
     */
    ResponseBo updatePassword(String password, String id);

    /**
     * TODO: 通过姓名、身份证号查询问题
     *
     * @param name 姓名
     * @param id   身份证号
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/27 20:38
     */
    ResponseBo seleteAnswer(String name, String id);

    /**
     * TODO: 通过姓名、身份证号、问题答案查询
     *
     * @param name   姓名
     * @param id     身份证号
     * @param answer 答案
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/27 20:45
     */
    ResponseBo seleteAnswer(String name, String id, String answer);

    /**
     * TODO: 通过验证：姓名、身份证号、问题答案，来重置密码
     *
     * @param name      姓名
     * @param id        身份证号
     * @param answer    答案
     * @param password1 密码1
     * @param password2 密码2
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/27 20:49
     */
    ResponseBo updatePassword(String name, String id, String answer, String password1, String password2);

    /**
     * TODO: 查询用户列表
     *
     * @param user 用户
     * @return java.util.List<cn.gobyte.apply.pojo.user.User>:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/4 22:41
     */
    List<User> findUserByUsernameOrIdNumber(User user);

    /**
     * TODO: 通过id物理删除用户
     *
     * @param userIds 用户id字符串，多个id用英文逗号,分割
     * @author shanLan misterchou@qq.com
     * @date 2019/4/7 11:02
     */
    void deleteUsers(String userIds);
}
