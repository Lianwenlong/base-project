package com.lian.base.web.v1.student;

import com.alanpoi.analysis.excel.imports.ExcelSheetData;
import com.alanpoi.analysis.excel.imports.handle.ExcelConsumeInterface;
import com.alanpoi.analysis.excel.imports.handle.ExcelError;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    @Override
    public void error(ExcelError excelError) {

    }

    @Override
    public void validData(String workbookId, List<ExcelSheetData> sheetDataList, Map<Serializable, Object> excelParam) {

    }

    @Override
    public void end(List<ExcelSheetData> sheetDataList, Map<Serializable, Object> excelParam) {
        // for (ExcelSheetData<StudentVO> excelSheetData : sheetDataList) {
        //     List<StudentVO> students = excelSheetData.getData();
        //     // TODO: 批量操作接口
        //     // for (StudentVO student : students) {
        //     //     InsertParam insertParam = new InsertParam();
        //     //     insertParam.setName(student.getName());
        //     //     insertParam.setAge(student.getAge());
        //     //     insertParam.setAddress(student.getAddress());
        //     //     studentService.delete(student.getId());
        //     //     studentService.insert(insertParam);
        //     // }
        // }
    }
}
