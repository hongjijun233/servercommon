package com.lianghongji.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

/**
 * 日期相关辅助类
 * 
 * @author denghuaiyu
 *
 */
public class DateUtils {
	private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);
	
	private static final String YYYMMDDHHmm = "YYYY-MM-dd HH:mm";
	private static final String YYYMMDDHHmmss = "YYYY-MM-dd HH:mm:ss";
	private static final String YYYYMMDD = "YYYY-MM-dd";
	private static final String HHmmss = "HH:mm:ss";

	/**
	 * 将日期格式化为 HH:mm:ss 字符串
	 *
	 * @return
	 */
	public static String formatHHmmss(Date date) {
		return DateFormatUtils.format(date, HHmmss);
	}

	/**
	 * 将日期格式化为 YYYY-MM-DD HH:MM 字符串
	 * 
	 * @return
	 */
	public static String formatYYYMMDDHHmm(Date date) {
		return DateFormatUtils.format(date, YYYMMDDHHmm);
	}

	public static Date parseYYYMMDDHHmm(String dateStr) {
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, YYYMMDDHHmm);
		} catch (ParseException e) {
			LOG.error("parse date error ", e);
			return null;
		}
	}
	
	/**
	 * 从YYYY-MM-dd分析日期
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseYYYMMDD(String dateStr) {
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, YYYYMMDD);
		} catch (ParseException e) {
			LOG.error("parse date error ", e);
			return null;
		}
	}
	
	/**
	 * 将日期格式化为 YYYY-MM-DD 字符串
	 * 
	 * @return
	 */
	public static String formatYYYYMMDD(Date date){
		return DateFormatUtils.format(date, YYYYMMDD);
	}
	
	/**
	 * 将日期格式化为 YYYY-MM-dd HH:mm:ss 字符串
	 * 
	 * @return
	 */
	public static String formatYYYMMDDHHmmss(Date date){
		return DateFormatUtils.format(date, YYYMMDDHHmmss);
	}
	
	public static Date parseYYYMMDDHHmmss(String dateStr) {
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, YYYMMDDHHmmss);
		} catch (ParseException e) {
			LOG.error("parse date error ", e);
			return null;
		}
	}
	
	/**
	 * 返回比source的加减时间，该时间是从00:00:00开始
	 * 
	 * @param source
	 * @param nday
	 * @return
	 */
	public static Date addDays(Date source, int nday){
		Date dateAdd = org.apache.commons.lang3.time.DateUtils.addDays(new Date(), nday);
		String dateAddStr = DateUtils.formatYYYYMMDD(dateAdd);
		return DateUtils.parseYYYMMDDHHmmss(dateAddStr + " 00:00:00");
	}
}
