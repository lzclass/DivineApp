package com.liuzhao.divineapp.utils;

import com.liuzhao.divineapp.base.BaZiConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 计算八字工具类
 */

public class BaZiUtils implements BaZiConstants {
    private int year;
    private int month;
    private int day;
    private boolean leap;
    private Date baseDate = null;
    private Calendar cal;
    private static SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 传出y年m月d日对应的农历.
     * yearCyl3:农历年与1864的相差数 ?
     * monCyl4:从1900年1月31日以来,闰月数
     * dayCyl5:与1900年1月31日相差的天数,再加40 ?
     *
     * @param cal
     * @return
     */
    public void BaZi(Calendar cal) {
        this.cal = cal;
        int yearCyl, monCyl, dayCyl;
        int leapMonth = 0;

        try {
            baseDate = chineseDateFormat.parse("1900-1-31");
        } catch (ParseException e) {
            e.printStackTrace(); //To change body of catch statement use Options | File Templates.
        }
        //求出和1900年1月31日相差的天数
        int offset = (int) ((cal.getTime().getTime() - baseDate.getTime()) / 86400000L);
        dayCyl = offset + 40;
        monCyl = 14;
        //用offset减去每农历年的天数,计算当天是农历第几天，i最终结果是农历的年份，offset是当年的第几天
        int iYear, daysOfYear = 0;
        for (iYear = 1900; iYear < 2050 && offset > 0; iYear++) {
            daysOfYear = yearDays(iYear);
            offset -= daysOfYear;
            monCyl += 12;
        }
        if (offset < 0) {
            offset += daysOfYear;
            iYear--;
            monCyl -= 12;
        }
        //农历年份
        year = iYear;
        yearCyl = iYear - 1864;
        leapMonth = leapMonth(iYear); //闰哪个月,1-12
        leap = false;
//用当年的天数offset,逐个减去每月（农历）的天数，求出当天是本月的第几天
        int iMonth, daysOfMonth = 0;
        for (iMonth = 1; iMonth < 13 && offset > 0; iMonth++) {
//闰月
            if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap) {
                --iMonth;
                leap = true;
                daysOfMonth = leapDays(year);
            } else {
                daysOfMonth = monthDays(year, iMonth);
            }
            offset -= daysOfMonth;
//解除闰月
            if (leap && iMonth == (leapMonth + 1)) {
                leap = false;
            }
            if (!leap) {
                monCyl++;
            }
        }
//offset为0时，并且刚才计算的月份是闰月，要校正
        if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
            if (leap) {
                leap = false;
            } else {
                leap = true;
                --iMonth;
                --monCyl;
            }
        }
