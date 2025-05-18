package com.company.project.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DatetimeUtil {

	// 日志器
	private static final Logger logger = Logger.getLogger(DatetimeUtil.class);

	// public static SimpleDateFormat SHORT_SDF = new
	// SimpleDateFormat("yyyy-MM-dd");
	// public static SimpleDateFormat LONG_SDF = new SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss");

	/**
	 * 获取上个月月份
	 * @return
	 */
	public static final String getLastMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		// 设置为当前时间
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,-1);
		// 设置为上一个月
		//calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		date = calendar.getTime();
		return format.format(date);
	}

	/**
	 * 得到指定时间的后几天
	 * @return
	 */
	public static String geDateAfter(Date date,int day) {
		//获取当前日期
		//将时间格式化成yyyy-MM-dd HH:mm:ss的格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//创建Calendar实例
		Calendar cal = Calendar.getInstance();
		//设置当前时间
		cal.setTime(date);
		//同理增加一天的方法：
		cal.add(Calendar.DATE, day);
		return format.format(cal.getTime());
	}

	/**
	 * 得到当前时间的后几天
	 * @return
	 */
	public static String getNewDateAfter(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat(Constants.DATE_PATTERN);
		String format = df.format(date);
		return format;
	}

	/**
	 * 获取当前时间的前几个月、后几个月的当天时间
	 * @return
	 */
	public static String getMonthBefore(int month) {
		SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_PATTERN);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, month);
		Date m = c.getTime();
		String mon = format.format(m);
		return mon;
	}

	/**
	 * 获取两个时间之间的每一天
	 *
	 * @param bigtimeStr
	 * @param endTimeStr
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getDays(String bigtimeStr, String endTimeStr) throws ParseException {
		Date bigtime = new SimpleDateFormat(Constants.DATE_PATTERN).parse(bigtimeStr + " 00:00:00");
		Date endtime = new SimpleDateFormat(Constants.DATE_PATTERN).parse(endTimeStr + " 00:00:00");
		//定义一个接受时间的集合
		List<Date> lDate = new ArrayList<>();
		lDate.add(bigtime);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(bigtime);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(endtime);
		// 测试此日期是否在指定日期之后
		while (endtime.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		List<String> datas = new LinkedList<>();
		for (Date date : lDate) {
			datas.add(new SimpleDateFormat(Constants.DATE_PATTERN_YMD).format(date));
		}
		return datas;
	}

	//根据日期取得星期几
	public static String getWeek(Date date){
		String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		}
		return weeks[week_index];
	}

	/**
	 * 时间戳转换成日期格式字符串
	 * @param seconds 精确到秒的字符串
	 * @param format
	 * @return
	 */
	public static String timeStamp2Date(String seconds,String format) {
		if(seconds == null || seconds.isEmpty() || "null".equals(seconds)){
			return "";
		}
		if(format == null || format.isEmpty()){
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.parseLong(seconds+"000")));
	}

    /**
     * 日期格式字符串转换成时间戳
	 * @param dat
     * @return
     */
	public static Date StringToDate(String dat,String format){
		if(dat == null || dat.isEmpty() || "null".equals(dat)){
			return null;
		}
		if(format == null || format.isEmpty()){
			format = "yyyy-MM-dd HH:mm:ss";
		}

		SimpleDateFormat press = new SimpleDateFormat(format);
		Date date = null;
		try {
			date= press.parse(dat);
		} catch (Exception e) {
			logger.error("String 转换 Date 异常",e);
		}
		return date;
	}

	/**
	 * 获取明天的日期（年月日时分秒）
	 * @return
	 */
	public static String tomorrowSecond(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,+1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
		return yesterday;
	}

	/**
	 * 获取昨天的日期（年月日时分秒）
	 * @return
	 */
	public static String yesterSecond(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
		return yesterday;
	}

	/**
	 * 获取昨天的日期（年月日）
	 * @return
	 */
	public static String yesterDay(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return yesterday;
	}

	/**
	 * 取得当前时间戳（精确到秒）
	 * @return
	 */
	public static String timeStamp(){
		long time = System.currentTimeMillis();
		String t = String.valueOf(time/1000);
		return t;
	}

	/*
	 * 将时间转换为时间戳
	 */
	public static String dateToStamp(String s)  {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = simpleDateFormat.parse(s);
		} catch (ParseException e) {
			logger.error("String时间 转换 时间戳 异常",e);
		}
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}

	public static String DateTOString(Date dat){
		SimpleDateFormat press = new SimpleDateFormat("yyyy-MM-dd");
		String dateString=null;
		try {
			dateString= press.format(dat);
		} catch (Exception e) {
			logger.error("Date 转换 String 异常",e);
		}
		return dateString;
	}

	/**
	 * 将字符串转换成日期格式（包含时分秒）
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return
	 */
	public static Date getLongDateByStr(String str) {
		Date result = null;
		if (StringUtils.isEmpty(str)) {
			return result;
		}
		SimpleDateFormat LONG_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			result = LONG_SDF.parse(str);
		} catch (Exception e) {
			logger.error("日期转换失败" + str, e);
		}

		return result;
	}


	/**
	 * 将字符串转换成日期格式（不包含时分秒）
	 *
	 * @param str
	 *            要转换的字符串
	 * @return
	 */
	public static Date getShortDateByStrChinese(String str) {
		Date result = null;
		if (StringUtils.isEmpty(str)) {
			return result;
		}
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat("yyyy年MM月");
		try {
			result = SHORT_SDF.parse(str);
		} catch (Exception e) {
			logger.error("日期转换失败" + str, e);
		}

		return result;
	}


	/**
	 * 将字符串转换成日期格式（不包含时分秒）
	 *
	 * @param str
	 *            要转换的字符串
	 * @return
	 */
	public static Date getShortDateByStrSlash(String str) {
		Date result = null;
		if (StringUtils.isEmpty(str)) {
			return result;
		}
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat("yyyy/MM/dd");
		try {
			result = SHORT_SDF.parse(str);
		} catch (Exception e) {
			logger.error("日期转换失败" + str, e);
		}

		return result;
	}


	/**
	 * 将字符串转换成日期格式（不包含时分秒）
	 *
	 * @param str
	 *            要转换的字符串
	 * @return
	 */
	public static Date getShortDateByStrSlashAndMinute(String str) {
		Date result = null;
		if (StringUtils.isEmpty(str)) {
			return result;
		}
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			result = SHORT_SDF.parse(str);
		} catch (Exception e) {
			logger.error("日期转换失败" + str, e);
		}

		return result;
	}

	/**
	 * 将字符串转换成日期格式（不包含时分秒）
	 *
	 * @param str
	 *            要转换的字符串
	 * @return
	 */
	public static Date getShortDateByStr(String str) {
		Date result = null;
		if (StringUtils.isEmpty(str)) {
			return result;
		}
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat("yyyy-MM-dd");
		try {
			result = SHORT_SDF.parse(str);
		} catch (Exception e) {
			logger.error("日期转换失败" + str, e);
		}

		return result;
	}

	/**
	 * 将字符串转换成日期格式（不包含时分秒,日）
	 *
	 * @param str
	 *            要转换的字符串
	 * @return
	 */
	public static Date getSmallShortDateByStr(String str) {
		Date result = null;
		if (StringUtils.isEmpty(str)) {
			return result;
		}
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat("yyyy-MM");
		try {
			result = SHORT_SDF.parse(str);
		} catch (Exception e) {
			logger.error("日期转换失败" + str, e);
		}

		return result;
	}


	public static String getLongDateByStr(Date date) {
		if (date == null) {
			return null;
		}
		String result = "";
		SimpleDateFormat LONG_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			result = LONG_SDF.format(date);
		} catch (Exception e) {
			logger.error("日期转换失败" + date, e);
		}

		return result;
	}

	public static String getShortDateByStr(Date date) {
		if (date == null) {
			return null;
		}
		String result = "";
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat("yyyy-MM-dd");
		try {
			result = SHORT_SDF.format(date);
		} catch (Exception e) {
			logger.error("日期转换失败" + date, e);
		}

		return result;
	}

	public static String getShortDateByStrSlash(Date date) {
		if (date == null) {
			return null;
		}
		String result = "";
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat("yyyy-MM-dd");
		try {
			result = SHORT_SDF.format(date);
		} catch (Exception e) {
			logger.error("日期转换失败" + date, e);
		}

		return result;
	}


	/**
	 * 根据指定的格式格式化日期
	 * 
	 * @param date
	 *            要转换的日期
	 * @param pattern
	 *            格式化的字符串
	 * @return
	 */
	public static String getDateStrByPattern(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		if (StringUtils.isEmpty(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		String result = "";
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat(pattern);
		try {
			result = SHORT_SDF.format(date);
		} catch (Exception e) {
			logger.error("日期转换失败" + date, e);
		}

		return result;
	}

	public static Date getDateByStr(String dateStr) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		String pattern = "yyyy-MM-dd HH:mm";
		Date result = null;
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat(pattern);
		try {
			result = SHORT_SDF.parse(dateStr);
		} catch (Exception e) {
			logger.error("日期转换失败" + dateStr, e);
		}

		return result;
	}

	public static Date getDateByStr(String dateStr, String pattern) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		if (StringUtils.isEmpty(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		Date result = null;
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat(pattern);
		try {
			result = SHORT_SDF.parse(dateStr);
		} catch (Exception e) {
			logger.error("日期转换失败" + dateStr, e);
		}

		return result;
	}

	public static int getWorkingDay(Date begin, Date end) {
		int result = 0;
		if (begin == null || end == null) {
			result = 0;
		}
		if (begin.after(end)) { // swap dates so that d1 is start and d2 is end
			Date swap = begin;
			begin = end;
			end = swap;
		}
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(begin);
		int beginYear = beginCalendar.get(Calendar.YEAR);
		int beginWeek = beginCalendar.get(Calendar.WEEK_OF_YEAR);
		boolean isFirstSunday = (beginCalendar.getFirstDayOfWeek() == Calendar.SUNDAY);
		int beginDayOfWeek = beginCalendar.get(Calendar.DAY_OF_WEEK);
		if (isFirstSunday) {
			beginDayOfWeek = beginDayOfWeek - 1;
			if (beginDayOfWeek == 0) {
				beginDayOfWeek = 7;
				beginWeek -= 1;
			}
		}
		if (beginDayOfWeek >= 6) {
			beginDayOfWeek = 6;
		}

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		int endYear = endCalendar.get(Calendar.YEAR);
		int endWeek = endCalendar.get(Calendar.WEEK_OF_YEAR);
		int endMonth = endCalendar.get(Calendar.MONTH);
		int endDayOfWeek = endCalendar.get(Calendar.DAY_OF_WEEK);
		if (endMonth >= 11 && endWeek <= 1) {
			endWeek += 52;
		}
		boolean isFirstSundayEnd = (beginCalendar.getFirstDayOfWeek() == Calendar.SUNDAY);
		if (isFirstSundayEnd) {
			endDayOfWeek = endDayOfWeek - 1;
			if (endDayOfWeek == 0) {
				endDayOfWeek = 7;
				endWeek -= 1;
			}
		}
		if (endDayOfWeek >= 6) {
			endDayOfWeek = 5;
		}

		if (beginYear == endYear && beginWeek == endWeek) {// 同一周
			result = endDayOfWeek - beginDayOfWeek + 1;
		} else {
			result = (endWeek - beginWeek - 1) * 5 + (6 - beginDayOfWeek) + endDayOfWeek;
		}
		return result;
	}

	/**
	 * 获取某一个月份的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMonthFirstDay(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		calendar.set(Calendar.MINUTE, 0);
		// 将秒至0
		calendar.set(Calendar.SECOND, 0);
		// 将毫秒至0
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取某一个月份的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMonthLastDay(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		// calendar.add(Calendar.DATE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至0
		calendar.set(Calendar.MINUTE, 59);
		// 将秒至0
		calendar.set(Calendar.SECOND, 59);
		// 将毫秒至0
		calendar.set(Calendar.MILLISECOND, 999);
		// System.out.println(calendar.getTime());
		return calendar.getTime();
	}

	public static Date getISODateByStr(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		str = str.replaceFirst("T", " ").replaceFirst("Z", " UTC");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date result = null;
		if (StringUtils.isEmpty(str)) {
			return result;
		}
		try {
			result = sdf.parse(str);
		} catch (Exception e) {
			logger.error("日期转换失败" + str, e);
		}

		return result;
	}

	public static String getISODateString(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ'Z'");
		String result = null;
		try {
			result = sdf.format(date);
		} catch (Exception e) {
			logger.error("日期转换失败" + date, e);
		}

		return result;
	}

	/**
	 * 获取当年的第一天
	 * 
	 * @return
	 */
	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取当年的最后一天
	 * 
	 * @return
	 */
	public static Date getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年上半年日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearMiddle(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, 6);
		calendar.set(Calendar.DATE, 30);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 通过输入指定日期时间生成cron表达式
	 * @param date
	 * @return cron表达式
	 */
	public static String getCron(Date date){
		String dateFormat="ss mm HH dd MM ? yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String formatTimeStr = null;
		if (date != null) {
			formatTimeStr = sdf.format(date);
		}
		return formatTimeStr;
	}


	public static void main(String[] args) {
		System.out.println(geDateAfter(new Date(),12));
	}

}
