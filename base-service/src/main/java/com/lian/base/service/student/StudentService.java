package com.lian.base.service.student;

import com.lian.base.common.service.BaseService;
import com.lian.base.service.student.dto.StudentDTO;
import com.lian.base.service.student.param.InsertParam;
import com.lian.base.service.student.param.UpdateParam;
import java.util.List;

/**
 * <p>
 * 示例: 学生管理服务
 * </p>
 *
 * @author LianWenLong
 * @since 2022/5/30 17:02
 */
public interface StudentService extends BaseService {

    /**
     * 插入学生信息
     *
     * @param insertParam 学生信息
     */
    void insert(InsertParam insertParam);

    /**
     * 删除学生信息
     *
     * @param id 学生Id
     */
    void delete(Integer id);

    /**
     * 更新学生信息
     *
     * @param updateParam 更新信息
     */
    void update(UpdateParam updateParam);


    /**
     * 获取学生信息
     *
     * @param id 学生Id
     * @return StudentDTO
     */
    StudentDTO get(Integer id);

    /**
     * 学生列表
     * TODO: 分页实现
     *
     * @return List
     */
    List<StudentDTO> listStudents();

}
