package com.lian.base.web.v1.student;

import com.alanpoi.analysis.common.utils.ExcelImportUtil;
import com.alanpoi.analysis.excel.imports.ExcelSheetData;
import com.alanpoi.analysis.excel.imports.handle.ExcelConsumeInterface;
import com.alanpoi.analysis.excel.imports.handle.ExcelError;

import com.alanpoi.analysis.excel.imports.handle.RowError;
import com.lian.base.service.student.StudentService;
import com.lian.base.service.student.dto.StudentDTO;
import com.lian.base.web.v1.student.vo.StudentVO;

import java.io.Serializable;
import java.util.*;

import java.util.stream.Collectors;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 学生信息Excel导入处理器
 * </p>
 *
 * @author LianWenLong
 * @since 2022/6/19 20:16
 */
@Slf4j
@Component
public class StudentImportHandler implements ExcelConsumeInterface {

    @Resource
    StudentService studentService;
    @Resource
    StudentVoConverter studentVoConverter;

    @Override
    public void error(ExcelError excelError) {

    }

    @Override
    public void validData(String workbookId, List<ExcelSheetData> sheetDataList, Map<Serializable, Object> excelParam) {
        excelParam.put("workbookId", workbookId);
        // 导入不覆盖情况下,及新增,校验已存在学生
        for (ExcelSheetData<StudentVO> sheetData : sheetDataList) {
            List<StudentVO> data = sheetData.getData();
            Map<Long, Integer> rowIndexMap = data.stream()
                                                 .collect(Collectors.toMap(StudentVO::getId,
                                                                           StudentVO::getRowIndex,
                                                                           (k1, k2) -> k1));
            List<StudentDTO> exist = studentService.getByIds(rowIndexMap.keySet());
            List<RowError> rowErrors = exist.stream()
                                            .map(dto -> new RowError(rowIndexMap.get(dto.getId()),
                                                                     dto.getId() + "主键重复"))
                                            .collect(Collectors.toList());
            ExcelImportUtil.reportExcelError(workbookId, sheetData.getIndex(), rowErrors);
        }
    }

    @Override
    public void end(List<ExcelSheetData> sheetDataList, Map<Serializable, Object> excelParam) {
        for (ExcelSheetData<StudentVO> excelSheetData : sheetDataList) {
            List<StudentVO> data = excelSheetData.getData();
            for (StudentVO datum : data) {
                StudentDTO dto = studentVoConverter.vo2do(datum);
                dto.setCreator("LianWenLong");
                try {
                    studentService.insert(dto);
                } catch (Exception e) {
                    RowError rowError = new RowError(datum.getRowIndex(), e.getMessage());
                    ExcelImportUtil.reportExcelError(excelParam.get("workbookId").toString(),
                                                     excelSheetData.getIndex(),
                                                     rowError);
                }
            }
        }
    }
}
