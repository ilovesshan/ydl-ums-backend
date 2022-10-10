package com.ilovesshan.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YdlUserRole implements Serializable {

  private static final long serialVersionUID = 7106917257036519780L;
  private long userId;
  private long roleId;


}
