package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("students")  // 数据库表名
public class Student {
  @TableId(value = "id", type = IdType.AUTO)  // 主键，设置为自增长
  private Integer id;

  @TableField("name")  // 非主键字段
  private String name;

  @TableField("gender")
  private String gender;

  @TableField("age")
  private Integer age;

  @TableField("class")
  private String classes;

  /**
   * 逻辑删除：假删除，将对应数据库中代表是否被删除字段的状态修改为“被删除”，数据库中依旧保留了该数据
   * 物理删除：真删除，将对应数据库中的数据删除，不可恢复
   *
   * 使用逻辑删除：deleted 字段默认为 0，未删除为 0，已删除为 1
   * 查询：select * from students where deleted = 0
   * 删除：update students set deleted = 1 where id = xxx and deleted = 0
   */
  // @TableLogic
  // private Integer deleted;

  // @Version
  // 支持的数据类型包括：Integer、Long、Date、LocalDateTime、String
  // 仅支持：updateById(id) 和 update(entity, wrapper) 方法
  // private Integer version;
}
