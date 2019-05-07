package cn.gobyte.apply.service.user;

import cn.gobyte.apply.pojo.user.Role;
import cn.gobyte.apply.service.IService;

import java.util.List;

/**
 * TODO: 和角色相关的service类
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/29 1:40
 */
public interface RoleService extends IService<Role> {

    /**
     * TODO: 找到用户角色
     *
     * @param userName
     * @return java.util.List<cc.mrbird.system.domain.Role>:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/29 1:04
     */
    List<Role> findUserRole(String userName);

    /**
     * TODO: 找到所有的角色
     *
     * @param role 角色对象
     * @return java.util.List<cc.mrbird.system.domain.Role>:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/29 1:39
     */
    List<Role> findAllRole(Role role);

    // 使用菜单查找角色
//    RoleWithMenu findRoleWithMenus(Long roleId);

    /**
     * TODO: 根据角色名查找角色
     *
     * @param roleName
     * @return cn.gobyte.apply.pojo.user.Role:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/29 1:41
     */
    Role findByName(String roleName);

    /**
     * TODO: 添加角色
     *
     * @param role
     * @param menuIds
     * @return void:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/29 1:42
     */
    void addRole(Role role, Long[] menuIds);

    /**
     * TODO: 更新角色
     *
     * @param role
     * @param menuIds
     * @return void:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/29 1:42
     */
    void updateRole(Role role, Long[] menuIds);

    /**
     * TODO: 删除角色
     *
     * @param roleIds
     * @return void:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/29 1:42
     */
    void deleteRoles(String roleIds);

    /**
     * TODO: 根据用户Id删除用户角色
     *
     * @param userIds
     * @return void:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/29 1:02
     */
    void deleteUserRolesByUserId(String userIds);
}
