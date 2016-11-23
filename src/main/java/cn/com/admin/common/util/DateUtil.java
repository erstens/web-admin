package cn.com.admin.common.util;

import org.joda.time.DateTime;

/**
 * date相关
 * @need joda
 * Created by wang'ao on 2016/11/10 0010.
 */
public class DateUtil {
    public static final String FORMAT_DAY = "yyyy-MM-dd" ;
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss" ;

    /**
     * 现在yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String formatNow() {
        return new DateTime().toString(FORMAT_DATETIME) ;
    }

    public static void main(String[] args) {
        System.out.printf(formatNow());
    }
}