//offset小于0时，也要校正
        if (offset < 0) {
            offset += daysOfMonth;
            --iMonth;
            --monCyl;
        }
        month = iMonth;
        day = offset + 1;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return chineseNumber[month - 1];
    }

    /**
     * @return the year
     */
    public String getYear() {
        return getYearStr(year);
    }

    /**
     * @return the year
     */
    public int getnumberYear() {
        return year;
    }

    /**
     * @return the year
     */
    public int getnumbermonth() {
        return month;
    }

    /**
     * @return the year 返回年的顺序，子0 丑2
     */
    public int getYearindex() {
        return (year - 4) % 12;
    }

    /**
     * @return the day
     */
    public String getDay() {
        return getChinaDayString(day);
    }

    /**
     * @param hour 这里的时间范围是1-12，具体几点到几点是子时、丑时请参考相关文档
     *             具体的选择如下 "子：1", "丑：2", "寅：3", "卯：4", "辰：5", "巳：6", "午：7", "未：8", "申：9", "酉：10", "戌：11", "亥：12"
     * @author kongqz
     */
    public String getYearTianGanDiZhi(int hour) {
        //1864年是甲子年，每隔六十年一个甲子
        int idx = (year - 1864) % 60;
        //没有过春节的话那么年还算上一年的，此处求的年份的干支
        String y = jiazhi[idx];

        String m = "";
        String d = "";
        String h = "";
        idx = idx % 5;
        int idxm = 0;
        /**
         * 年上起月
         * 甲己之年丙作首，乙庚之岁戊为头，
         * 丙辛必定寻庚起，丁壬壬位顺行流，
         * 更有戊癸何方觅，甲寅之上好追求。
         */
        idxm = (idx + 1) * 2;
        if (idxm == 10) idxm = 0;
        //求的月份的干支
        m = TianGan[(idxm + month - 1) % 10] + DiZhi[(month + 2 - 1) % 12];


        /*求出和1900年1月31日甲辰日相差的天数
         * 甲辰日是第四十天
        */
        int offset = (int) ((cal.getTime().getTime() - baseDate.getTime()) / 86400000L);
        offset = (offset + 40) % 60;
        //求的日的干支
        d = jiazhi[offset];

        /**
         * 日上起时
         * 甲己还生甲，乙庚丙作初，
         * 丙辛从戊起，丁壬庚子居，
         * 戊癸何方发，壬子是真途。
         */

        offset = (offset % 5) * 2;
        //求得时辰的干支
        h = TianGan[(offset + hour) % 10] + DiZhi[hour];
        //在此处输出我们的年月日时的天干地支
        return y + "," + m + "," + d + "," + h;
    }

    public String getShichenFromDay(int offset) {

        return null;
    }

    //====== 传回农历 y年的总天数
    final private static int yearDays(int y) {
        int i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) {
            if ((lunarInfo[y - 1900] & i) != 0) {
                sum += 1;
            }
        }
        return (sum + leapDays(y));
    }

    //====== 传回农历 y年闰月的天数
    final private static int leapDays(int y) {
        if (leapMonth(y) != 0) {
            if ((lunarInfo[y - 1900] & 0x10000) != 0) {
                return 30;
            } else {
                return 29;
            }
        } else {
            return 0;
        }
    }

    //====== 传回农历 y年闰哪个月 1-12 , 没闰传回 0
    final private static int leapMonth(int y) {
        return (int) (lunarInfo[y - 1900] & 0xf);
    }

    //====== 传回农历 y年m月的总天数
    final private static int monthDays(int y, int m) {
        if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0) {
            return 29;
        } else {
            return 30;
        }
    }

    /***
     * @return 传回农历 y年的生肖
     */
    final public String animalsYear() {

        return animalsName[(year - 4) % 12];
    }

    //====== 传入 月日的offset 传回干支, 0=甲子
    final private static String cyclicalm(int num) {
        return (TianGan[num % 10] + DiZhi[num % 12]);
    }

    //====== 传入 offset 传回干支, 0=甲子
    final public String cyclical() {
        int num = year - 1900 + 36;
        return (cyclicalm(num));
    }

    public static String getChinaDayString(int day) {
        String chineseTen[] = {"初", "十", "廿", "卅"};
        int n = day % 10 == 0 ? 9 : day % 10 - 1;
        if (day > 30) {
            return "";
        }
        if (day == 10) {
            return "初十";
        } else {
            return chineseTen[day / 10] + chineseNumber[n];
        }
    }

    public String toString() {
        return getYearStr(year) + "年" + (leap ? "闰" : "") + chineseNumber[month - 1] + "月" + getChinaDayString(day);
    }

    public String getYearStr(int year) {
        String[] chineseword = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String ys = "";
        int index = year / 1000;
        ys += chineseword[index];
        year = year % 1000;
        index = year / 100;
        ys += chineseword[index];
        year = year % 100;
        index = year / 10;
        ys += chineseword[index];
        year = year % 10;
        index = year;
        ys += chineseword[index];
        return ys;
    }

    public static String getSixtyDay() {
        String temp = "";
        for (int i = 0; i < 60; i++) {
            temp += ",/" + cyclicalm(i) + "/";
        }
        return temp;
    }
}
