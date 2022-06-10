package com.lian.base.web.student;

import com.lian.base.common.BaseController;
import com.lian.base.service.student.StudentService;
import com.lian.base.service.student.dto.StudentDTO;
import com.lian.base.web.student.vo.StudentVoConverter;
import com.lian.base.web.student.vo.StudentVO;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class StudentController implements BaseController {

    @Resource
    StudentService studentService;
    @Resource
    StudentVoConverter studentVoConverter;

    @GetMapping(value = "{id}")
    StudentVO getById(@PathVariable Integer id) {
        StudentDTO studentDTO = studentService.get(id);
        return studentVoConverter.so2to(studentDTO);
    }
}
