/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dg.mall.logger.chain.aop;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dg.mall.core.context.RequestNoContext;
import com.dg.mall.core.util.ToolUtil;
import com.dg.mall.logger.chain.context.ParentSpanIdHolder;
import com.dg.mall.logger.chain.context.SpanIdContext;
import com.dg.mall.logger.chain.context.SpanIdHolder;
import com.dg.mall.logger.chain.context.TraceIdHolder;
import com.dg.mall.logger.chain.enums.RpcPhaseEnum;
import com.dg.mall.logger.util.TraceUtil;
import com.dg.mall.model.auth.context.LoginUserHolder;
import com.dg.mall.model.constants.AopSortConstants;
import com.dg.mall.validator.util.CheckUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;



/**
 * 基于调用链的服务治理系统的设计（服务提供者的aop处理）
 *
 * @author fengshuonan
 * @date 2018年05月15日14:58:44
 */
@Aspect
@Order(AopSortConstants.CHAIN_ON_PROVIDER_SORT)
public class ChainOnProviderAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截控制器层和远程提供者层
     */
    @Pointcut("execution(* *..provider.*.*(..))")
    public void cutService() {
    }

    /**
     * <pre>
     *  主要业务逻辑如下：
     *      1. 获取traceId，spanId, parentSpanId（header中）(没有就是没走网关)
     *      2. 推送接收到远程调用的日志，响应远程调用成功或失败的响应
     * </pre>
     */
    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {

        long begin = System.currentTimeMillis();
        if (logger.isDebugEnabled()) {
            logger.debug("开始记录provider aop耗时！");
        }

        //获取拦截方法的参数
        Object[] methodParams = point.getArgs();

        //生成本节点的spanId
        String currentSpanId = IdWorker.getIdStr();
        SpanIdHolder.set(currentSpanId);

        //获取当前节点的parentSpanId
        String parentSpanId = SpanIdContext.getSpanIdByRequestParam(methodParams);
        ParentSpanIdHolder.set(parentSpanId);

        //获取traceId
        String traceId = RequestNoContext.getRequestNoByRequestParam(methodParams);
        TraceIdHolder.set(traceId);

        //初始化临时LoginUser
        LoginUserHolder.init();

        if (logger.isDebugEnabled()) {
            logger.debug("provider aop 获取参数！" + (System.currentTimeMillis() - begin));
        }

        try {

            //如果参数中，包含BaseValidatingParam的子类就开始校验参数
            if (methodParams != null && methodParams.length > 0) {
                CheckUtil.validateParameters(methodParams);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("provider aop 开始提供远程服务业务！" + (System.currentTimeMillis() - begin));
            }

            Object result = point.proceed();

            if (logger.isDebugEnabled()) {
                logger.debug("provider aop 提供远程服务完成！" + (System.currentTimeMillis() - begin));
            }

            return result;

        } catch (Throwable exception) {

            String exceptionMsg = ToolUtil.getExceptionMsg(exception);

            if (logger.isDebugEnabled()) {
                logger.debug("provider aop 记录完错误日志！" + (System.currentTimeMillis() - begin));
            }

            MethodSignature methodSignature = null;
            Signature signature = point.getSignature();
            if (signature instanceof MethodSignature) {
                methodSignature = (MethodSignature) signature;
            }

            //报告:发送给消费端失败的远程调用
            TraceUtil.trace(methodSignature, RpcPhaseEnum.EP3, traceId, currentSpanId, parentSpanId, exceptionMsg);

            throw exception;

        } finally {
            SpanIdHolder.remove();
            ParentSpanIdHolder.remove();
            TraceIdHolder.remove();
            LoginUserHolder.remove();
        }

    }

}
