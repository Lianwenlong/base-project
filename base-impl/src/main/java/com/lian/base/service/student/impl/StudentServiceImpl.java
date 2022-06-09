package com.lian.base.service.student.impl;

import com.lian.base.dao.student.StudentMapper;
import com.lian.base.dao.student.model.StudentDO;
import com.lian.base.service.student.StudentConverter;
import com.lian.base.service.student.dto.StudentDTO;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lian.base.service.student.StudentService;

/**
 * <p>
 * 示例: 学生管理服务实现
 * </p>
 *
 * @author LianWenLong
 * @since 2022/5/30 17:02
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentMapper studentMapper;
    @Resource
    StudentConverter studentConverter;

    @Override
    public StudentDTO get(Integer id) {
        StudentDO studentDO = studentMapper.selectById(id);
        return studentConverter.to2so(studentDO);
    }
}
