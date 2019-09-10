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
package com.dg.mall.logger.constants;

/**
 * 请求ip相关常量
 *
 * @author huangna
 * @Date 2019/8/22  上午10:27
 */
public interface RequestConstants {

    String X_FORWARDED_FOR = "X-Forwarded-For";

    String X_REAL_IP = "X-Real-IP";

    String UNKNOWN = "unKnown";

    String LOCAL_IP = "0:0:0:0:0:0:0:1";

    String REAL_LOCAL_IP = "127.0.0.1";

}