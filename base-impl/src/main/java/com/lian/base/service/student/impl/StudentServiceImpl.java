package com.lian.base.service.student.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lian.base.dao.student.StudentMapper;
import com.lian.base.dao.student.model.StudentDO;
import com.lian.base.service.student.InsertParamConverter;
import com.lian.base.service.student.StudentDoConverter;
import com.lian.base.service.student.dto.StudentDTO;
import com.lian.base.service.student.param.InsertParam;
import com.lian.base.service.student.param.UpdateParam;
import java.util.List;
import java.util.Objects;
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
    public void insert(InsertParam insertParam) {
        if (ObjectUtils.isEmpty(insertParam)) {
            return;
        }
        StudentDO studentDO = insertParamConverter.in2Out(insertParam);
        studentMapper.insert(studentDO);
    }

    @Override
    public void delete(Integer id) {
        if (Objects.isNull(id)) {
            return;
        }
        studentMapper.deleteById(id);
    }

    @Override
    public void update(UpdateParam updateParam) {
        if (ObjectUtils.isEmpty(updateParam)) {
            return;
        }

        StudentDO studentDO = insertParamConverter.in2Out(updateParam);
        studentDO.setId(updateParam.getId());
        studentMapper.updateById(studentDO);
    }


    @Override
    public StudentDTO get(Integer id) {
        StudentDO studentDO = studentMapper.selectById(id);
        return studentConverter.out2In(studentDO);
    }

    @Override
    public List<StudentDTO> listStudents() {
        List<StudentDO> studentDOS = studentMapper.selectList(new QueryWrapper<>());
        return studentConverter.out2In(studentDOS);
    }
}
