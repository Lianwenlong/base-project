package com.lian.base.service.student;

import com.lian.base.dao.BaseConverter;
import com.lian.base.dao.student.model.StudentDO;
import com.lian.base.service.student.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 学生模型转换器
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/22 17:09
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentConverter extends BaseConverter<StudentDTO, StudentDO> {
    
}
