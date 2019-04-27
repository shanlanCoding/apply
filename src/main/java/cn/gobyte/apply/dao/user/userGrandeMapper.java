package cn.gobyte.apply.dao.user;

import cn.gobyte.apply.config.MyMapper;
import cn.gobyte.apply.pojo.user.userGrande;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface userGrandeMapper extends MyMapper<userGrande> {

    /**
     * TODO: 根据身份证号查询
     *
     * @param id
     * @return cn.gobyte.apply.pojo.user.userGrande:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/27 0:40
     */
    userGrande selectGrande(@Param("id") String id);

    /**
     * TODO: 根据身份证号、姓名查询
     *
     * @param id
     * @return cn.gobyte.apply.pojo.user.userGrande:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/27 16:00
     */
    userGrande selectGrande2(String name, String id);

}