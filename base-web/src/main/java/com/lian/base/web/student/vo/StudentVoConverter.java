package com.lian.base.web.student.vo;

import com.lian.base.common.BaseConverter;
import com.lian.base.service.student.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 对象转换接口
 * </p>
 *
 * @author LianWenLong
 * @since 2022/6/9 22:55
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentVoConverter extends BaseConverter<StudentDTO, StudentVO> {

}
