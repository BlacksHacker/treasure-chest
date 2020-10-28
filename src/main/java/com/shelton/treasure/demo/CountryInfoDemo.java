package com.shelton.treasure.demo;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang.StringUtils;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Country
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public class CountryInfoDemo {

    public static void excel2007Reader(String filePath){
        File excelFile = null;
        InputStream is = null;
        excelFile = new File(filePath);
        String cellStr = "";
        Workbook workbook2007 = null;
        List<String> tagList = new ArrayList<>();
        Map<String, JSONObject> map = new HashMap<>();
        try {
            is = new FileInputStream(excelFile);
            workbook2007 = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook2007.getSheetAt(0);
        for (int i = 0; i <= sheet.getLastRowNum(); i++){
            System.out.println(sheet.getLastRowNum());
            System.out.println(sheet.getRow(sheet.getLastRowNum()).getCell(1).getStringCellValue());
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
                if ( i == 0 && j >= 6){
                    tagList.add(cellStr);
                }

                if (i >= 2){
                    if (j == 1){
                        map.put(cellStr, new JSONObject());
                    }
                    if (j >= 6){
                        JSONObject obj = map.get(row.getCell(1).getStringCellValue());
                        obj.put(tagList.get(j-6), cellStr);
                    }
                }

            }
        }
        for (Map.Entry<String, JSONObject> entry: map.entrySet()){
            System.out.println("update country set language = '"+entry.getValue()+"' where abbr = '"+entry.getKey()+"';");
        }

    }

    public static void main(String[] args) {
        excel2007Reader("D:\\file\\temp\\Country info.xlsx");
    }
}
