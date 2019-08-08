package com.dg.mall.core.context.login;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jmabo
 * @since 2019-08-01
 */
@Data
//@ApiModel(value="SysMenu服务传输对象", description="菜单栏主要信息类")
public class SysMenuDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer parentId;

    private String sort;

    private String path;

    // private String title;

    private String matchRoute;

    private String redirect;

    // private String icon;

    private String type;

    private String memo;

    private String createdUser;

    private Date createdTime;

    private String updatedUser;

    private Date updatedTime;

    private int isDeleted;

    private List<SysMenuDTO> children = new ArrayList<SysMenuDTO>();

    private SysMenuBasicDTO mata;
}
