package com.shelton.treasure.demo;

import com.sun.org.apache.bcel.internal.ExceptionConst;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * @ClassName ExcelReadUtis
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public class IndiaDemo {

    public static void excel2007Reader(String filePath, String outPath)throws Exception {
        File file = new File(outPath);
        if (file.exists()){
            file.delete();
        }
        FileOutputStream out = new FileOutputStream(outPath);
        StringBuilder builder = new StringBuilder();

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
        int count = 0;
        Sheet sheet = workbook2007.getSheetAt(0);
        for (int i = 1; i < sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            for (int j = 1; j < row.getLastCellNum(); j++){
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

                    builder.append("update user_login_info set clientId = '19274432' where loginname = '+84"+cellStr+"';\n");

            }
        }
        byte[] data = builder.toString().getBytes();
        out.write(data);
        out.close();
        System.out.println(count);
    }

    public static void main(String[] args) {
        String path = "D:\\file\\temp\\yuenanall.xlsx";
        String outPath = "D:\\file\\temp\\yuenan.sql";
        try {
            excel2007Reader(path , outPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
