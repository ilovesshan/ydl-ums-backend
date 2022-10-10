package com.ilovesshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YdlUser implements Serializable {

  private static final long serialVersionUID = 4291250707494907157L;

  private long userId;
  private String userName;
  private String nickName;
  private String email;
  private String phonenumber;
  private String sex;
  private String avatar;
  private String password;
  private String status;
  private String delFlag;
  private String loginIp;
  private java.sql.Timestamp loginDate;
  private String createBy;
  private java.sql.Timestamp createTime;
  private String updateBy;
  private java.sql.Timestamp updateTime;


}
