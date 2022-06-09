package com.lian.base.web.api.v1.student;

import com.lian.base.service.student.StudentService;
import com.lian.base.service.student.dto.StudentDTO;
import com.lian.base.web.api.BaseController;
import com.lian.base.web.api.v1.student.vo.StudentConverter;
import com.lian.base.web.api.v1.student.vo.StudentVO;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LianWenLong
 * @since 2022-04-25 14:42
 */
@RestController
@RequestMapping("/v1/student")
public class StudentController implements BaseController {

    @Resource
    StudentService studentService;
    @Resource
    StudentConverter studentConverter;

    @GetMapping(value = "/{id}")
    StudentVO getById(@PathVariable Integer id) {
        StudentDTO studentDTO = studentService.get(id);
        return studentConverter.so2to(studentDTO);
    }
}
