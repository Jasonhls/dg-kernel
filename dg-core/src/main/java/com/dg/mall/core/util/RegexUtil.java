package com.dg.mall.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配工具
 *
 */
public class RegexUtil {

	public static boolean matcher(String matcher, String pattern) {
		Pattern p = Pattern.compile(matcher);
		Matcher m = p.matcher(pattern);
		return m.matches();
	}
}
