package cn.gobyte.apply.service.user.impl;

import cn.gobyte.apply.dao.user.userMapper;
import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.pojo.user.UserVo;
import cn.gobyte.apply.service.BaseService;
import cn.gobyte.apply.service.user.UserService;
import cn.gobyte.apply.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 用户的业务类，里面包括了注册，登陆等等用户的业务
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/10 1:57
 */

@Service("userService")
// 继承了基础的BaseService类，然后再去实现需要完善的UserService类
public class UserServiceImpl extends BaseService<User> implements UserService {

    //    mapper，查询数据库
    @Autowired
    private userMapper um;

    public UserServiceImpl(Mapper<User> mapper) {
        super(mapper);
    }

    //注册到数据库
    @Override
    public void register(User user) {
        try {
            // 1.以身份证号为条件，判断是否为空注册
            if (user.getId() != null && !user.getId().equals("") && um != null) {
                String id = user.getId();
                //如果返回了null ，说明没有找到，即代表可以注册
                if (um.queryId(id) == null) {
                    // 补充部分字段，例如时间
                    user.setCreatTime(new Date());
                    user.setTheme(user.DEFAULT_THEME);
                    user.setAvatar(user.DEFAULT_AVATAR);

                    // 2.判断通过，身份证号可以注册
                    //利用tk-mybatis进行注册
                    this.save(user);
                    // um.register(user);

                    Long sysId = user.getSystemId();

                    System.err.println("sysId：" + sysId + this.getClass().getName());
                } else {
                    System.err.println("存在" + um.queryId(user.getId()) + this.getClass().getName());
                }
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(UserVo user) {

    }

    //使用邮箱或者身份证号查找
    @Override
    public User findByEmailOrIdNumber(String arg) {

        // 实例化
        Example example = new Example(User.class);

        // 判断是否为邮箱
        boolean isEmail = Utils.checkEmail(arg, 99);
        if (isEmail) {
            // 开始查数据库，email 是否等于 arg
            example.createCriteria().andCondition("email=", arg);
        } else {
            // 否则把arg当成身份证号去查询，先转小写
            example.createCriteria().andCondition("id=", arg.toLowerCase());
        }
        List<User> userList = this.selectByExample(example);
        // 三元表达式 userList是空的，则返回null；否则返回第0个索引
        return userList.isEmpty() ? null : userList.get(0);
    }

}
