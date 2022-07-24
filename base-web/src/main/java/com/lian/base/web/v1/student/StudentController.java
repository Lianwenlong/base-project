package com.lian.base.web.v1.student;

import com.alanpoi.analysis.common.utils.ExcelExportUtil;
import com.alanpoi.analysis.common.utils.ExcelImportUtil;
import com.alanpoi.analysis.excel.imports.ExcelImportRes;
import com.alanpoi.common.enums.ResponseEnum;
import com.lian.base.common.model.page.QueryPager;
import com.lian.base.common.model.page.ResultPager;
import com.lian.base.common.service.BaseController;
import com.lian.base.service.student.StudentService;
import com.lian.base.service.student.dto.StudentDTO;
import com.lian.base.web.v1.student.vo.StudentVO;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 学生管理控制器
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/22 19:00
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
    void insert(@RequestBody StudentDTO dto) {
        studentService.insert(dto);
    }


    @ApiOperation("删除学生")
    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        studentService.delete(id);
    }


    @ApiOperation("更新学生")
    @PutMapping
    StudentVO update(@Validated @RequestBody StudentDTO dto) {
        StudentDTO updated = studentService.update(dto);
        return studentVoConverter.do2vo(updated);
    }

    @ApiOperation("查询学生信息")
    @ApiImplicitParam(name = "id", value = "学生id", required = true)
    @GetMapping(value = "{id}")
    StudentVO get(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.get(id);
        return studentVoConverter.do2vo(studentDTO);
    }

    @ApiOperation("分页查询")
    @GetMapping("/students")
    ResultPager<StudentVO> listStudents(QueryPager queryPager) {
        ResultPager<StudentDTO> pager = studentService.listStudents(queryPager);
        ResultPager<StudentVO> resultPager = new ResultPager<>();
        resultPager.setCurrent(pager.getCurrent());
        resultPager.setSize(pager.getSize());
        resultPager.setSearchCount(pager.isSearchCount());
        resultPager.setTotal(pager.getTotal());
        List<StudentVO> records = studentVoConverter.do2vo(pager.getRecords());
        resultPager.setRecords(records);
        return resultPager;
    }

    @ApiOperation("学生信息导出")
    @GetMapping("/export")
    void export(HttpServletRequest request, HttpServletResponse response, QueryPager queryPager) {
        ResultPager<StudentDTO> studentPager = studentService.listStudents(queryPager);
        List<StudentVO> records = studentVoConverter.do2vo(studentPager.getRecords());
        ExcelExportUtil.export(records, StudentVO.class, request, response, "Student.xlsx");
    }

    @ApiOperation("学生信息导入")
    @PostMapping("/import")
    ExcelImportRes importStudents(MultipartFile file) throws IOException {
        String sheetName = "学生信息";
        Map<Serializable, Object> excelParam = new HashMap<>();
        ExcelImportRes importRes = ExcelImportUtil.customImportData("Student",
                                                                    file.getInputStream(),
                                                                    file.getOriginalFilename(),
                                                                    excelParam);
        // StudentImportHandler的end方法中落地失败的数据没有在结果计数的修改。需要修正
        int failNum = (int) excelParam.getOrDefault("学生信息", 0);
        Map<String, ExcelImportRes.SheetInfo> errorMap = importRes.getErrorMap();
        ExcelImportRes.SheetInfo sheetInfo = errorMap.get(sheetName);
        int sucNum = sheetInfo.getSucNum() - failNum;
        failNum = sheetInfo.getFailNum() + failNum;
        sheetInfo.setSucNum(sucNum);
        sheetInfo.setFailNum(failNum);
        importRes.setMessage(String.format(ResponseEnum.IMPORT_FILE_DATA_EXP.message(), sucNum, failNum));
        return importRes;
    }
}
