package com.lian.base.dao.student.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lian.base.dao.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学生表实体模型
 * </p>
 *
 * @author LianWenLong
 * @since 2022/5/30 17:02
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "test_student.t_student")
public class StudentDO extends BaseDO {

    private static final long serialVersionUID = 5951084044913971170L;

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

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_AGE = "age";

    public static final String COL_ADDRESS = "address";
}