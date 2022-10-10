package com.ilovesshan.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YdlOperLog implements Serializable {

  private static final long serialVersionUID = 4482172779975759994L;

  private long operId;
  private String title;
  private String businessType;
  private String method;
  private String requestMethod;
  private String operName;
  private String operUrl;
  private String operIp;
  private long status;
  private String errorMsg;
  private java.sql.Timestamp operTime;


}
