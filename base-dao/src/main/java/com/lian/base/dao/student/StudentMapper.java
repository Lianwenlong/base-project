package com.lian.base.dao.student;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lian.base.dao.student.model.StudentDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 示例: 学生表管理Mapper
 * </p>
 *
 * @author LianWenLong
 * @since 2022/5/30 17:02
 */
@Mapper
public interface StudentMapper extends BaseMapper<StudentDO> {

}