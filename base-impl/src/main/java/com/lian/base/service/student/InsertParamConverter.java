package com.lian.base.service.student;

import com.lian.base.common.service.BaseConverter;
import com.lian.base.dao.student.model.StudentDO;
import com.lian.base.service.student.param.InsertParam;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 学生实体转换工具接口
 * </p>
 *
 * @author LianWenLong
 * @since 2022/6/9 22:18
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InsertParamConverter extends BaseConverter<InsertParam, StudentDO> {

}
