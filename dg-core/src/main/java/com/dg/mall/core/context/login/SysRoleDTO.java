package com.dg.mall.core.context.login;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-08-01
 */
@Data
@AllArgsConstructor
//@ApiModel(value="SysRole服务传输对象", description="SysRole主要信息类")
public class SysRoleDTO implements Serializable {

    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "主键")
    private Integer roleId;

    //@ApiModelProperty(value = "角色名称")
    private String roleName;

    //@ApiModelProperty(value = "角色概述")
    private String roleDescription;
}
