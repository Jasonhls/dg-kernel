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
package com.dg.mall.model.api;



import com.dg.mall.model.resource.ResourceDefinition;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

/**
 * 系统管理服务端提供的远程服务
 *
 * @author fengshuonan
 * @date 2018-02-06 14:30
 */
@RequestMapping("/api/resourceService")
public interface ResourceService {

    /**
     * 报告业务系统的资源(Resources)到服务器,appCode若重复则会覆盖
     */
    @RequestMapping(value = "/reportResources", method = RequestMethod.POST)
    void reportResources(@RequestParam("appCode") String appCode, @RequestBody Map<String, Map<String, ResourceDefinition>> resourceDefinitions);

    /**
     * 获取用户所拥有的资源url
     */
    @RequestMapping(value = "/getUserResourceUrls", method = RequestMethod.POST)
    Set<String> getUserResourceUrls(@RequestParam("accountId") String accountId);

    /**
     * 获取资源通过url
     */
    @RequestMapping(value = "/getResourceByUrl", method = RequestMethod.POST)
    ResourceDefinition getResourceByUrl(@RequestParam("url") String url);
}
