package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortalVO {
  private String keywords;
  private Integer type;
  private Integer pageNum;
  private Integer pageSize;
}
