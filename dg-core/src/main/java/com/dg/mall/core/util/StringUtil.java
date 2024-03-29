package com.dg.mall.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 字符串工具类
 * 
 * @author admin
 *
 */
public class StringUtil extends StringUtils {

	public static final char UNDERLINE = '_';

	/**
	 * 驼峰格式字符串转换为下划线格式字符串
	 * 
	 * <pre>
	 * camelToUnderline("userName") = "user_name";
	 * </pre>
	 */
	public static String camelToUnderline(String param) {
		if (isBlank(param)) {
			return "";
		}

		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String[] camelToUnderline(String[] param) {
		if (param == null) {
			return null;
		}

		Integer length = param.length;
		String[] array = new String[length];
		for (int i = 0; i < length; i++) {
			array[i] = camelToUnderline(param[i]);
		}

		return array;
	}

	/**
	 * 下划线格式字符串转换为驼峰格式字符串
	 * 
	 * <pre>
	 * underlineToCamel("user_name") = "userName";
	 * </pre>
	 */
	public static String underlineToCamel(String param) {
		if (isBlank(param)) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 下划线格式字符串转换为大驼峰格式字符串
	 * 
	 * <pre>
	 * underlineToCamel("user_name") = "UserName";
	 * </pre>
	 */
	public static String underlineToBigCamel(String param) {
		if (isBlank(param)) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				if (i == 0) {
					c = Character.toUpperCase(c);
				}
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static List<String> splitList(String str) {
		String[] split = split(str, ",");

		return Arrays.asList(split);
	}
}