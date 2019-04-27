package cn.gobyte.apply.pojo.user;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "cj")
public class userGrande {
    /**
     * 身份证号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 学号
     */
    private String xh;

    /**
     * 准考证号
     */
    private String zkzh;

    /**
     * 名字
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 查询成绩密码
     */
    private String password;

    /**
     * 报考专业
     */
    private String bkmajor;

    /**
     * 考试科目1
     */
    private String km1;

    /**
     * 科目1分数
     */
    private Float km1f;

    private String km1b;

    /**
     * 考试科目2
     */
    private String km2;

    /**
     * 考试科目2分数
     */
    private Float km2f;

    private String km2b;

    /**
     * 总分
     */
    private String total;

    /**
     * 是否录取
     */
    private String lq;

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
     * 获取学号
     *
     * @return xh - 学号
     */
    public String getXh() {
        return xh;
    }

    /**
     * 设置学号
     *
     * @param xh 学号
     */
    public void setXh(String xh) {
        this.xh = xh == null ? null : xh.trim();
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
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * 获取查询成绩密码
     *
     * @return password - 查询成绩密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置查询成绩密码
     *
     * @param password 查询成绩密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取报考专业
     *
     * @return bkmajor - 报考专业
     */
    public String getBkmajor() {
        return bkmajor;
    }

    /**
     * 设置报考专业
     *
     * @param bkmajor 报考专业
     */
    public void setBkmajor(String bkmajor) {
        this.bkmajor = bkmajor == null ? null : bkmajor.trim();
    }

    /**
     * 获取考试科目1
     *
     * @return km1 - 考试科目1
     */
    public String getKm1() {
        return km1;
    }

    /**
     * 设置考试科目1
     *
     * @param km1 考试科目1
     */
    public void setKm1(String km1) {
        this.km1 = km1 == null ? null : km1.trim();
    }

    /**
     * 获取科目1分数
     *
     * @return km1f - 科目1分数
     */
    public Float getKm1f() {
        return km1f;
    }

    /**
     * 设置科目1分数
     *
     * @param km1f 科目1分数
     */
    public void setKm1f(Float km1f) {
        this.km1f = km1f;
    }

    /**
     * @return km1b
     */
    public String getKm1b() {
        return km1b;
    }

    /**
     * @param km1b
     */
    public void setKm1b(String km1b) {
        this.km1b = km1b == null ? null : km1b.trim();
    }

    /**
     * 获取考试科目2
     *
     * @return km2 - 考试科目2
     */
    public String getKm2() {
        return km2;
    }

    /**
     * 设置考试科目2
     *
     * @param km2 考试科目2
     */
    public void setKm2(String km2) {
        this.km2 = km2 == null ? null : km2.trim();
    }

    /**
     * 获取考试科目2分数
     *
     * @return km2f - 考试科目2分数
     */
    public Float getKm2f() {
        return km2f;
    }

    /**
     * 设置考试科目2分数
     *
     * @param km2f 考试科目2分数
     */
    public void setKm2f(Float km2f) {
        this.km2f = km2f;
    }

    /**
     * @return km2b
     */
    public String getKm2b() {
        return km2b;
    }

    /**
     * @param km2b
     */
    public void setKm2b(String km2b) {
        this.km2b = km2b == null ? null : km2b.trim();
    }

    /**
     * 获取总分
     *
     * @return total - 总分
     */
    public String getTotal() {
        return total;
    }

    /**
     * 设置总分
     *
     * @param total 总分
     */
    public void setTotal(String total) {
        this.total = total == null ? null : total.trim();
    }

    /**
     * 获取是否录取
     *
     * @return lq - 是否录取
     */
    public String getLq() {
        return lq;
    }

    /**
     * 设置是否录取
     *
     * @param lq 是否录取
     */
    public void setLq(String lq) {
        this.lq = lq == null ? null : lq.trim();
    }

    @Override
    public String toString() {
        return "userGrande{" +
                "id='" + id + '\'' +
                ", xh='" + xh + '\'' +
                ", zkzh='" + zkzh + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", bkmajor='" + bkmajor + '\'' +
                ", km1='" + km1 + '\'' +
                ", km1f=" + km1f +
                ", km1b='" + km1b + '\'' +
                ", km2='" + km2 + '\'' +
                ", km2f=" + km2f +
                ", km2b='" + km2b + '\'' +
                ", total='" + total + '\'' +
                ", lq='" + lq + '\'' +
                '}';
    }
}