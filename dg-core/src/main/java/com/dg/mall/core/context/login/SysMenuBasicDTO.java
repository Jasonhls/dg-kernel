package com.dg.mall.core.context.login;

import lombok.Data;

/**
 * @author mabo
 * @create 2019/8/7 15:14
 */
@Data
public class SysMenuBasicDTO {

    private String title;

    private String icon;

    public SysMenuBasicDTO(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }
}
