package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@TableName(value = "users")
@Data
public class User implements Serializable {
  @TableId
  private Integer uid;

  private String username;

  private String password;

  private String nickName;

  @Version
  private Integer version;

  @TableLogic
  private Integer isDeleted;

  @Serial
  private static final long serialVersionUID = 1L;
}
