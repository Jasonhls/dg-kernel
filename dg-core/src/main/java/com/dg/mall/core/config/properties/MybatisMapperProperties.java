package com.dg.mall.core.config.properties;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MybatisMapperProperties {
    private String mapperLocation;
}
