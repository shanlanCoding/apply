package cn.gobyte.apply.pojo.user;

import javax.persistence.Column;
import javax.persistence.Id;

public class Course {
    /**
     * 专业的代码
     */
    @Id
    private String cid;

    /**
     * 专业的名字
     */
    private String cname;

    /**
     * 考试科目1的名字
     */
    private String km1;

    /**
     * 考试科目1的时间
     */
    @Column(name = "km1_time")
    private String km1Time;

    /**
     * 考试科目2
     */
    private String km2;

    /**
     * 科目2考试时间
     */
    @Column(name = "km2_time")
    private String km2Time;

    /**
     * 考试科目3
     */
    private String km3;

    /**
     * 科目3考试时间
     */
    @Column(name = "km3_time")
    private String km3Time;

    private String km4;

    @Column(name = "km4_time")
    private String km4Time;

    /**
     * 获取专业的代码
     *
     * @return cid - 专业的代码
     */
    public String getCid() {
        return cid;
    }

    /**
     * 设置专业的代码
     *
     * @param cid 专业的代码
     */
    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    /**
     * 获取专业的名字
     *
     * @return cname - 专业的名字
     */
    public String getCname() {
        return cname;
    }

    /**
     * 设置专业的名字
     *
     * @param cname 专业的名字
     */
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * 获取考试科目1的名字
     *
     * @return km1 - 考试科目1的名字
     */
    public String getKm1() {
        return km1;
    }

    /**
     * 设置考试科目1的名字
     *
     * @param km1 考试科目1的名字
     */
    public void setKm1(String km1) {
        this.km1 = km1 == null ? null : km1.trim();
    }

    /**
     * 获取考试科目1的时间
     *
     * @return km1_time - 考试科目1的时间
     */
    public String getKm1Time() {
        return km1Time;
    }

    /**
     * 设置考试科目1的时间
     *
     * @param km1Time 考试科目1的时间
     */
    public void setKm1Time(String km1Time) {
        this.km1Time = km1Time == null ? null : km1Time.trim();
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
     * 获取科目2考试时间
     *
     * @return km2_time - 科目2考试时间
     */
    public String getKm2Time() {
        return km2Time;
    }

    /**
     * 设置科目2考试时间
     *
     * @param km2Time 科目2考试时间
     */
    public void setKm2Time(String km2Time) {
        this.km2Time = km2Time == null ? null : km2Time.trim();
    }

    /**
     * 获取考试科目3
     *
     * @return km3 - 考试科目3
     */
    public String getKm3() {
        return km3;
    }

    /**
     * 设置考试科目3
     *
     * @param km3 考试科目3
     */
    public void setKm3(String km3) {
        this.km3 = km3 == null ? null : km3.trim();
    }

    /**
     * 获取科目3考试时间
     *
     * @return km3_time - 科目3考试时间
     */
    public String getKm3Time() {
        return km3Time;
    }

    /**
     * 设置科目3考试时间
     *
     * @param km3Time 科目3考试时间
     */
    public void setKm3Time(String km3Time) {
        this.km3Time = km3Time == null ? null : km3Time.trim();
    }

    /**
     * @return km4
     */
    public String getKm4() {
        return km4;
    }

    /**
     * @param km4
     */
    public void setKm4(String km4) {
        this.km4 = km4 == null ? null : km4.trim();
    }

    /**
     * @return km4_time
     */
    public String getKm4Time() {
        return km4Time;
    }

    /**
     * @param km4Time
     */
    public void setKm4Time(String km4Time) {
        this.km4Time = km4Time == null ? null : km4Time.trim();
    }
}