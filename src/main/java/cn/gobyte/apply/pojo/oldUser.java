package cn.gobyte.apply.pojo;

import cn.gobyte.apply.annotation.ExportConfig;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/*
 * TODO:用户注册的表，里面包含了用户个人信息
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/3/27 22:15
 */

@Table(name = "users")

//使用插件，代替累赘的Set/Get
@Getter
@Setter
@ToString
@AllArgsConstructor//有参构造
@NoArgsConstructor//无参构造
public class oldUser implements Serializable {
    private static final long serialVersionUID = -1561561645651651L;

    /**
     * 账户状态: 0锁定; 1有效
     */
    public static final String STATUS_VALID = "1";

    /*
     * 锁状态
     */
    public static final String STATUS_LOCK = "0";
    /**
     * 主题颜色
     */
    public static final String DEFAULT_THEME = "green";
    /**
     * 默认头像
     */
    public static final String DEFAULT_AVATAR = "default.jpg";

    /**
     * 男
     */
    public static final String SEX_MALE = "0";
    /**
     * 女
     */
    public static final String SEX_FEMALE = "1";
    /**
     * 未知性别
     */
    public static final String SEX_UNKNOW = "2";



    //--------------------------------------------------------

    /**
     * 数据库系统id，插入数据库后会返回主键
     */
    // 返回数据库自增长的主键
    @GeneratedValue(generator = "JDBC")
    private Integer systemId;

    /**
     * 身份证号，不能为空，18位
     */
    @ExportConfig(value = "身份证号")
//    @Column(name = "id")// 数据库列名
    @NotEmpty(message = "身份证号不能为空")
    @Pattern(regexp = "^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message = "身份证格式错误")
    private String id;

    /**
     * 密码，非空，6-18位
     */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 18, message = "密码长度必须位于6到18之间")
    private String password;

    /**
     * 邮箱验证，非空
     */
    @ExportConfig(value = "邮箱")
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    /**
     * 创建时间，非空
     */
    @ExportConfig(value = "创建时间", convert = "c:cc.mrbird.common.utils.poi.convert.TimeConvert")
    @NotEmpty(message = "创建时间不能为空")
    private Date creat_time;

    /**
     * 账号状态:0锁定 1有效，非空
     */
    @ExportConfig(value = "状态", convert = "s:0=锁定,1=有效")
    @NotEmpty(message = "账号状态不能为空")
    private String account_status;

    /**
     * 提示问题
     */
    @NotEmpty(message = "提示问题不能为空")
    private String tswt;

    /**
     * 问题答案
     */
    @ExportConfig(value = "问题答案")
    @NotEmpty(message = "问题答案不能为空")
    private String mmda;

    /**
     * 学号
     */
    private String sid;

    /**
     * 姓名
     */
    @ExportConfig(value = "姓名")
    private String name;

    /**
     * 生日
     */
    private String birthd;

    /**
     * 性别
     */
    @ExportConfig(value = "性别", convert = "s:0=男,1=女,2=保密")
    private String gender;

    /**
     * 名族
     */
    private String mz;

    /**
     * 政治面貌
     */
    private String zzmm;

    /**
     * 电话号
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 学校
     */
    private String school;

    /**
     * 学校代码
     */
    private String schoolc;

    /**
     * 之前的专业
     */
    private String major;

    /**
     * 高考报名号
     */
    private String gkbmh;

    /**
     * 报考专业
     */
    private String bkmajor;

    /**
     * 报考专业的代码
     */
    private String mcode;

    /**
     * 在校期间获得何种奖励
     */
    private String jl;

    /**
     * 特长
     */
    private String tc;

    /**
     * 联系地址
     */
    private String lxaddress;

    /**
     * 邮编
     */
    private String yb;

    /**
     * 未审核、已审核
     */
    private String state;

    /**
     * 时间,2019-03-27 22:27:34
     */
    private String sj;

    /**
     * 准考证号 201601002
     */
    private String zkzh;

    /**
     * 省份
     */
    private String sf;

    /**
     * 修改时间
     */
    private Date modify_time;

    /**
     * 最近访问时间
     */
    private Date last_login_time;

    /**
     * 主题
     */
    private String theme;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 描述
     */
    private String description;

    /**
     * TODO:最少创建一个用户必备参数
     *
     * @param id             身份证号
     * @param password       密码
     * @param email          邮箱
     * @param account_status 账号状态:0锁定 1有效
     * @param creat_time     创建时间
     * @return
     * @author shanLan misterchou@qq.com
     * @date 2019/4/11 1:21
     */
    public oldUser(String id, String password, String email, int account_status, Date creat_time) {
        this.sid = sid;
    }
}