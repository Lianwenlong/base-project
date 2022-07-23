package com.lian.base.service.student.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lian.base.common.model.QueryPager;
import com.lian.base.common.model.ResultPager;
import com.lian.base.common.util.ParamUtils;
import com.lian.base.dao.student.StudentMapper;
import com.lian.base.dao.student.model.StudentDO;
import com.lian.base.service.student.StudentConverter;
import com.lian.base.service.student.StudentService;
import com.lian.base.service.student.dto.StudentDTO;

import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 学生管理服务实现类
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/22 16:16
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentMapper studentMapper;
    @Resource
    StudentConverter studentConverter;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(StudentDTO dto) {
        ParamUtils.dtoNotNullValidate(dto, StudentDTO.class.getSimpleName());
        StudentDO studentDO = studentConverter.vo2do(dto);
        studentMapper.insert(studentDO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        ParamUtils.idNotNullValidate(id, StudentDO.COL_ID);
        studentMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public StudentDTO update(StudentDTO dto) {
        ParamUtils.dtoNotNullValidate(dto, StudentDTO.class.getSimpleName());
        ParamUtils.idNotNullValidate(dto.getId(), StudentDO.COL_ID);
        StudentDO studentDO = studentConverter.vo2do(dto);
        studentMapper.updateById(studentDO);
        return get(dto.getId());
    }

    @Override
    public StudentDTO get(Long id) {
        ParamUtils.idNotNullValidate(id, StudentDO.COL_ID);
        StudentDO studentDO = studentMapper.selectById(id);
        return studentConverter.do2vo(studentDO);
    }

    @Override
    public List<StudentDTO> getByIds(Set<Long> ids) {
        ParamUtils.setNotEmptyValidate(ids, "ids");
        QueryWrapper<StudentDO> wrapper = new QueryWrapper<>();
        wrapper.in(StudentDO.COL_ID, ids);
        List<StudentDO> dos = studentMapper.selectList(wrapper);
        return studentConverter.do2vo(dos);
    }

    @Override
    public ResultPager<StudentDTO> listStudents(QueryPager queryPager) {
        // 设置查询条件
        QueryWrapper<StudentDO> wrapper = new QueryWrapper<>();
        queryPager.like(StudentDTO::getName, n -> wrapper.like(StudentDO.COL_NAME, n));
        Page<StudentDO> page = studentConverter.queryPager2Page(queryPager);
        Page<StudentDO> iPage = studentMapper.selectPage(page, wrapper);
        return studentConverter.page2ResultPager(iPage);
    }
}