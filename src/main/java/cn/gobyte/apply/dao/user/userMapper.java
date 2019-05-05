package cn.gobyte.apply.dao.user;

import cn.gobyte.apply.config.MyMapper;
import cn.gobyte.apply.pojo.user.User;
import org.springframework.stereotype.Component;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
@Component
public interface userMapper extends MyMapper<User> {

    /**
     * TODO:注册一个User用户到数据库内
     *
     * @param user 提供一个对象
     * @return java.lang.Integer 返回该数据在数据库表中的系统id
     * @author shanLan misterchou@qq.com
     * @date 2019/3/29 20:18
     */
    Integer register(User user);

    /**
     * TODO:查询身份证号是否存在数据库，True存在；false不存在
     *
     * @param id 需要被传入的身份证号
     * @return java.lang.Integer 返回数据的id，这个是数据库自增id
     * @author shanLan misterchou@qq.com
     * @date 2019/3/29 0:52
     */
    Integer queryId(String id);

    /**
     * TODO: 查询用户列表
     *
     * @param user
     * @return java.util.List<cn.gobyte.apply.pojo.user.User>:
     * @author shanLan misterchou@qq.com
     * @date 2019/5/4 21:25
     */
    List<User> findUserByUsernameOrIdNumber(User user);

}