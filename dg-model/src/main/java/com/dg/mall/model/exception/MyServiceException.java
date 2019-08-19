package com.dg.mall.model.exception;

public class MyServiceException extends ApiServiceException{
    public MyServiceException(AbstractBaseExceptionEnum exception) {
        super(exception);
    }

    @Override
    public String getExceptionClassName() {
        return MyServiceException.class.getName();
    }
}
