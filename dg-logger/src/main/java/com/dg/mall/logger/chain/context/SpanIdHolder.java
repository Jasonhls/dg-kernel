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
package com.dg.mall.logger.chain.context;

/**
 * 基于调用链的服务治理系统的设计（requestNo以及当前节点的spanId和parentSpanId的临时存储器）
 *
 * @author fengshuonan
 * @date 2017年3月5日 上午9:10:58
 */
public class SpanIdHolder {

    private static final ThreadLocal<String> spanIdContext = new ThreadLocal<>();

    public static void set(String spanId) {
        spanIdContext.set(spanId);
    }

    public static String get() {
        return spanIdContext.get();
    }

    public static void remove() {
        spanIdContext.remove();
    }
}
