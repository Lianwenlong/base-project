package com.lian.base.service.student;

import com.lian.base.common.model.QueryPager;
import com.lian.base.common.model.ResultPager;
import com.lian.base.common.service.BaseService;
import com.lian.base.service.student.dto.StudentDTO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 学生管理服务
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/22 16:15
 */
public interface StudentService extends BaseService {

    /**
     * 新增学生信息
     *
     * @param dto 学生信息
     */
    void insert(StudentDTO dto);


    /**
     * 删除学生信息
     *
     * @param id 学生ID
     */
    void delete(Long id);

    /**
     * 修改学生信息
     *
     * @param dto 学生对象
     * @return StudentDTO
     */
    StudentDTO update(StudentDTO dto);


    /**
     * 查询学生信息
     *
     * @param id 学生ID
     * @return StudentDTO
     */
    StudentDTO get(Long id);

    /**
     * 根据ID获取学生
     *
     * @param ids ID列表
     * @return Students
     */
    List<StudentDTO> getByIds(Set<Long> ids);

    ResultPager<StudentDTO> listStudents(QueryPager queryPager);
}
