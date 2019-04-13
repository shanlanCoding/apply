package cn.gobyte.apply.dao.page;

import cn.gobyte.apply.pojo.page.Menu;

import java.util.List;

public interface MenuMapper {

    /**
     * TODO: 根据用户名查权限
     *
     * @param userName
     * @return java.util.List<cn.gobyte.apply.pojo.page.Menu>:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/14 1:12
     */
    public List<Menu> findUserPermissions(String userName);
}
