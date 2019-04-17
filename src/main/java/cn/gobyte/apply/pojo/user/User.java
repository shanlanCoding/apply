package cn.gobyte.apply.pojo.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表，里面包含了用户的所有信息
 */
@Table(name = "users")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -6478492890959452848L;

    /**
     * 账户状态: 0锁定; 1有效
     */
    public static final String STATUS_VALID = "1";

    /**
     * 账户状态: 0锁定; 1有效
     */
    public static final String STATUS_LOCK = "0";

    /**
     * 主题
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
     * 系统默认id
     */
    @Id
    @Column(name = "systemId")
    @GeneratedValue(generator = "JDBC") // 插入后返回主键
    private Long systemId;

    /**
     * 身份证号
     */
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "creat_time")
    private Date creatTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 最近访问时间
     */
    @Column(name = "last_login_time")
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
    @Column(name = "account_status")
    private String accountStatus;

    /**
     * 获取系统默认id
     *
     * @return systemId - 系统默认id
     */
    public Long getSystemId() {
        return systemId;
    }

    /**
     * 设置系统默认id
     *
     * @param systemId 系统默认id
     */
    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    /**
     * 获取身份证号
     *
     * @return id - 身份证号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置身份证号
     *
     * @param id 身份证号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取提示问题
     *
     * @return tswt - 提示问题
     */
    public String getTswt() {
        return tswt;
    }

    /**
     * 设置提示问题
     *
     * @param tswt 提示问题
     */
    public void setTswt(String tswt) {
        this.tswt = tswt == null ? null : tswt.trim();
    }

    /**
     * 获取问题答案
     *
     * @return mmda - 问题答案
     */
    public String getMmda() {
        return mmda;
    }

    /**
     * 设置问题答案
     *
     * @param mmda 问题答案
     */
    public void setMmda(String mmda) {
        this.mmda = mmda == null ? null : mmda.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取学号
     *
     * @return sid - 学号
     */
    public String getSid() {
        return sid;
    }

    /**
     * 设置学号
     *
     * @param sid 学号
     */
    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取生日
     *
     * @return birthd - 生日
     */
    public String getBirthd() {
        return birthd;
    }

    /**
     * 设置生日
     *
     * @param birthd 生日
     */
    public void setBirthd(String birthd) {
        this.birthd = birthd == null ? null : birthd.trim();
    }

    /**
     * 获取性别
     *
     * @return gender - 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * 获取民族
     *
     * @return mz - 民族
     */
    public String getMz() {
        return mz;
    }

    /**
     * 设置民族
     *
     * @param mz 民族
     */
    public void setMz(String mz) {
        this.mz = mz == null ? null : mz.trim();
    }

    /**
     * 获取政治面貌
     *
     * @return zzmm - 政治面貌
     */
    public String getZzmm() {
        return zzmm;
    }

    /**
     * 设置政治面貌
     *
     * @param zzmm 政治面貌
     */
    public void setZzmm(String zzmm) {
        this.zzmm = zzmm == null ? null : zzmm.trim();
    }

    /**
     * 获取电话号
     *
     * @return tel - 电话号
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置电话号
     *
     * @param tel 电话号
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取学校
     *
     * @return school - 学校
     */
    public String getSchool() {
        return school;
    }

    /**
     * 设置学校
     *
     * @param school 学校
     */
    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    /**
     * 获取学校代码
     *
     * @return schoolc - 学校代码
     */
    public String getSchoolc() {
        return schoolc;
    }

    /**
     * 设置学校代码
     *
     * @param schoolc 学校代码
     */
    public void setSchoolc(String schoolc) {
        this.schoolc = schoolc == null ? null : schoolc.trim();
    }

    /**
     * 获取主修科目
     *
     * @return major - 主修科目
     */
    public String getMajor() {
        return major;
    }

    /**
     * 设置主修科目
     *
     * @param major 主修科目
     */
    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    /**
     * 获取高考报名号
     *
     * @return gkbmh - 高考报名号
     */
    public String getGkbmh() {
        return gkbmh;
    }

    /**
     * 设置高考报名号
     *
     * @param gkbmh 高考报名号
     */
    public void setGkbmh(String gkbmh) {
        this.gkbmh = gkbmh == null ? null : gkbmh.trim();
    }

    /**
     * 获取报考科目
     *
     * @return bkmajor - 报考科目
     */
    public String getBkmajor() {
        return bkmajor;
    }

    /**
     * 设置报考科目
     *
     * @param bkmajor 报考科目
     */
    public void setBkmajor(String bkmajor) {
        this.bkmajor = bkmajor == null ? null : bkmajor.trim();
    }

    /**
     * 获取报考专业的代码
     *
     * @return mcode - 报考专业的代码
     */
    public String getMcode() {
        return mcode;
    }

    /**
     * 设置报考专业的代码
     *
     * @param mcode 报考专业的代码
     */
    public void setMcode(String mcode) {
        this.mcode = mcode == null ? null : mcode.trim();
    }

    /**
     * 获取记录
     *
     * @return jl - 记录
     */
    public String getJl() {
        return jl;
    }

    /**
     * 设置记录
     *
     * @param jl 记录
     */
    public void setJl(String jl) {
        this.jl = jl == null ? null : jl.trim();
    }

    /**
     * 获取退出
     *
     * @return tc - 退出
     */
    public String getTc() {
        return tc;
    }

    /**
     * 设置退出
     *
     * @param tc 退出
     */
    public void setTc(String tc) {
        this.tc = tc == null ? null : tc.trim();
    }

    /**
     * 获取联系地址
     *
     * @return lxaddress - 联系地址
     */
    public String getLxaddress() {
        return lxaddress;
    }

    /**
     * 设置联系地址
     *
     * @param lxaddress 联系地址
     */
    public void setLxaddress(String lxaddress) {
        this.lxaddress = lxaddress == null ? null : lxaddress.trim();
    }

    /**
     * 获取邮编
     *
     * @return yb - 邮编
     */
    public String getYb() {
        return yb;
    }

    /**
     * 设置邮编
     *
     * @param yb 邮编
     */
    public void setYb(String yb) {
        this.yb = yb == null ? null : yb.trim();
    }

    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 获取时间
     *
     * @return sj - 时间
     */
    public String getSj() {
        return sj;
    }

    /**
     * 设置时间
     *
     * @param sj 时间
     */
    public void setSj(String sj) {
        this.sj = sj == null ? null : sj.trim();
    }

    /**
     * 获取准考证号
     *
     * @return zkzh - 准考证号
     */
    public String getZkzh() {
        return zkzh;
    }

    /**
     * 设置准考证号
     *
     * @param zkzh 准考证号
     */
    public void setZkzh(String zkzh) {
        this.zkzh = zkzh == null ? null : zkzh.trim();
    }

    /**
     * 获取省份
     *
     * @return sf - 省份
     */
    public String getSf() {
        return sf;
    }

    /**
     * 设置省份
     *
     * @param sf 省份
     */
    public void setSf(String sf) {
        this.sf = sf == null ? null : sf.trim();
    }

    /**
     * 获取创建时间
     *
     * @return creat_time - 创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 设置创建时间
     *
     * @param creatTime 创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取最近访问时间
     *
     * @return last_login_time - 最近访问时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最近访问时间
     *
     * @param lastLoginTime 最近访问时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取主题
     *
     * @return theme - 主题
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 设置主题
     *
     * @param theme 主题
     */
    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    /**
     * 获取头像
     *
     * @return avatar - 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取账号状态:0锁定 1有效
     *
     * @return account_status - 账号状态:0锁定 1有效
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     * 设置账号状态:0锁定 1有效
     *
     * @param accountStatus 账号状态:0锁定 1有效
     */
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}