package cn.gobyte.apply.service.user;

import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.userGrande;
import cn.gobyte.apply.service.IService;

public interface GrandeService extends IService<userGrande> {

    /**
     * TODO: 根据身份证号查询成绩
     *
     * @param id
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/27 0:41
     */
    userGrande selectGrande(String id);

    /**
     * TODO: 根据身份证号、姓名查询
     *
     * @param name
     * @param id
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/27 16:08
     */
    ResponseBo selectGrande(String name, String id);

}
