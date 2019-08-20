package com.dg.mall.core.exception;

import com.dg.mall.model.exception.AbstractBaseExceptionEnum;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class CodeMsgRegistry  {
    private static final ConcurrentHashMap<Integer,String> registry = new ConcurrentHashMap(16);

    public static String getMessageByCode(Integer code){
        return registry.get(code);
    }

    @PostConstruct
    public void init(){
        Set<Class<?>> clzFromPkg = ClassHelper.getClzFromPkg(SpringApplicationUtils.getBasePackage());
        for(Class clazz : clzFromPkg){
            Class[] interfaces = clazz.getInterfaces();
            for (int m = 0;m < interfaces.length;m++){
                Class anInterface = interfaces[m];
                if("com.dg.mall.model.exception.AbstractBaseExceptionEnum".equals(anInterface.getName())){
                    if(clazz.getSuperclass() != null && "java.lang.Enum".equals(clazz.getSuperclass().getName())){
                        AbstractBaseExceptionEnum[] enumConstants = (AbstractBaseExceptionEnum[]) clazz.getEnumConstants();
                        for (int j = 0;j < enumConstants.length;j++){
                            AbstractBaseExceptionEnum systemExceptionEnum = enumConstants[j];
                            if(systemExceptionEnum != null){
                                registry.put(systemExceptionEnum.getCode(),systemExceptionEnum.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }
}
