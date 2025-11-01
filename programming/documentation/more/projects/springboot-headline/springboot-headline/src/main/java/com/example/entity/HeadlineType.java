package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@TableName(value = "headline_types")
@Data
public class HeadlineType implements Serializable {
  @TableId
  private Integer tid;

  private String tname;

  @Version
  private Integer version;

  @TableLogic
  private Integer isDeleted;

  @Serial
  private static final long serialVersionUID = 1L;
}
