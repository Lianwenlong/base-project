package com.lian.base.web.v1.student;

import com.alanpoi.analysis.common.utils.ExcelExportUtil;
import com.alanpoi.analysis.common.utils.ExcelImportUtil;
import com.alanpoi.analysis.excel.imports.ExcelImportRes;
import com.lian.base.common.service.BaseController;
import com.lian.base.service.student.StudentService;
import com.lian.base.service.student.dto.StudentDTO;
import com.lian.base.service.student.param.InsertParam;
import com.lian.base.service.student.param.UpdateParam;
import com.lian.base.web.v1.student.query.StudentQuery;
import com.lian.base.web.v1.student.converter.StudentVoConverter;
import com.lian.base.web.v1.student.vo.StudentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@Api(tags = "Demo-学生管理")
public class StudentController implements BaseController {

    @Resource
    StudentService studentService;
    @Resource
    StudentVoConverter studentVoConverter;


    @ApiOperation("新增学生")
    @PostMapping
    void insert(@RequestBody InsertParam insertParam) {
        studentService.insert(insertParam);
    }


    @ApiOperation("删除学生")
    @DeleteMapping("{id}")
    void delete(@PathVariable Integer id) {
        studentService.delete(id);
    }


    @ApiOperation("更新学生")
    @PutMapping
    void update(@Validated @RequestBody UpdateParam updateParam) {
        studentService.update(updateParam);
    }

    @ApiOperation("查询学生信息")
    @ApiImplicitParam(name = "id", value = "学生id", required = true)
    @GetMapping(value = "{id}")
    StudentVO getById(@PathVariable Integer id) {
        StudentDTO studentDTO = studentService.get(id);
        return studentVoConverter.in2Out(studentDTO);
    }

    @ApiOperation("学生列表")
    @GetMapping("/list")
    List<StudentVO> listStudents(StudentQuery studentQuery) {
        List<StudentDTO> studentDTOS = studentService.listStudents();
        return studentVoConverter.in2Out(studentDTOS);
    }

    @ApiOperation("学生信息导出")
    @GetMapping("/download")
    void download(HttpServletRequest request, HttpServletResponse response, StudentQuery studentQuery) {
        List<StudentVO> studentVOS = listStudents(studentQuery);
        ExcelExportUtil.export(studentVOS, StudentVO.class, request, response, "Student.xlsx");
    }

    @ApiOperation("学生信息导入")
    @PostMapping("/upload")
    ExcelImportRes upload(MultipartFile file) throws IOException {
        return ExcelImportUtil.customImportData("Student", file.getInputStream(), file.getOriginalFilename());
    }

}
