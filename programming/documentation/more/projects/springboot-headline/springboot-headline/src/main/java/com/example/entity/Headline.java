package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@TableName(value = "headlines")
@Data
public class Headline implements Serializable {
  @TableId
  private Integer hid;

  private String title;

  private String article;

  private Integer type;

  private Integer publisher;

  private Integer pageViews;

  private Date createTime;

  private Date updateTime;

  @Version
  private Integer version;

  @TableLogic
  private Integer isDeleted;

  @Serial
  private static final long serialVersionUID = 1L;
}
