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
package com.dg.mall.logger.config.properties;

import lombok.Data;

/**
 * 日志记录的参数配置
 *
 * @author fengshuonan
 * @Date 2018/5/26 下午2:16
 */
@Data
public class LogProperties {

    /**
     * 日志记录的总开关（通过kafka）
     */
    private Boolean kafka = false;

    /**
     * 记录日志的级别（逗号隔开）
     */
    private String level = "error,info";

    /**
     * 是否开启trace链式记录
     */
    private Boolean trace = true;

}
