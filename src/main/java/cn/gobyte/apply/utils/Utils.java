package cn.gobyte.apply.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 */
public class Utils {

    private Utils() {

    }


    /**
     * TODO:简单判断是否为手机号
     *
     * @param phoneNo 电话号码
     * @return boolean：false不是；true是
     * @author shanLan misterchou@qq.com
     * @date 2019/4/11 18:53
     */
    public static boolean isPhoneNo(String phoneNo) {
        String regex = "[1]\\d{10}";
        if (StringUtils.isBlank(phoneNo)) {
            return false;
        } else {
            return phoneNo.matches(regex);
        }
    }

    /**
     * TODO: 判断是否为 AJAX 请求
     *
     * @param request 请求体
     * @return boolean: false不是；true是
     * @author shanLan misterchou@qq.com
     * @date 2019/4/11 19:01
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }

    /**
     * 校验邮箱格式;检查 email输入是否正确,正确的书写格式为 username@domain
     * true是通过；false未通过
     * @param value
     * @return
     */
    public static boolean checkEmail(String value, int length) {
        return value
                .matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
                && value.length() <= length;
    }

    public static boolean checkMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean checkTel(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-?[0-9]{5,10}$"); // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 验证年龄0-120
     *
     * @param value
     * @return
     */
    public static boolean checkAge(String value) {
        return value.matches("120|((1[0-1]|\\d)?\\d)");
    }

    /**
     * 检查中文名输 入是否正确
     *
     * @param value
     * @return
     */
    public static boolean checkChineseName(String value, int length) {
        return value.matches("^[\u4e00-\u9fa5]+/{1}")
                && value.length() <= length;
    }


    /**
     * 检查字符串是 否含有HTML标签
     *
     * @param value
     * @return
     */

    public static boolean checkHtmlTag(String value) {
        return value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");
    }

    /**
     * 检查URL是 否合法
     *
     * @param value
     * @return
     */
    public static boolean checkURL(String value) {
        return value.matches("[a-zA-z]+://[^\\s]*");
    }

    /**
     * 检查IP是否 合法
     *
     * @param value
     * @return
     */
    public static boolean checkIP(String value) {
        return value.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");
    }

    /**
     * 检查ID是否 合法，开头必须是大小写字母，其他位可以有大小写字符、数字、下划线
     *
     * @param value
     * @return
     */
    public static boolean checkID(String value) {
        return value.matches("[a-zA-Z][a-zA-Z0-9_]{4,15}{1");
    }

    /**
     * 检查QQ是否 合法，必须是数字，且首位不能为0，最长15位
     *
     * @param value
     * @return
     */

    public static boolean checkQQ(String value) {
        return value.matches("[1-9][0-9]{4,13}");
    }

    /**
     * 检查邮编是否 合法
     *
     * @param value
     * @return
     */
    public static boolean checkPostCode(String value) {
        return value.matches("[1-9]\\d{5}(?!\\d)");
    }

    /**
     * 检查身份证是 否合法,15位或18位
     *
     * @param value
     * @return
     */
    public static boolean checkIDCard(String value) {
        return value.matches("\\d{15}|\\d{18}");
    }

    /**
     * 检查输入是否 超出规定长度 Java教程:http://www.javaweb.cc
     *
     * @param length
     * @param value
     * @return
     */
    public static boolean checkLength(String value, int length) {
        return ((value == null || "".equals(value.trim())) ? 0 : value.length()) <= length;
    }

    /**
     * 检查是否为空 字符串,空：true,不空:false
     *
     * @param value
     * @return
     */
    public static boolean checkNull(String value) {
        return value == null || "".equals(value.trim());
    }
}
