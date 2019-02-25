package com.nstc.dbwriter.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * <p>Title: ExcelUtil.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2018年12月24日 下午2:57:12
 * 
 */
public class ExcelUtil {
    /**
     * 2003- 版本的excel
     */
    private final static String EXCEL2003L =".xls";
    /**
     * 2007+ 版本的excel
     */
    private final static String EXCEL2007U =".xlsx";
    
    private final static String GENERAL = "General";

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     * @param file
     * @return
     * @throws Exception
     */
    public static List<List<String>> importExcel(File file) throws Exception{
        List<List<String>> list = null;

        //创建Excel工作薄
        Workbook work = getWorkbook(file);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<List<String>>();
        //遍历Excel中所有的sheet
        for (int i = 0; i <work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}
            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(row==null||row.getFirstCellNum()==j){continue;}
                //遍历所有的列
                List<String> li = new ArrayList<String>();
                for (int y = row.getFirstCellNum(); y <row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(getCellValue(cell));
                }
                list.add(li);
            }
        }
        return list;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param file
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(File file) throws Exception{
        Workbook wb = null;
        String fileType = file.getName().substring(file.getName().lastIndexOf("."));
        if(EXCEL2003L.equals(fileType)){
            //2003-
            wb = new HSSFWorkbook(new FileInputStream(file));
        }else if(EXCEL2007U.equals(fileType)){
            //2007+
            wb = new XSSFWorkbook(new FileInputStream(file));
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        // 用String接收所有返回的值
        String value = null;
        // 格式化number String字符
        DecimalFormat df = new DecimalFormat("0");
        // 日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        // 格式化数字
        DecimalFormat df2 = new DecimalFormat("0.00");
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
        // String类型的数据
        case Cell.CELL_TYPE_STRING: 
            value = cell.getStringCellValue();
            break;
        // 数值类型(取值用cell.getNumericCellValue() 或cell.getDateCellValue())
        case Cell.CELL_TYPE_NUMERIC:
            if (GENERAL.equals(cell.getCellStyle().getDataFormatString())) {
                value = df.format(cell.getNumericCellValue());
            } else if (HSSFDateUtil.isCellDateFormatted(cell)) {
                value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
            } else {
                value = df2.format(cell.getNumericCellValue());
            }
            break;
        // Boolean类型
        case Cell.CELL_TYPE_BOOLEAN:
            value = String.valueOf(cell.getBooleanCellValue());
            break;

        // 表达式类型
        case Cell.CELL_TYPE_FORMULA:
            value = String.valueOf(cell.getCellFormula());
            break;
        // 异常类型 不知道何时算异常
        case Cell.CELL_TYPE_ERROR:
            value = String.valueOf(cell.getErrorCellValue());
            break;
        // 空，不知道何时算空
        case Cell.CELL_TYPE_BLANK:
            value = "";
            break;

        default:
            value = "";
            break;
        }
        if (value.equals("") || value == null) {
            value = "";
        }
        return value;
    }

}
