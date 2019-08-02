package com.dg.mall.core.util;


import com.dg.mall.core.exception.CodeMsgRegistry;
import com.dg.mall.model.exception.AbstractBaseExceptionEnum;
import com.dg.mall.model.exception.ServiceException;

public class ValidationUtils {

  public static void checkParam(boolean b, AbstractBaseExceptionEnum exceptionEnum){
      if(!b){
          throw new ServiceException(exceptionEnum);
      }
  }

  public static void checkParam(boolean b,Integer code){
      if(!b){
          throw new ServiceException(code,CodeMsgRegistry.getMessageByCode(code));
      }
  }

  public static void checkParam(boolean b, Integer code,String... arguments){
      if(!b){
          throw new ServiceException(code, String.format(CodeMsgRegistry.getMessageByCode(code),(Object[])arguments));
      }
  }

  public static void checkNotNull(Object object, AbstractBaseExceptionEnum exceptionEnum){
      if(object == null){
          throw new ServiceException(exceptionEnum);
      }
  }

  public static void checkNotNull(Object object, Integer code){
      if(object == null){
          throw new ServiceException(code,CodeMsgRegistry.getMessageByCode(code));
      }
  }

  public static void checkNotNull(Object object, Integer code,String... arguments){
      if(object == null){
          throw new ServiceException(code, String.format(CodeMsgRegistry.getMessageByCode(code),(Object[])arguments));
      }
  }
}
