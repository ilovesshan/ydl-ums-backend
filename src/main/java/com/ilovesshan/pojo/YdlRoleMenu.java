package com.ilovesshan.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YdlRoleMenu implements Serializable {


  private static final long serialVersionUID = 6568506089001968575L;

  private long roleId;
  private long menuId;



}
