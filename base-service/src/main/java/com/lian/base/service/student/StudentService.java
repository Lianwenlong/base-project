package com.lian.base.service.student;

import com.lian.base.common.BaseService;
import com.lian.base.service.student.dto.StudentDTO;

/**
 * <p>
 * 示例: 学生管理服务
 * </p>
 *
 * @author LianWenLong
 * @since 2022/5/30 17:02
 */
public interface StudentService extends BaseService {

    StudentDTO get(Integer id);

}
