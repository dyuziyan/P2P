package my.comp.lang;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	/** 节假日列表 */
	public static List<Calendar> holidayList = new ArrayList<Calendar>();

	public static final String DAY_FORMAT = "yyyy-MM-dd";

	// 一些时间格式串
	public final static String DATE_FULL = "yyyy-MM-dd HH:mm:ss";

	public static final String formatDate(Date date) {
		return formatDate(date, DAY_FORMAT);
	}

	/**
	 * 默认日期格式yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static final Date parseDate(String date) {
		return parseDate(date, DAY_FORMAT);
	}

	public static final Date parseDate(String date, String pattern) {
		try {
			return org.apache.commons.lang.time.DateUtils.parseDate(date, new String[] { pattern });
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static final Date parseDate(String date, String[] patterns) {
		try {
			return org.apache.commons.lang.time.DateUtils.parseDate(date, patterns);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static final String formatDate(Date date, String format) {

		SimpleDateFormat fmt = new SimpleDateFormat(DAY_FORMAT);

		if (format == null) {
			return fmt.format(date);

		} else {
			fmt = new SimpleDateFormat(format);
		}

		return fmt.format(date);
	}

	/**
	 * 日期格式化得到Date
	 * 
	 * @deprecated
	 * @see #truncateToDay(Date)
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static final Date formatDateForDate(Date date, String format) {

		SimpleDateFormat fmt = new SimpleDateFormat(DAY_FORMAT);

		if (format == null) {
			return parseDate(fmt.format(date), DAY_FORMAT);

		} else {
			fmt = new SimpleDateFormat(format);
		}
		return parseDate(fmt.format(date), format);
	}

	public static final String formatSystime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static Date addDay(Date date, int day) {

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		cal.add(Calendar.DAY_OF_MONTH, day);

		return cal.getTime();

	}

	public static Date addMonth(Date date, int month) {

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		cal.add(Calendar.MONTH, month);

		return cal.getTime();

	}

	public static Date Long2Date(Long t) {

		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(t);

		return ca.getTime();

	}

	/**
	 * 日期格式化
	 * 
	 * @param c
	 * @param pattern
	 * @return
	 */
	public static String format(Calendar c, String pattern) {
		Calendar calendar = null;
		if (c != null) {
			calendar = c;
		} else {
			calendar = Calendar.getInstance();
		}
		if (pattern == null || pattern.equals("")) {
			pattern = "yyyy年MM月dd日 HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(calendar.getTime());
	}

	/**
	 * 获取两个时间差
	 * 
	 * @deprecated
	 * @param beginDate
	 * @param endDate
	 * @return 相差天数
	 */
	public static Long getDatesDistanceForDay(Date beginDate, Date endDate) {
		beginDate = DateUtils.formatDateForDate(beginDate, DateUtils.DAY_FORMAT);
		endDate = DateUtils.formatDateForDate(endDate, DateUtils.DAY_FORMAT);
		return (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @deprecated 不再使用
	 * @see #parseDate(String)
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str, String dateFormate) {
		SimpleDateFormat format;
		if (dateFormate == null || "".equals(dateFormate)) {
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			format = new SimpleDateFormat(dateFormate);
		}
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取两个时间相差的月份和天数：小于一个月返回0月
	 * 
	 * @deprecated
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return
	 */
	public static DateDistanceVo getDatesDistance(Date beginDate, Date endDate) {
		DateDistanceVo res = new DateDistanceVo();
		beginDate = DateUtils.formatDateForDate(beginDate, DateUtils.DAY_FORMAT);
		endDate = DateUtils.formatDateForDate(endDate, DateUtils.DAY_FORMAT);
		beginDate = addMonth(beginDate, 1);
		int month = 0;
		while (beginDate.compareTo(endDate) == -1 || beginDate.compareTo(endDate) == 0) {// 开始日期小于或者结束日期
			month++;
			beginDate = addMonth(beginDate, 1);
		}
		beginDate = addMonth(beginDate, -1);
		Long day = getDatesDistanceForDay(beginDate, endDate);
		res.setDayDistance(day.intValue());
		res.setMonthDistance(month);
		return res;
	}

	/**
	 * 根据开始和结束时间，判断当前时间是在整月数据中还是在剩余天数中：如2015.1.1到2015.5.20 有4个整月数，19天于天数，假如今天是2015.3.4就是在整月数中
	 * 
	 * @deprecated
	 * @param beginDate
	 * @param endDate
	 * @return 在正月数中返回0，在剩余天数中返回1
	 */
	public static int getWhereExits(Date beginDate, Date endDate) {
		beginDate = DateUtils.formatDateForDate(beginDate, DateUtils.DAY_FORMAT);
		endDate = DateUtils.formatDateForDate(endDate, DateUtils.DAY_FORMAT);
		DateDistanceVo dateDistance = getDatesDistance(beginDate, endDate);
		if (dateDistance.getMonthDistance() == 0) return 1;// 处于剩余天数中
		int month = dateDistance.getMonthDistance();
		Date maxMonthDate = addMonth(beginDate, month);// 开始日期加上正月数
		// 判断开始日期加上正月数和当前时间
		int compare = maxMonthDate.compareTo(DateUtils.formatDateForDate(new Date(), DateUtils.DAY_FORMAT));
		if (compare == 1 || compare == 0) {// 开始日期加上正月数大于等于当前时间，处于整月数中
			return 0;
		} else {
			return 1;
		}

	}

	public static int diffDate(Date d1, Date d2) {
		if ((d1 == null) || (d2 == null)) return 0;

		Calendar cal = Calendar.getInstance();

		int zoneoffset = cal.get(Calendar.ZONE_OFFSET);
		int dstoffset = cal.get(Calendar.DST_OFFSET);

		long dl1 = d1.getTime() + zoneoffset + dstoffset;
		long dl2 = d2.getTime() + zoneoffset + dstoffset;

		int intDaysFirst = (int) (dl1 / (60 * 60 * 1000 * 24)); // 60*60*1000
		int intDaysSecond = (int) (dl2 / (60 * 60 * 1000 * 24));

		return intDaysFirst > intDaysSecond ? intDaysFirst - intDaysSecond : intDaysSecond - intDaysFirst;
	}

	/**
	 * 截断小时分钟秒，精确到天
	 * 
	 * @param date
	 * @return
	 */
	public static Date truncateToDay(Date date) {
		return DateUtils.truncate(date, Calendar.DATE);
	}

	public static Date transMillis2Date(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	public static Double daysBetween(Date start, Date end) {
		BigDecimal num1 = BigDecimal.valueOf(end.getTime());
		BigDecimal num2 = BigDecimal.valueOf(start.getTime());
		return num1.subtract(num2).divide(BigDecimal.valueOf(24 * 60 * 60 * 1000), 2, RoundingMode.HALF_UP).doubleValue();
	}

	public static int daysBetween2(Date start, Date end) {
		BigDecimal num1 = BigDecimal.valueOf(end.getTime());
		BigDecimal num2 = BigDecimal.valueOf(start.getTime());
		return num1.subtract(num2).divide(BigDecimal.valueOf(24 * 60 * 60 * 1000), 0, RoundingMode.HALF_UP).intValue();
	}

	/**
	 * 获取月份差。向上取整
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getCeilMonths(Date $beginDate, Date $endDate) {
		Date beginDate = DateUtils.truncateToDay($beginDate);
		Date endDate = DateUtils.truncateToDay($endDate);

		beginDate = addMonth(beginDate, 1);

		int month = 0;
		while (beginDate.compareTo(endDate) == -1 || beginDate.compareTo(endDate) == 0) {// 开始日期小于或者结束日期
			month++;
			beginDate = addMonth(beginDate, 1);
		}

		beginDate = addMonth(beginDate, -1);

		if (beginDate.before(endDate) && DateUtils.daysBetween2(beginDate, endDate) > 0) return month + 1;

		return month;
	}

	public static void main(String[] args) {
		DateDistanceVo vo = getDatesDistance(parseDate("2015-01-28", DateUtils.DAY_FORMAT), parseDate("2015-05-27", DateUtils.DAY_FORMAT));
		System.out.println(vo.getDayDistance());

		System.out.println(DateUtils.getSunday(new Date()));
		System.out.println(DateUtils.getSaturday(new Date()));
	}

	// 获取当前的结束时间,精确到秒
	public static Date setEndOfDay(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 计算前n个工作日或者后n个工作日
	 * 
	 * @param src日期
	 *            (源)
	 * @param addOrMinu
	 *            向前传-1，向后传1
	 * @param adddays
	 *            要加的天数
	 */
	public static Date addDateByWorkDay(Date date, int days, int addOrMinu) {

		date = formatDateForDate(date, null);
		if (addOrMinu == 1 || addOrMinu == -1) {

		} else {
			return null;
		}
		boolean holidayFlag = false;
		Calendar src = Calendar.getInstance();
		src.setTime(date);
		for (int i = 0; i < days; i++) {
			// 把源日期加一天
			src.add(Calendar.DAY_OF_MONTH, addOrMinu);
			holidayFlag = checkHoliday(src);
			if (holidayFlag) {
				i--;
			}
		}
		return src.getTime();
	}

	/**
	 * 校验指定的日期是否在节日列表中 具体节日包含哪些,可以在HolidayMap中修改
	 * 
	 * @param src
	 *            要校验的日期(源)
	 */
	public static boolean checkHoliday(Calendar src) {
		boolean result = false;
		// 先检查是否是周六周日
		if (src.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || src.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}

		for (Calendar c : holidayList) {
			if (src.get(Calendar.MONTH) == c.get(Calendar.MONTH) && src.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH)) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * Date 转化Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar calendar2Date(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 判断指定时间是否在某时间范围内
	 * 
	 * @param date
	 *            指定时间
	 * @param begin
	 *            开始时间
	 * @param includeBegin
	 *            是否包括开始时间
	 * @param end
	 *            结束时间
	 * @param includeEnd
	 *            是否包括结束时间
	 * @param field
	 *            精确度，精确到日，小时，分钟，秒
	 * @return
	 */
	public static boolean between(Date date, Date begin, boolean includeBegin, Date end, boolean includeEnd, int field) {
		Date $date = DateUtils.truncate(date, field);
		Date $begin = DateUtils.truncate(begin, field);
		Date $end = DateUtils.truncate(end, field);
		boolean bool = $begin.before($date) || (includeBegin && !$begin.after($date));
		bool = bool && ($end.after($date) || (includeEnd && !$end.before($date)));
		return bool;
	}

	/**
	 * 判断指定日期是否在某日期范围内
	 * 
	 * @param date
	 *            指定日期
	 * @param begin
	 *            开始日期，包括开始日期
	 * @param end
	 *            结束日期，包括结束日期
	 * @return begin &lt;= date &lt;= end 返回true
	 */
	public static boolean between(Date date, Date begin, Date end) {
		return between(date, begin, true, end, true, Calendar.DATE);
	}

	/**
	 * 判断指定日期是否在某日期范围内
	 * 
	 * @param date
	 *            指定日期
	 * @param begin
	 *            开始日期，包括开始日期
	 * @param end
	 *            结束日期，不包括结束日期
	 * @param includeEnd
	 *            是否包括结束日期
	 * @return
	 */
	public static boolean between(Date date, Date begin, Date end, boolean includeEnd) {
		return between(date, begin, true, end, includeEnd, Calendar.DATE);
	}

	public static boolean before(Date earlier, Date later, boolean equals, int field) {
		Date $earlier = DateUtils.truncate(earlier, field);
		Date $later = DateUtils.truncate(later, field);
		return $earlier.before($later) || (equals && !$earlier.after($later));
	}

	public static int[] paseDate(Date date) {

		String[] s = formatDate(date, "HH:mm:ss").split(":");
		int[] a = new int[3];
		a[0] = Integer.parseInt(s[0]);
		a[1] = Integer.parseInt(s[1]);
		a[2] = Integer.parseInt(s[2]);

		return a;
	}

	/**
	 * 得到上月时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastMonthDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 得到指定日期月初日期
	 * 
	 * @return
	 */
	public static Date getMonthStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 得到指定日期月末日期
	 * 
	 * @return
	 */
	public static Date getMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	/**
	 * 得到指定日期周日时间(周第一天)
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSunday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cal.getTime();
	}

	/**
	 * 得到指定日期周六时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSaturday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		return cal.getTime();
	}

	/**
	 * 获取当前时间的时分秒
	 * 
	 * @return
	 */
	public static int[] getIntTime() {

		int[] ret = new int[3];
		Calendar cal = Calendar.getInstance();
		ret[0] = cal.get(Calendar.HOUR_OF_DAY);
		ret[1] = cal.get(Calendar.MINUTE);
		ret[2] = cal.get(Calendar.SECOND);
		return ret;
	}

	public static String formatDate(Date date, String datePattern, String timePattern) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (c.get(Calendar.HOUR) == 0 && c.get(Calendar.MINUTE) == 0 && c.get(Calendar.SECOND) == 0) {
			return DateUtils.formatDate(c.getTime(), datePattern);
		} else {
			return DateUtils.formatDate(c.getTime(), timePattern);
		}
	}

	public static String formatCurrentDateTime() {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String timeString = dataFormat.format(date);
		return timeString;
	}

	public static Date getInitializedDate() {
		return parseDate("1900-01-01 00:00:00", DATE_FULL);
	}
	
	/**
	 * 得到两个日期间隔的天数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getBetweenDays(Date d1, Date d2) {
		int betweenDays = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c1 = c2;
			c2 = Calendar.getInstance();
			c2.setTime(d1);
		}
		Calendar date = (Calendar) c1.clone();
		while (date.before(c2)) {
			date.add(Calendar.DAY_OF_MONTH, 1);
			betweenDays++ ;
		}
		return betweenDays;
	}
	
	/**
	 * 得到星期几
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date){
		if(date == null){
			return null;
		}
		try {
			String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if(week_index<0){
				week_index = 0;
			} 
			return weeks[week_index];
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 字符串转换成时间类型
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date strToDate(String str,String format){
		if(StringUtils.isBlank(str) || StringUtils.isBlank(format)){
			return null;
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.parse(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 得到当前日期的字符串--yyyyMMdd
	 */
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(date);
		return dateString;
	}

}
