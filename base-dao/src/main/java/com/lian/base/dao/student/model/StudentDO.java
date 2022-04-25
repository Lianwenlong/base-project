package com.lian.base.dao.student.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author LianWenLong
 * @since 2022-04-25 14:45
 */
@Data
@TableName("student")
public class StudentDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;
    
}
