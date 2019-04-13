package cn.gobyte.apply.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

@Data
public class myUserDetails extends User {

    public myUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public myUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer accountStatus) {
        super(username, password, authorities);
    }

    /**
     * 系统默认id
     */
    private Long systemid;

    /**
     * 身份证号
     */
    private String id;

    /**
     * 密码
     */
    private String password;

    /**
     * 提示问题
     */
    private String tswt;

    /**
     * 问题答案
     */
    private String mmda;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 学号
     */
    private String sid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 生日
     */
    private String birthd;

    /**
     * 性别
     */
    private String gender;

    /**
     * 民族
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
     * 主修科目
     */
    private String major;

    /**
     * 高考报名号
     */
    private String gkbmh;

    /**
     * 报考科目
     */
    private String bkmajor;

    /**
     * 报考专业的代码
     */
    private String mcode;

    /**
     * 记录
     */
    private String jl;

    /**
     * 退出
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
     * 状态
     */
    private String state;

    /**
     * 时间
     */
    private String sj;

    /**
     * 准考证号
     */
    private String zkzh;

    /**
     * 省份
     */
    private String sf;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 最近访问时间
     */
    private Date lastLoginTime;

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
     * 账号状态:0锁定 1有效
     */
    private Integer accountStatus;

}
