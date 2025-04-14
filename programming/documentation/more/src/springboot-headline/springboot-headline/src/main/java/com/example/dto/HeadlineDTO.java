package com.example.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeadlineDTO {
  private Integer hid;
  private String title;
  private String article;
  private Integer type;
  private Integer publisher;
  private Integer pageViews;
  private Date createTime;
  private Date updateTime;
}
