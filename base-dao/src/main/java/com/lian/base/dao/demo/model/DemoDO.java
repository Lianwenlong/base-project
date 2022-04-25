package com.lian.base.dao.demo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("demo")
public class DemoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @TableField("name")
    private String name;

}
