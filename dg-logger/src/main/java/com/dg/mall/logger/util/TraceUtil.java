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
package com.dg.mall.logger.util;


import com.dg.mall.core.util.HttpContext;
import com.dg.mall.core.util.SpringContextHolder;
import com.dg.mall.core.util.ToolUtil;
import com.dg.mall.logger.chain.enums.RpcPhaseEnum;
import com.dg.mall.logger.config.properties.LogProperties;
import com.dg.mall.logger.entity.SendingTCLog;
import com.dg.mall.logger.entity.SendingTraceLog;
import com.dg.mall.logger.service.LogProducerService;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 日志记录工具
 *
 * @author fengshuonan
 * @date 2018-01-16 15:00
 */
public class TraceUtil {

    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void trace(
            MethodSignature methodSignature, RpcPhaseEnum rpcPhaseEnum, String traceId, String spanId, String parentSpanId) {
        trace(methodSignature, rpcPhaseEnum, traceId, spanId, parentSpanId, "");
    }

    public static void trace(
            MethodSignature methodSignature, RpcPhaseEnum rpcPhaseEnum, String traceId, String spanId, String parentSpanId, String errorMessage) {

        if (isTraceFlag()) {

            String servletPath = "";

            try {
                servletPath = HttpContext.getRequest().getServletPath();
            } catch (NullPointerException e) {
                //为空代表当前没有http请求
            }

            SendingTraceLog sendingTraceLog = new SendingTraceLog();
            sendingTraceLog.setIp(ToolUtil.getIP());
            sendingTraceLog.setAppCode(ToolUtil.getApplicationName());
            sendingTraceLog.setCreateTimestamp(System.currentTimeMillis());
            sendingTraceLog.setParentSpanId(parentSpanId);
            sendingTraceLog.setSpanId(spanId);
            sendingTraceLog.setRpcPhase(rpcPhaseEnum.name());
            sendingTraceLog.setServletPath(servletPath);
            sendingTraceLog.setTraceId(traceId);
            sendingTraceLog.setContent(errorMessage);

            try {
                getLogProducer().sendTraceMsg(sendingTraceLog);
            } catch (Exception e) {
                logger.error("发送trace消息错误！", e);
            }
        }

    }

    public static void trace(String requestPath, Long useTime) {

        if (isTraceFlag()) {

            SendingTCLog sendingTCLog = new SendingTCLog();
            sendingTCLog.setCreateTime(new Date());
            sendingTCLog.setRequestPath(requestPath);
            sendingTCLog.setUseTime(useTime);

            try {
                getLogProducer().sendTcMsg(sendingTCLog);
            } catch (Exception e) {
                logger.error("发送trace tc消息错误！", e);
            }
        }

    }

    private static LogProducerService getLogProducer() {
        return SpringContextHolder.getBean(LogProducerService.class);
    }

    private static LogProperties getLogProperties() {
        return SpringContextHolder.getBean(LogProperties.class);
    }

    private static boolean isTraceFlag() {

        Boolean traceFlag = null;

        try {
            traceFlag = getLogProperties().getTrace();
        } catch (Exception e) {
            logger.error("获取trace！", e);
            traceFlag = false;
        }

        if (traceFlag == null) {
            return false;
        } else {
            return traceFlag;
        }

    }
}
