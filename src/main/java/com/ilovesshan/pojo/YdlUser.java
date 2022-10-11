package com.ilovesshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YdlUser implements Serializable {

  private static final long serialVersionUID = 4291250707494907157L;

  private long userId;
  private String username;
  private String nickName;
  private String email;
  private String phonenumber;
  private String sex;
  private String avatar;
  private String password;
  private String status;
  private String delFlag;
  private String loginIp;
  private Date loginDate;
  private String createBy;
  private Date createTime;
  private String updateBy;
  private Date updateTime;


}
