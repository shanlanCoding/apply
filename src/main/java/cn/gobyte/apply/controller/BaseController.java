package cn.gobyte.apply.controller;

import cn.gobyte.apply.domain.QueryRequest;
import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.security.pojo.myUserDetails;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * TODO: 这个是Controller的基础类
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/26 0:47
 */
public class BaseController {

    /**
     * TODO: 按页号大小选择
     *
     * @param request
     * @param s
     * @return java.util.Map<java.lang.String, java.lang.Object>:
     * @author shanLan misterchou@qq.com
     * @date 2019/5/2 22:08
     */
    protected Map<String, Object> selectByPageNumSize(QueryRequest request, Supplier<?> s) {
//        System.err.println(s.toString() + "----" + this.getClass().getName());
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageInfo<?> pageInfo = new PageInfo<>((List<?>) s.get());
        PageHelper.clearPage();
        return getDataTable(pageInfo);
    }

    /**
     * TODO: 根据页面信息返回表格
     *
     * @param pageInfo
     * @return java.util.Map<java.lang.String, java.lang.Object>:
     * @author shanLan misterchou@qq.com
     * @date 2019/5/4 21:24
     */
    private Map<String, Object> getDataTable(PageInfo<?> pageInfo) {
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("rows", pageInfo.getList());
        rspData.put("total", pageInfo.getTotal());
        return rspData;
    }

/*	protected Map<String, Object> selectByPageNumSize(QueryRequest request, Supplier<?> s) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		PageInfo<?> pageInfo = new PageInfo<>((List<?>) s.get());
		PageHelper.clearPage();
		return getDataTable(pageInfo);
	}*/

    /**
     * TODO: 获取当前登录的用户信息，如身份证号、邮箱、密码
     *
     * @param
     * @return cn.gobyte.apply.pojo.user.User:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/26 0:55
     */
    protected User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        User user = new User();
/*		// 普通用户
		if(principal instanceof FebsUserDetails){
			FebsUserDetails userDetails = (FebsUserDetails) principal;
			user.setUserId(userDetails.getUserId());
			user.setPassword(userDetails.getPassword());
			user.setUsername(userDetails.getUsername());
		}else{
			// 社交账号
			FebsSocialUserDetails userDetails = (FebsSocialUserDetails) principal;
			user.setUserId(userDetails.getUsersId());
			user.setPassword(userDetails.getPassword());
			user.setUsername(userDetails.getUsername());
		}*/
        // 设置账户信息
        myUserDetails userDetails = (myUserDetails) principal;
        user.setSystemId(userDetails.getSystemid());
        user.setId(userDetails.getId());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());

        return user;
    }
}
