package com.lian.base.service.student.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 学生对象DTO模型
 * </p>
 *
 * @author LianWenLong
 * @since 2022/5/30 17:35
 */
@Data
public class StudentDTO implements Serializable {


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
