package com.ilovesshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YdlRole implements Serializable {

    private static final long serialVersionUID = -4992728841122130205L;

    private long roleId;
    private String roleName;
    private String roleTag;
    private long roleSort;
    private String status;
    private String delFlag;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private List<YdlMenu> ydlMenus;
}
