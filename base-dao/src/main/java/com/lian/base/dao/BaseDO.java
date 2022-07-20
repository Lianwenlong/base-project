package com.lian.base.dao;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 * <p>
 * 数据库实体基类 封装通用属性
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/20 14:27
 */
@Data
@MappedSuperclass
public abstract class BaseDO implements Serializable {

    private static final long serialVersionUID = -2468811473547035750L;
    public static final String ID = "id";
    public static final String CREATE_TIME = "create_time";
    public static final String CREATOR = "creator";
    public static final String CREATOR_NAME = "creator_name";
    public static final String MODIFY_TIME = "modify_time";
    public static final String MODIFIER = "modifier";
    public static final String MODIFIER_NAME = "modifier_name";

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;

    /**
     * 创建人翻译
     */
    @TableField(value = "creator_name", exist = false)
    private String creatorName;

    /**
     * 更新时间
     */
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyTime;

    /**
     * 更新人
     */
    @TableField(value = "modifier", fill = FieldFill.INSERT_UPDATE)
    private String modifier;

    /**
     * 更新人翻译
     */
    @TableField(value = "modifier_name", exist = false)
    private String modifierName;

}
