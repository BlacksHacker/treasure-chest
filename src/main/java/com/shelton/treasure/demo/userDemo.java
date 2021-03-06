package com.shelton.treasure.demo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName ExcelReadUtis
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public class userDemo {

    public static void excel2007Reader(String filePath){
        File excelFile = null;
        InputStream is = null;
        excelFile = new File(filePath);
        String cellStr = "";
        Workbook workbook2007 = null;
        try {
            is = new FileInputStream(excelFile);
            workbook2007 = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder("(");
        Sheet sheet = workbook2007.getSheetAt(0);
        for (int i = 1; i < sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            for (int j =0; j < row.getLastCellNum(); j++){
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cellStr = "";
                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
                    cellStr = String.valueOf(cell.getBooleanCellValue());
                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    cellStr = cell.getNumericCellValue() + "";
                } else {
                    cellStr = cell.getStringCellValue();
                }
                if (i == 1){
                    builder.append("'");
                    builder.append(cellStr);
                    builder.append("'");
                }else {
                    builder.append(",");
                    builder.append("'");
                    builder.append(cellStr);
                    builder.append("'");
                }
            }
        }
        System.out.println(sheet.getLastRowNum());
        builder.append(")");
        System.out.println("select username from user_bind_info where bindname in " + builder.toString());
    }

    public static void main(String[] args) {
        String path = "D:\\file\\temp\\xini.xlsx";
        excel2007Reader(path);
    }
}
