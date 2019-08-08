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
package com.dg.mall.core.context.login;


import com.dg.mall.model.auth.AbstractLoginUser;
import lombok.Data;

import java.util.List;


/**
 * 当前用户的登录信息
 *
 * @author fengshuonan
 * @Date 2018/8/22 下午6:19
 */
@SuppressWarnings("ALL")
@Data
//ApiModel(value = "用户信息传输对象", description = "登录用户信息")
public class LoginUser implements AbstractLoginUser {

    //ApiModelProperty(value = "主键id")
    private Integer userId;

    //ApiModelProperty(value = "手机号/账号")
    private String phone;

    //ApiModelProperty(value = "名称")
    private String name;

    //ApiModelProperty(value = "性别（M：男 F：女）")
    private String sex;

    //ApiModelProperty(value = "电子邮件")
    private String email;

    //ApiModelProperty(value = "状态(0:未启用 1：启用  2：冻结  3：删除）")
    private Integer status;

    //ApiModelProperty(value = "是否普通管理员（1：是，0：否）")
    private Boolean isGeneralAdministrator;

    //ApiModelProperty(value = "是否具备审核（1：是，0：否）")
    private Boolean isToexamine;

    //ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 角色集合
     */
    //ApiModelProperty(value = "用户角色信息")
    private List<SysRoleDTO> roles;

    /**
     * 菜单集合
     */
    //ApiModelProperty(value = "用户菜单信息")
    private List<SysMenuDTO> menus;

    @Override
    public Integer getUserUniqueId() {
        return userId;
    }
}
