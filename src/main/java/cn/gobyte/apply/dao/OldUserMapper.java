package cn.gobyte.apply.dao;

import cn.gobyte.apply.pojo.oldUser;
import org.springframework.stereotype.Repository;

//@Component
@Repository
//@Service
public interface OldUserMapper {

    /**
    TODO:注册一个User用户到数据库内
    * @param oldUser 提供一个对象
    * @return java.lang.Integer 返回该数据在数据库表中的系统id
    * @author shanLan misterchou@qq.com
    * @date 2019/3/29 20:18
    */
    public Integer register(oldUser oldUser);

    /**
    TODO:查询身份证号是否存在数据库，True存在；false不存在
    * @param id 需要被传入的身份证号
    * @return java.lang.Integer 返回数据的id，这个是数据库自增id
    * @author shanLan misterchou@qq.com
    * @date 2019/3/29 0:52
    */
    public Integer queryId(String id);


}


