package cn.gobyte.apply.service.page;

import cn.gobyte.apply.pojo.page.Menu;
import cn.gobyte.apply.pojo.page.Tree;
import cn.gobyte.apply.service.IService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

@CacheConfig(cacheNames = "MenuService")
public interface MenuService extends IService<Menu> {

    /**
     * TODO:查找用户权限
     *
     * @param userName
     * @return java.lang.String
     * @author shanLan misterchou@qq.com
     * @date 2019/4/9 23:32
     */
    String findUserPermissions(String userName);

    /**
     * TODO:找到用户菜单
     *
     * @param userName
     * @return java.util.List<cc.mrbird.system.domain.Menu>
     * @author shanLan misterchou@qq.com
     * @date 2019/4/9 23:33
     */
    List<Menu> findUserMenus(String userName);

    /**
     * TODO:找到所有的菜单
     *
     * @param menu
     * @return java.util.List<cc.mrbird.system.domain.Menu>
     * @author shanLan misterchou@qq.com
     * @date 2019/4/9 23:33
     */
    List<Menu> findAllMenus(Menu menu);

    /**
     * TODO:获取菜单按钮树
     *
     * @param
     * @return cc.mrbird.common.domain.Tree<cc.mrbird.system.domain.Menu>
     * @author shanLan misterchou@qq.com
     * @date 2019/4/9 23:33
     */
    Tree<Menu> getMenuButtonTree();

    /**
     * TODO:获得菜单树
     *
     * @param
     * @return cc.mrbird.common.domain.Tree<cc.mrbird.system.domain.Menu>
     * @author shanLan misterchou@qq.com
     * @date 2019/4/9 23:33
     */
    Tree<Menu> getMenuTree();

    /**
     * TODO:获得用户菜单
     *
     * @param userName
     * @return cc.mrbird.common.domain.Tree<cc.mrbird.system.domain.Menu>
     * @author shanLan misterchou@qq.com
     * @date 2019/4/9 23:33
     */
    Tree<Menu> getUserMenu(String userName);

    /**
     * TODO:根据ID查找菜单
     *
     * @param menuId
     * @return cc.mrbird.system.domain.Menu
     * @author shanLan misterchou@qq.com
     * @date 2019/4/9 23:34
     */
    Menu findById(Long menuId);

    /**
     * TODO:按名称和类型查找菜单
     *
     * @param menuName
     * @param type
     * @return cc.mrbird.system.domain.Menu
     * @author shanLan misterchou@qq.com
     * @date 2019/4/9 23:34
     */
    Menu findByNameAndType(String menuName, String type);

    /**
     * TODO:添加菜单
     *
     * @param menu
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/4/9 23:34
     */
    void addMenu(Menu menu);

    /**
     * TODO:修改菜单
     *
     * @param menu
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/4/9 23:34
     */
    void updateMenu(Menu menu);

    /**
     * 删除菜单
     * TODO:
     *
     * @param menuIds
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/4/9 23:34
     */
    void deleteMeuns(String menuIds);

    /**
     * TODO:获得所有链接URL
     *
     * @param p1
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.String>>
     * @author shanLan misterchou@qq.com
     * @date 2019/4/9 23:35
     */
    @Cacheable(key = "'url_'+ #p0")
    List<Map<String, String>> getAllUrl(String p1);

}
