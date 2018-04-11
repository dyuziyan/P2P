/**
 * 
 */
package com.wx.carloadmessage.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;


/**
 * 时间工具类
 * 
 * @author Administrator
 * 
 */
public class DateUtil {

	public final static DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public final static DateFormat YYYY_MM_DD_MM_HH = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	public final static DateFormat YYYY_MM_DD = new SimpleDateFormat(
			"yyyy-MM-dd");

	public final static DateFormat YYYYMMDDMMHHSSSSS = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	public final static DateFormat YYYYMMDDHHMMSS = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	public static final DateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

	public static final DateFormat YYYY = new SimpleDateFormat("yyyy");

	/**
	 * 时间转换为yyyy-MM-dd HH:mm:ss格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return YYYY_MM_DD_MM_HH_SS.format(date);
	}

	public static String dateToShortString(String dateString) {
		 String shortTimeString= "";
		
		try {
			Date date = YYYY_MM_DD.parse(dateString);
			shortTimeString = YYYY_MM_DD.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return shortTimeString;
	}
	
	public static Date strToDate(String dateString) {
		Date date = null;
		try {
			date = YYYY_MM_DD_MM_HH_SS.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date strToDateYYYYMMDDMMHH(String dateString) {
		Date date = null;
		try {
			date = YYYY_MM_DD_MM_HH.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date strToYYMMDDDate(String dateString) {
		Date date = null;
		try {
			date = YYYY_MM_DD.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 计算两个时间之间相差的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long diffDays(Date startDate, Date endDate) {
		long days = 0;
		long start = startDate.getTime();

		long end = endDate.getTime();
		// 一天的毫秒数1000 * 60 * 60 * 24=86400000
		days = (end - start) / 86400000;
		return days;
	}

	/**
	 * 日期加上月数的时间
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date dateAddMonth(Date date, int month) {
		return add(date, Calendar.MONTH, month);
	}

	/**
	 * 日期加上天数的时间
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date dateAddDay(Date date, int day) {
		return add(date, Calendar.DAY_OF_YEAR, day);
	}

	/**
	 * 日期加上年数的时间
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date dateAddYear(Date date, int year) {
		return add(date, Calendar.YEAR, year);
	}

	/**
	 * 计算剩余时间 (多少天多少时多少分)
	 * 
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 */
	public static String remainDateToString(Date startDate, Date endDate) {
		StringBuilder result = new StringBuilder();
		if (endDate == null) {
			return "过期";
		}
		long times = endDate.getTime() - startDate.getTime();
		if (times < -1) {
			result.append("过期");
		} else {
			long temp = 1000 * 60 * 60 * 24;
			// 天数
			long d = times / temp;

			// 小时数
			times %= temp;
			temp /= 24;
			long m = times / temp;
			// 分钟数
			times %= temp;
			temp /= 60;
			long s = times / temp;

			result.append(d);
			result.append("天");
			result.append(m);
			result.append("小时");
			result.append(s);
			result.append("分");
		}
		return result.toString();
	}

	private static Date add(Date date, int type, int value) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, value);
		return calendar.getTime();
	}

	/**
	 * @MethodName: getLinkUrl
	 * @Param: DateUtil flag ： true 转换 false 不转换
	 * @Author: gang.lv
	 * @Date: 2013-5-8 下午02:52:44
	 * @Return:
	 * @Descb:
	 * @Throws:
	 */
	public static String getLinkUrl(boolean flag, String content, String id) {
		if (flag) {
			content = "<a href='finance.do?id=" + id + "'>" + content + "</a>";
		}
		return content;
	}

	/**
	 * 时间转换为时间戳
	 * 
	 * @param format
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static long getTimeCur(String format, String date)
			throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.parse(sf.format(date)).getTime();
	}

	/**
	 * 时间转换为时间戳
	 * 
	 * @param format
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static long getTimeCur(String format, Date date)
			throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.parse(sf.format(date)).getTime();
	}

	/**
	 * 将时间戳转为字符串
	 * 
	 * @param cc_time
	 * @return
	 */
	public static String getStrTime(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 时间转换为时间戳
	 * 
	 * @param format
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getTimeCurS(String format, Date date)
			throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return Convert.strToStr(sf.parse(sf.format(date)).getTime() + "", "");
	}

	public static String getTimeFormat(String time) {
		Date date = strToDate(time);
		return dateToString(date);
	}

	/**************************************************/
	/**
	 * 返回系统当前时间(精确到秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getOrderNum() {
		Date date = new Date();
		return YYYYMMDDHHMMSS.format(date);

	}

	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateFormatter() {
		Date date = new Date();
		return YYYY_MM_DD_MM_HH_SS.format(date);
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		return YYYYMMDD.format(date);
	}
	
	/**
	 * 获取系统明天的日期 格式：yyyyMMdd
	 * @return
	 */
	public static String getTomorrowDate()
	{
		Date date = new Date();
		return YYYY_MM_DD.format(add(date, Calendar.DAY_OF_YEAR, 1));
	}

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getThree() {
		Random rad = new Random();
		return rad.nextInt(900) + 100 + "";
	}

	/**
	 * 获取当月的第一天
	 * 
	 * @return
	 */
	public static String getMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		Calendar f = (Calendar) cal.clone();
		f.clear();
		f.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		f.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		String firstday = new SimpleDateFormat("yyyy-MM-dd")
				.format(f.getTime());
		firstday = firstday + " 00:00:00";
		return firstday;

	}

	/**
	 * 获取当月的最后一天
	 * 
	 * @return
	 */
	public static String getMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		Calendar l = (Calendar) cal.clone();
		l.clear();
		l.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		l.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		l.set(Calendar.MILLISECOND, -1);
		String lastday = new SimpleDateFormat("yyyy-MM-dd").format(l.getTime());
		lastday = lastday + " 23:59:59";
		return lastday;
	}

	/**
	 * 获取昨天日期
	 * 
	 * @return
	 */
	public static Date getYesterDay() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime();
	}
	/**
	 * 两个日期之前的时间差 （天）
	 * 不满足一天按一天计算
	 * */
	public static Integer twoDateDiff(Date addTime, Date endTime) {
		long time = endTime.getTime() - addTime.getTime();
		int day = (int)(time/(1000*60*60*24));
		if(time%(1000*60*60*24)>0){
			day++;
		}
		return day;
	}
	
	public static String changeEndTime(String endTime) {
		if (endTime != null && !endTime.equals("")) {
			String[] strs = endTime.split("-");
			// 结束日期往后移一天,否则某天0点以后的数据都不呈现
			Date date = new Date();// 取时间
			long time = Date.UTC(Convert.strToInt(strs[0], -1) - 1900, Convert
					.strToInt(strs[1], -1) - 1, Convert.strToInt(strs[2], -1),
					0, 0, 0);
			date.setTime(time);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			date = calendar.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.format(date);
		}
		return null;
	}
	
	public static Date changeStrToDate(String dateTime){
		if(dateTime != null){
			String[] strs = dateTime.split("-");
			int ind = strs[2].indexOf(" ");
			if(ind >= 0){
				strs[2] = strs[2].substring(0,ind+1);
			}
			Date date = new Date();// 取时间
			long time = date.UTC(Convert.strToInt(strs[0], -1) - 1900, Convert
					.strToInt(strs[1], -1) - 1, Convert.strToInt(strs[2].trim(), -1),
					0, 0, 0);
			date.setTime(time);
			return date;
		}
		return null;
	}
}
