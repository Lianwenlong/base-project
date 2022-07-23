package com.lian.base.dao.student.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lian.base.dao.BaseDO;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学生信息持久化对象
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/22 16:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_student")
public class StudentDO extends BaseDO implements Serializable {

    public static final String COL_NAME = "name";

    public static final String COL_AGE = "age";

    public static final String COL_ADDRESS = "address";

    /**
     * 姓名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

}