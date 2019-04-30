package cn.gobyte.apply.pojo.user;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * TODO: 用户关联角色
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/29 1:00
 */
@Table(name = "t_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = -3166012934498268403L;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "ROLE_ID")
    private Long roleId;

    /**
     * @return USER_ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return ROLE_ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}