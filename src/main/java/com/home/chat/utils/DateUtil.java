package com.home.chat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {

    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static String getDate(Long millNumber) {
        return new SimpleDateFormat(YYYYMMDD).format(new Date(millNumber));
    }

    public static Long getLongDate(Long millNumber){
        if(millNumber == null || millNumber.longValue() == 0){
            return 0L;
        }
        return Long.valueOf(getDate(millNumber));
    }

    public static long getCurrDate() {
        SimpleDateFormat sf = new SimpleDateFormat(YYYYMMDD);
        return Long.valueOf(sf.format(new Date()));
    }
    /**
     * 根据传入的数据格式格式化日期
     *
     * @param date
     * @param format
     * @return
     */
    public static String getDateFormat(Long date, String format) throws ParseException {

        SimpleDateFormat sf = new SimpleDateFormat(YYYYMMDD);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(sf.parse(date.toString()));
    }

}
