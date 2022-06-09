package com.lian.base.web.api.v1.student.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 展示层学生对象
 * </p>
 *
 * @author LianWenLong
 * @since 2022/5/30 18:41
 */
@Data
public class StudentVO implements Serializable {

    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 地址
     */
    private String address;
}
