package cn.gobyte.apply.service.user.impl;

import cn.gobyte.apply.dao.user.userGrandeMapper;
import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.userGrande;
import cn.gobyte.apply.service.BaseService;
import cn.gobyte.apply.service.user.GrandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrandeServiceImpl extends BaseService<userGrande> implements GrandeService {
    @Autowired
    private userGrandeMapper ugm;

    // 根据身份证号查询成绩
    @Override
    public userGrande selectGrande(String id) {
        // 去除前后空格
        id = id.trim();
        userGrande grande = this.ugm.selectGrande(id);
        return grande == null ? null : grande;
    }

    // 根据身份证号、姓名查询
    @Override
    public ResponseBo selectGrande(String name, String id) {
        name = name.trim();
        id = id.trim();
        userGrande userGrande = this.ugm.selectGrande2(name, id);
        return userGrande == null ? ResponseBo.error("查询的用户信息不存在") : ResponseBo.ok(userGrande);
    }
}
