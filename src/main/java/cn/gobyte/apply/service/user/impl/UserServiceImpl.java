package cn.gobyte.apply.service.user.impl;

import cn.gobyte.apply.dao.user.userMapper;
import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.Course;
import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.pojo.user.UserVo;
import cn.gobyte.apply.security.pojo.myUserDetails;
import cn.gobyte.apply.service.BaseService;
import cn.gobyte.apply.service.user.CourseService;
import cn.gobyte.apply.service.user.UserService;
import cn.gobyte.apply.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
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

    private final userMapper um;

    private final CourseService cs;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public UserServiceImpl(userMapper um, CourseService cs) {
        this.um = um;
        this.cs = cs;
    }

    //注册到数据库
    @Override
    public ResponseBo register(User user) {
        try {
            if (um == null) {
                return ResponseBo.error("注册服务启动失败，请联系管理员");
            }

//            System.err.println(user.toString() + "----" + this.getClass().getName());
            // 查询报考科目
            Course course = cs.selectExamCourseByMajor(user.getBkmajor());

            // 1.以身份证号为条件，判断是否为空注册;判断报考科目不为空，原因是考试报名系统，报考科目是不能缺少的
            if (user.getId() != null && !user.getId().equals("") && course != null) {

                // 设置默认信息，例如头像、主题、创建时间、默认激活账号等
                /*user.setTheme(user.DEFAULT_THEME); //默认主题
                user.setAvatar(user.DEFAULT_AVATAR); //默认头像*/
                user.setCreatTime(new Date());  //注册时间
                user.setState("未审核");   //资料未审核
                user.setAccountStatus("1"); //账户可用
                user.setMcode(course.getCid());// 设置专业代码
                user.setKskm1(course.getKm1());// 设置考试科目1
                user.setKskm2(course.getKm2());// 设置考试科目2
                user.setJl("0");// 登陆次数默认0次
                user.setGkbmh("00000000000000");//设置默认的高考报名号
                user.setSid("2");//设置类型为普通用户

                // 2.判断通过，身份证号可以注册
                //利用tk-mybatis进行注册
                int temp = this.save(user);
                if (temp > 0) {
                    return ResponseBo.ok();
                } else {
                    return ResponseBo.error("注册失败，请重试.");
                }
            } else {
                return ResponseBo.error("注册失败!身份证号/报考科目不能为空");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return ResponseBo.error("注册失败!");
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

//查找身份证号和邮箱是否存在。
/*    @Override
    public boolean findByNameAndIdNumber(String id, String email) {
        // 1. 先验证身份证号和邮箱格式的合法性
        // 2. 拿着身份证号和邮箱进行数据库查询
        // 3. 返回查询的结果
        email = email.trim();
        id = id.trim();

        boolean isEmail = Utils.checkEmail(email, 99);
        boolean isId;
        if (id.length() >= 18) {
            isId = true;
            return isId;
        }
        // 实例化
        Example example = new Example(User.class);
        if (isEmail) {
            // 开始查数据库
            example.createCriteria().andCondition("email=", email);

        }
        return isEmail;
    }*/

    /**
     * TODO: 根据身份证号更新用户登陆时间；初步设想是在登陆成功以后先获取一下登录时间，然后再使用该方法进行更新
     *
     * @param userName 身份证号
     * @author shanLan misterchou@qq.com
     * @date 2019/4/22 23:54
     */
    @Override
    @Transactional
    public void updateLoginTimeByIdNumber(String userName) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("id=", userName);

        User user = new User();
        user.setLastLoginTime(new Date());
        this.um.updateByExampleSelective(user, example);
    }

    /**
     * TODO: 根据id，更新user登陆次数
     *
     * @param id     身份证号
     * @param number 登陆的次数
     * @author shanLan misterchou@qq.com
     * @date 2019/4/25 20:36
     */
    @Override
    @Transactional
    public void updateLoginTotal(String id, String number) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("id=", id);

        User user = new User();
        user.setJl(number);
        this.um.updateByExampleSelective(user, example);
    }

    @Override
    public User findById(String id) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("id=", id);

        List<User> userList = this.selectByExample(example);
        return userList.isEmpty() ? null : userList.get(0);
    }

    @Override
    @Transactional
    public ResponseBo updateUser(User user) {
        try {
            // 修改用户资料的时候，不能修改用户名也就是身份证号和密码所以要设置null。如果身份证号不对，可以重新注册一个账号，但是不允许修改
//            user.setId(null);
            user.setPassword(null);
            // 设置修改时间modify_time
            user.setModifyTime(new Date());

            //获取登录信息中的系统id
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // 判断是否是匿名； 匿名类AnonymousAuthenticationToken
            if (authentication != null && !(authentication instanceof AnnotatedBeanDefinition)) {

                // 获得当前登陆用户对象
                myUserDetails myUserDetails = (myUserDetails) authentication.getPrincipal();

                // 是否为管理员，然后再设置主键.不等于管理员的时候才使用当前登陆账户的系统id（1管理员，2普通用户）
                if (myUserDetails.getSid().equals("2")) {
                    // 不是管理员，则使用当前登陆的用户的系统id
                    user.setSystemId(myUserDetails.getSystemid());
                } else {
                    // 管理员用户，则需要通过身份证号查询系统id来修改信息
                    // 通过身份证号查询:系统id(主键)
                    User byDataUser = this.findByEmailOrIdNumber(user.getId());
                    // 把查询到的主键赋值给需要修改的user
                    user.setSystemId(byDataUser.getSystemId());
                }

//                user.setSystemId(byDataUser.getSystemId());

                System.err.println(user.toString() + "----" + this.getClass().getName());

                // 通过主键，更新非null的信息到数据库
                int updateNumber = this.updateNotNull(user);
                if (updateNumber > 0) {
                    return ResponseBo.ok("修改成功！");
                } else {
                    return ResponseBo.error("修改失败");
                }
            }

        } catch (Exception e) {
            log.error("修改用户信息失败", e);
        }
        return ResponseBo.error("修改用户信息失败，请尝试重新登陆本系统");
    }

    // 修改用户密码
    @Override
    @Transactional
    public ResponseBo updatePassword(String password, String id) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("id=", id);

        User user = new User();
        if (password.length() < 6) {
            return ResponseBo.error("新密码长度不足六位数.");
        } else {
            user.setPassword(password);
            int num = this.um.updateByExampleSelective(user, example);
            if (num > 0) {
                return ResponseBo.ok("密码修改成功！");
            } else {
                return ResponseBo.error("密码修改失败，请联系管理员.");
            }
        }
    }

