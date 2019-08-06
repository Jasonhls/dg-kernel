package com.dg.mall.core.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hn
 * @date 2019/8/6  10:48
 */
public class ConvertBeanUtil {

	private static ConcurrentHashMap<String, BeanCopier> cache = new ConcurrentHashMap();

	public static <T> T copyBeanProperties(Class source, Class<T> target, Object sourceObj, boolean useConverter) {
		if (sourceObj == null) {
			return null;
		}
		T t;
		try {
			t = target.newInstance();
		} catch (Exception e) {
			return null;
		}
		String key = source.getSimpleName() + target.getSimpleName();
		BeanCopier copier = (BeanCopier) cache.get(key);
		if (copier == null) {
			copier = createBeanCopier(source, target, useConverter, key);
		}
		copier.copy(sourceObj, t, null);
		return t;
	}

	public static <T> T copyBeanProperties(Object sourceObj, T target) {
		return (T) copyBeanProperties(sourceObj, target, false);
	}

	public static <T> T copyBeanProperties(Object sourceObj, T target, boolean useConverter) {
		if ((sourceObj == null) || (target == null)) {
			return null;
		}
		String key = sourceObj.getClass().getSimpleName() + target.getClass().getSimpleName();
		BeanCopier copier = (BeanCopier) cache.get(key);
		if (copier == null) {
			copier = createBeanCopier(sourceObj.getClass(), target.getClass(), useConverter, key);
		}
		copier.copy(sourceObj, target, null);
		return target;
	}

	public static <T> List<T> copyListBeanPropertiesToList(List<?> sourceObjs, List<T> targets, Class<T> targetType) {
		if ((sourceObjs == null) || (targets == null) || (targetType == null)) {
			return null;
		}
		for (Object o : sourceObjs) {
			try {
				T t = targetType.newInstance();
				targets.add(copyBeanProperties(o, t, false));
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			}
		}
		return targets;
	}

	private static String getHashKey(String str) {
		if (str == null) {
			return null;
		}
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}

	private static BeanCopier createBeanCopier(Class sourceClass, Class targetClass, boolean useConverter, String cacheKey) {
		BeanCopier copier = BeanCopier.create(sourceClass, targetClass, useConverter);
		cache.putIfAbsent(cacheKey, copier);
		return copier;
	}
}
