package com.ilovesshan.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class YdlMenu implements Serializable {

  private static final long serialVersionUID = 761615963103867708L;

  private long menuId;
  private String menuName;
  private String perms;
  private long parentId;
  private long orderNum;
  private String path;
  private String component;
  private String menuType;
  private String visible;
  private String icon;
  private String createBy;
  private java.sql.Timestamp createTime;
  private String updateBy;
  private java.sql.Timestamp updateTime;

}
