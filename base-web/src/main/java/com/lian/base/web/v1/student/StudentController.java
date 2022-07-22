package com.lian.base.web.v1.student;

import com.lian.base.common.service.BaseController;
import io.swagger.annotations.Api;

import lombok.extern.slf4j.Slf4j;
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
@Api(tags = "Demo-学生管理")
public class StudentController implements BaseController {

    // @Resource
    // StudentService studentService;
    // @Resource
    // StudentVoConverter studentVoConverter;
    //
    //
    // @ApiOperation("新增学生")
    // @PostMapping
    // void insert(@RequestBody StudentDTO dto) {
    //     studentService.insert(dto);
    // }
    //
    //
    // @ApiOperation("删除学生")
    // @DeleteMapping("{id}")
    // void delete(@PathVariable Long id) {
    //     studentService.delete(id);
    // }
    //
    //
    // @ApiOperation("更新学生")
    // @PutMapping
    // void update(@Validated @RequestBody StudentDTO dto) {
    //     studentService.update(dto);
    // }
    //
    // @ApiOperation("查询学生信息")
    // @ApiImplicitParam(name = "id", value = "学生id", required = true)
    // @GetMapping(value = "{id}")
    // StudentVO getById(@PathVariable Long id) {
    //     StudentDTO studentDTO = studentService.get(id);
    //     // return studentVoConverter.do2vo(studentDTO);
    //     return null;
    // }
    //
    // @ApiOperation("学生列表")
    // @GetMapping("/list")
    // List<StudentVO> listStudents(QueryPager queryPager) {
    //     List<StudentDTO> studentDTOS = studentService.listStudents(queryPager);
    //     // return studentVoConverter.do2vo(studentDTOS);
    //     return null;
    // }
    //
    // @ApiOperation("学生信息导出")
    // @GetMapping("/download")
    // void download(HttpServletRequest request, HttpServletResponse response, QueryPager queryPager) {
    //     List<StudentVO> studentVOS = listStudents(queryPager);
    //     ExcelExportUtil.export(studentVOS, StudentVO.class, request, response, "Student.xlsx");
    // }
    //
    // @ApiOperation("学生信息导入")
    // @PostMapping("/upload")
    // ExcelImportRes upload(MultipartFile file) throws IOException {
    //     return ExcelImportUtil.customImportData("Student", file.getInputStream(), file.getOriginalFilename());
    // }

}
