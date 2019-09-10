package com.dg.mall.logger.config;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    //方法描述
     String description() default "";
    //方法参数
     String keyParam() default "";
    //方法参数
    String paramDescription() default "";
}
