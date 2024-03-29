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
package com.dg.mall.logger.config;


import com.dg.mall.logger.config.properties.LogProperties;
import com.dg.mall.logger.service.LogProducerService;
import com.dg.mall.logger.service.impl.LogProducerServiceImpl;
import com.dg.mall.model.constants.ConfigPrefixConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 默认kafka消息队列日志
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午4:48:10
 */
@Configuration
public class LoggerAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = ConfigPrefixConstants.LOG_PREFIX)
    public LogProperties logProperties() {
        return new LogProperties();
    }

    @Bean
    @ConditionalOnProperty(prefix = ConfigPrefixConstants.LOG_PREFIX, value = "kafka", havingValue = "true")
    public LogProducerService logProducerService() {
        return new LogProducerServiceImpl();
    }
}