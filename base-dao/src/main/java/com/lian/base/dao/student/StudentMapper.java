package com.lian.base.dao.student;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lian.base.dao.student.model.StudentDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * TODO(注释说明)
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/22 16:14
 */
@Mapper
public interface StudentMapper extends BaseMapper<StudentDO> {

}