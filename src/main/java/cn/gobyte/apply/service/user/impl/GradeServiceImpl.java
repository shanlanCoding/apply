package cn.gobyte.apply.service.user.impl;

import cn.gobyte.apply.dao.user.UserGradeMapper;
import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.userGrade;
import cn.gobyte.apply.service.BaseService;
import cn.gobyte.apply.service.user.GradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeServiceImpl extends BaseService<userGrade> implements GradeService {
    @Autowired
    private UserGradeMapper ugm;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //根据条件查询所有的成绩
    @Override
    public List<userGrade> selectGradeAll(userGrade userGrade) {

        try {
            List<cn.gobyte.apply.pojo.user.userGrade> userGradeList = this.ugm.selectAllGrade(userGrade);
            return userGradeList;
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    // 根据身份证号查询成绩
    @Override
    public userGrade selectGrade(String id) {
        // 去除前后空格
        id = id.trim();
        userGrade grade = this.ugm.selectGrade(id);
        return grade == null ? null : grade;
    }

    // 根据身份证号、姓名查询
    @Override
    public ResponseBo selectGrade(String name, String id) {
        name = name.trim();
        id = id.trim();
        userGrade userGrade = this.ugm.selectGradeByIdAndName(name, id);
        return userGrade == null ? ResponseBo.error("查询的用户信息不存在") : ResponseBo.ok(userGrade);
    }

    // 修改单个用户成绩
    @Override
    public ResponseBo editGrade(userGrade userGrade) {
        try {
            int i = this.updateNotNull(userGrade);
            if (i > 0) {
                return ResponseBo.ok("修改成功");
            } else {
                return ResponseBo.error("修改失败");
            }
        } catch (Exception e) {
            log.error("修改成绩信息失败", e);
            return ResponseBo.error("修改失败，请联系管理员处理");
        }

    }



    /*扩大输入框*/
}
