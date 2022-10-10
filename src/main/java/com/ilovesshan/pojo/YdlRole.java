package com.sample;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
  private java.sql.Timestamp createTime;
  private String updateBy;
  private java.sql.Timestamp updateTime;



}
