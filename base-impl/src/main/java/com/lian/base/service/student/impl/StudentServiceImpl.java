package com.lian.base.service.student.impl;


import com.lian.base.dao.student.StudentMapper;
import com.lian.base.dao.student.model.StudentDO;
import com.lian.base.service.student.InsertParamConverter;
import com.lian.base.service.student.StudentDoConverter;
import com.lian.base.service.student.dto.StudentDTO;
import com.lian.base.service.student.param.InsertParam;
import com.lian.base.service.student.param.UpdateParam;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lian.base.service.student.StudentService;
import org.springframework.util.ObjectUtils;

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
    InsertParamConverter insertParamConverter;
    @Resource
    StudentDoConverter studentConverter;


    @Override
    public Integer insert(InsertParam insertParam) {
        if (ObjectUtils.isEmpty(insertParam)) {
            return 0;
        }
        StudentDO studentDO = insertParamConverter.in2Out(insertParam);
        return studentMapper.insert(studentDO);
    }

    @Override
    public Integer delete(Integer id) {
        return studentMapper.deleteById(id);
    }

    @Override
    public Integer update(UpdateParam updateParam) {
        if (ObjectUtils.isEmpty(updateParam)) {
            return 0;
        }

        StudentDO studentDO = insertParamConverter.in2Out(updateParam);
        studentDO.setId(updateParam.getId());
        return studentMapper.updateById(studentDO);
    }


    @Override
    public StudentDTO get(Integer id) {
        StudentDO studentDO = studentMapper.selectById(id);
        return studentConverter.out2In(studentDO);
    }
}
