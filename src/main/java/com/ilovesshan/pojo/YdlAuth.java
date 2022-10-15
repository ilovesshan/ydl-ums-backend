package com.ilovesshan.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class YdlAuth implements Serializable {

  private static final long serialVersionUID = 761615963103867708L;

  private  String userId;
  private  String username;
  private  String nickName;
  private List<YdlRole> ydlRoles;
}
