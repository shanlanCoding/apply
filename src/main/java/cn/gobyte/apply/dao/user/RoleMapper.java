package cn.gobyte.apply.dao.user;

import cn.gobyte.apply.config.MyMapper;
import cn.gobyte.apply.pojo.user.Role;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {
    /**
     * TODO: 根据用户的身份证号或者邮箱号，找到用户的角色
     *
     * @param userName 用户的身份证号或者邮箱号，建议使用身份证号查询
     * @return java.util.List<cc.mrbird.system.domain.Role>:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/29 1:01
     */
    List<Role> findUserRole(String userName);


    /**
     * TODO: 主键查询
     *
     * @param roleId
     * @return java.util.List<cc.mrbird.system.domain.RoleWithMenu>:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/29 1:28
     */
//	List<RoleWithMenu> findById(Long roleId);
}