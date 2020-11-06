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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExcelReadUtis
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public class UserChangeDemo {

    public static void excel2007Reader(String filePath){
        Map<Integer, List<String>> map = new HashMap<>();

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
        Sheet sheet = workbook2007.getSheetAt(0);
        for (int i = 1; i < sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> list = null;
            for (int j = 0; j < row.getLastCellNum(); j++){
                Cell cell = row.getCell(j);
                String value = cell.getStringCellValue();
                int tableNumber = Math.abs(value.hashCode()) % 64;
                list = map.get(tableNumber);
                if (list == null){
                    list = new ArrayList<>();
                    map.put(tableNumber, list);
                }
                list.add(value);
            }
        }
        System.out.println(sheet.getLastRowNum());
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()){
            int key = entry.getKey();
            List<String> dataList = entry.getValue();
            for (String username : dataList){
                System.out.println("update user_" + key +" set protocol = 1, isprivate = 1 where username = '"+username+"';");
            }
        }

    }

    public static void main(String[] args) {
        String path = "D:\\file\\temp\\悉尼用户.xlsx";
        excel2007Reader(path);
    }
}
