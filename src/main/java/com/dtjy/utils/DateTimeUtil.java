/**
 * @author dtjy
 * 2018-12-19 21:43
 */
package com.dtjy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 * @author dtjy
 * 2018-12-19 21:43
 */
public class DateTimeUtil {
	
	public static void main(String[] args) {
		System.out.println(toHhmmSs());
	}
	
	/**
	 * 转换为yyyy-MM-dd格式
	 * @author dtjy
	 * 2018-12-19 21:46
	 * @return
	 */
	public static String toYyyyMmDd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	
	/**
	 * 转换为yyyy-MM-dd HH:mm:ss格式
	 * @author dtjy
	 * 2018-12-19 21:46
	 * @return
	 */
	public static String toYyyyMmDdHhmmSs() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	/**
	 * 转换为HH:mm:ss格式
	 * @author dtjy
	 * 2018-12-19 21:47
	 * @return
	 */
	public static String toHhmmSs() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new Date());
	}
}
