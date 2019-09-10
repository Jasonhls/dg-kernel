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
package com.dg.mall.logger.chain.enums;

import lombok.Getter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.lang.annotation.Annotation;

/**
 * 请求方式标识
 *
 * @author hn
 * @Date 2019/8/14 下午5:31
 */
@Getter
public enum RequestTypeEnum {

    GET(1,"GET"),

    POST(2,"POST"),

    PUT(3,"PUT"),

    DELETE(4,"DELETE")
    ;

    private Integer code;
    private String desc;

    RequestTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getNameByCode(Integer code) {
        if (code == null) {
            return null;
        } else {
            for (RequestTypeEnum enumItem : RequestTypeEnum.values()) {
                if (enumItem.getCode().equals(code)) {
                    return enumItem.getDesc();
                }
            }
            return null;
        }
    }

    public static RequestTypeEnum toEnum(Integer code) {
        if (null == code) {
            return null;
        } else {
            for (RequestTypeEnum e : RequestTypeEnum.values()) {
                if (e.getCode().equals(code)) {
                    return e;
                }
            }
            return null;
        }
    }

}
