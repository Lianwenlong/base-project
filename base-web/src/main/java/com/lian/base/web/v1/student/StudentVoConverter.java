package com.lian.base.web.v1.student;

import com.lian.base.dao.BaseConverter;
import com.lian.base.service.student.dto.StudentDTO;
import com.lian.base.web.v1.student.vo.StudentVO;
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
public interface StudentVoConverter extends BaseConverter<StudentVO, StudentDTO> {

}
