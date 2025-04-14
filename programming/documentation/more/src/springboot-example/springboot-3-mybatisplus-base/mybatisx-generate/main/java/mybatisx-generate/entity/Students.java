package mybatisx-generate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName students
 */
@TableName(value ="students")
@Data
public class Students implements Serializable {
    private Integer id;

    private String name;

    private String gender;

    private Integer age;

    private String class;

    private static final long serialVersionUID = 1L;
}