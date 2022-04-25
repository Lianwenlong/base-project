package com.lian.base.dao.student;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lian.base.dao.student.model.StudentDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LianWenLong
 * @since 2022-04-25 14:42
 */
@Mapper
public interface StudentMapper extends BaseMapper<StudentDO> {

}
