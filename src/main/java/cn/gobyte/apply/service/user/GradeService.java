package cn.gobyte.apply.service.user;

import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.userGrade;
import cn.gobyte.apply.service.IService;

import java.util.List;

public interface GradeService extends IService<userGrade> {

    /**
     * TODO: 根据身份证号查询成绩
     *
     * @param id
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/27 0:41
     */
    userGrade selectGrade(String id);

    /**
     * TODO: 根据条件查询所有的成绩
     *
     * @param userGrade
     * @return java.util.List<cn.gobyte.apply.pojo.user.userGrade>:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/7 15:33
     */
    List<userGrade> selectGradeAll(userGrade userGrade);

    /**
     * TODO: 根据身份证号、姓名查询
     *
     * @param name
     * @param id
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/27 16:08
     */
    ResponseBo selectGrade(String name, String id);

    /**
     * TODO: 修改单个用户成绩
     *
     * @param userGrade
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/8 13:46
     */
    ResponseBo editGrade(userGrade userGrade);

}
