package com.dg.mall.core.exception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class CodeApplicationListener implements SpringApplicationRunListener {
    private final SpringApplication springApplication;
    private final String[] args;

    public CodeApplicationListener(SpringApplication springApplication,String[] args) {
        this.springApplication = springApplication;
        this.args = args;
    }

    @Override
    public void starting() {
        Class<?> mainApplicationClass = this.springApplication.getMainApplicationClass();
        String name = mainApplicationClass.getName();
        int index = name.lastIndexOf(".");
        SpringApplicationUtils.setBasePackage(name.substring(0,index));
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
