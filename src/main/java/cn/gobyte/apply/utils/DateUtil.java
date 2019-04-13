package cn.gobyte.apply.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private DateUtil() {

    }

    /**
     * 时间格式化
     */
    public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * TODO: 格式化时间
     *
     * @param date           需要格式化的实际
     * @param dateFormatType 格式；例如：yyyy-MM-dd HH:mm:ss
     * @return java.lang.String:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/14 2:28
     */
    public static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simformat = new SimpleDateFormat(dateFormatType);
        return simformat.format(date);
    }

    public static String formatCSTTime(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date d = sdf.parse(date);
        return DateUtil.getDateFormat(d, format);
    }
}
