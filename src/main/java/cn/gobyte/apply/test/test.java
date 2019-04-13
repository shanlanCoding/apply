package cn.gobyte.apply.test;

import cn.gobyte.apply.pojo.user.User;
import cn.gobyte.apply.service.Impl.BaseService;
import cn.gobyte.apply.utils.Utils;
import cn.gobyte.apply.utils.idNumberVerify;
import org.junit.jupiter.api.Test;

public class test extends BaseService<User> {

//    @Autowired
//    private UserService userService;

    /**
     * TODO:测试身份证号验证码工具类
     *
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/3/28 20:55
     */
    @Test
    public void test01() {
        System.err.println("身份证号验证：" + idNumberVerify.validateIdCard18("421127199909098888"));
    }

    /**
     * TODO:测试DAO取数据库数据
     *
     * @param
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/4/11 17:00
     */
    @Test
    void test02() {
/*        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.findByEmailOrIdNumber("421127199408013338");
        System.err.println(user);*/

    }

    /**
     * TODO:邮箱校验测试
     *
     * @param
     * @return void
     * @author shanLan misterchou@qq.com
     * @date 2019/4/11 17:00
     */
    @Test
    void test03() {
        System.err.println(Utils.checkEmail("hi@goby1te.csdf1", 18));
    }

    @Test
    void test04() {
        System.err.println(Utils.checkEmail("hi@goby1te.csdf1", 18));
    }

}
