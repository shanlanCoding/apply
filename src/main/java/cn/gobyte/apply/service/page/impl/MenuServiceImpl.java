package cn.gobyte.apply.service.page.impl;

import cn.gobyte.apply.dao.page.MenuMapper;
import cn.gobyte.apply.pojo.page.Menu;
import cn.gobyte.apply.pojo.page.Tree;
import cn.gobyte.apply.service.Impl.BaseService;
import cn.gobyte.apply.service.page.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuServiceImpl extends BaseService<Menu> implements MenuService {
    // 日志
    private Logger log = LoggerFactory.getLogger(this.getClass());

    // 成员变量
    @Autowired
    private MenuMapper menuMapper;

    // 查找用户权限、按钮
    @Override
    public String findUserPermissions(String userName) {
        List<Menu> list = this.menuMapper.findUserPermissions(userName);
        String s = list.stream().map(Menu::getPerms).collect(Collectors.joining(","));
        return s;
    }

    @Override
    public List<Menu> findUserMenus(String userName) {
        return null;
    }

    @Override
    public List<Menu> findAllMenus(Menu menu) {
        return null;
    }

    @Override
    public Tree<Menu> getMenuButtonTree() {
        return null;
    }

    @Override
    public Tree<Menu> getMenuTree() {
        return null;
    }

    @Override
    public Tree<Menu> getUserMenu(String userName) {
        return null;
    }

    @Override
    public Menu findById(Long menuId) {
        return null;
    }

    @Override
    public Menu findByNameAndType(String menuName, String type) {
        return null;
    }

    @Override
    public void addMenu(Menu menu) {

    }

    @Override
    public void updateMenu(Menu menu) {

    }

    @Override
    public void deleteMeuns(String menuIds) {

    }

    @Override
    public List<Map<String, String>> getAllUrl(String p1) {
        return null;
    }
}
