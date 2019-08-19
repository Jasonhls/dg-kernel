package com.dg.mall.logger.entity;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author hn
 * @Description: 封装日志消息实体类
 * @Date 2019/8/2 10:25
 * @Version V1.0
 **/

@Data
public class SysOperationLogDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * userId
     */
    private String userId;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * path
     */
    private String path;


    public SysOperationLogDTO(String userId, String ipAddress, String requestUrl, String requestParams, String requestType, Date createTime, String path) {
        this.userId = userId;
        this.ipAddress = ipAddress;
        this.requestUrl = requestUrl;
        this.requestParams = requestParams;
        this.requestType = requestType;
        this.createTime = createTime;
        this.path = path;
    }
}
