package com.ilovesshan.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YdlUserLogin implements Serializable {

  private static final long serialVersionUID = 3237326061710617880L;

  private long loginId;
  private long userId;
  private String token;
  private String userName;
  private String loginIp;
  private java.sql.Timestamp loginTime;
  private String loginLocaltion;
  private String browser;
  private String systemOs;




}