/*    // 通过身份证号查询问题
    @Override
    public ResponseBo seleteAnswer(String id) {
        id = id.trim();
        Example example = new Example(User.class);
        example.createCriteria().andCondition("id=", id);

        List<User> userList = this.selectByExample(example);
        return userList.isEmpty() ? ResponseBo.error("该身份证号未注册") : ResponseBo.ok(userList.get(0).getTswt());
    }*/

    // 通过姓名、身份证号查询问题
    @Override
    public ResponseBo seleteAnswer(String name, String id) {
        id = id.trim();
        name = name.trim();

        Example example = new Example(User.class);
        // 多条件查找；根据id和name
        example.createCriteria().andCondition("id=", id).andCondition("name=", name);

        List<User> userList = this.selectByExample(example);
        return userList.isEmpty() ? ResponseBo.error("该姓名或身份证号不正确") : ResponseBo.ok(userList.get(0).getTswt());
    }

    // 通过姓名、身份证号、问题答案查询
    @Override
    public ResponseBo seleteAnswer(String name, String id, String answer) {

        try {
            id = id.trim();
            name = name.trim();
            answer = answer.trim();

            Example example = new Example(User.class);
            example.createCriteria().andCondition("id=", id);
            example.createCriteria().andCondition("name=", name);
            example.createCriteria().andCondition("mmda=", answer);

            List<User> userList = this.selectByExample(example);

            // 返回ok即可，切记不要返回用户对象，否则用户信息将会泄露
            return userList.isEmpty() ? ResponseBo.error("该身份证号未注册") : ResponseBo.ok("请填写新密码");
        } catch (Exception e) {
            //System.err.println(e.getMessage());
        }

        return ResponseBo.error("通过姓名、身份证号、问题答案查询失败");
    }

    // 通过验证：姓名、身份证号、问题答案，来重置密码
    @Override
    public ResponseBo updatePassword(String name, String id, String answer, String password1, String password2) {

        ResponseBo responseBo = this.seleteAnswer(name, id, answer);
        if (responseBo != null) {
            password1 = password1.trim();
            password2 = password2.trim();

            if (password1.length() < 6 || password2.length() < 6) {
                return ResponseBo.error("新密码长度不足");
            } else if (!password1.equals(password2)) {
                return ResponseBo.error("两次输入的密码不相同");
            }

            // && responseBo.get("code")
            if (responseBo.get("code").toString().equals("0")) {
//                System.err.println("成功进入修改密码");

                // 修改密码
                return this.updatePassword(password1, id);

            } /*else {
                // System.err.println("失败");
            }*/
        }
        return ResponseBo.error("重置密码失败");
    }

    // 查询用户列表
    @Override
    public List<User> findUserByUsernameOrIdNumber(User user) {
        try {
            return this.um.findAllUserByUsernameOrIdNumber(user);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    // 通过id物理删除用户
    @Override
    public void deleteUsers(String userIds) {
        List<String> list = Arrays.asList(userIds.split(","));
        int num = this.batchDelete(list, "id", User.class);
        System.err.println("num====" + num);
    }
























    /*把编辑框空间拉大点，免得光标总是在屏幕下方*/

}
