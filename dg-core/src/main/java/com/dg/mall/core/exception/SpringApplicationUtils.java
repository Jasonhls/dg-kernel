package com.dg.mall.core.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpringApplicationUtils {

    private static String basePackage;

    public static String getBasePackage() {
        return basePackage;
    }

    public static void setBasePackage(String basePackage) {
        SpringApplicationUtils.basePackage = basePackage;
    }

}
