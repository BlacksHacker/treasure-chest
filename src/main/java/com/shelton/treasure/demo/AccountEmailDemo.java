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
public class AccountEmailDemo {

    public static void excel2007Reader(String filePath, int start, int max, int type, String desc){
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
        List<List<String>> list = new ArrayList<>();
        List<String> title = new ArrayList<>();
        List<String> country = new ArrayList<>();
        for (int i = 0; i <= max; i++){
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            for (int j = 33; j < row.getLastCellNum(); j++){
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


                if (i == 0){
                    List<String> contentList = new ArrayList<>();
                    list.add(contentList);
                    title.add(cellStr);
                }
                if (i == 1 ){
                    country.add(cellStr);
                }
                if (i >= start)
                {
                    List templeList = list.get(j - 33);
                    templeList.add(cellStr);
                }
            }
        }
        System.out.println("-----------------------------------");
        for (int i = 0; i < list.size(); i++){
            List<String> temp = list.get(i);
//            for (int x = 0; x < temp.size(); x++){
//                System.out.println(x);
//                System.out.println(temp.get(x));
//            }
            //System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            String lang = title.get(i);
            String name = country.get(i);
            String subject = temp.get(0);
            String dear = temp.get(1).replace("[", "").replace("]", "").replace("【","").replace("【", "").replace("name", "USERNAME").replace("+", "");
            String register = temp.get(2).replace("XXX@XX.COM", "E-MAIL");
            String please = temp.get(3);
            String minute = temp.get(4);
            String ifyou= temp.get(5);
            String thank = temp.get(6);
            String no = temp.get(7);
            String tclcom = temp.get(8).replace("tcl", "").replace(".", "").replace("com", "");
            String address = temp.get(9);

            StringBuilder content = new StringBuilder("");
            content.append(dear)
                    .append("</br></br>")
                    .append(register)
                    .append(please)
                    .append("</br></br>")
                    .append("VALIDCODE")
                    .append("</br></br>")
                    .append(minute)
                    .append("</br>")
                    .append(ifyou)
                    .append("</br></br></br>");
            StringBuilder sign = new StringBuilder("");
            sign.append(thank)
                    .append("</br></br>")
                    .append("TCL")
                    .append("</br></br></br></br></br>");
            StringBuilder company = new StringBuilder();
            company.append(no)
                    .append("</br>")
                    .append(tclcom)
                    .append("<a href=\"http://tcl.com/\">tcl.com</a>")
                    .append("</br>")
                    .append(address);
            String descri ="海外官网-" + desc + name +"版本";
//            System.out.println(content);
//            System.out.println(sign);
//            System.out.println(company);
            if (lang.equals("fr-rCA")){
                System.out.println("insert into mail_template (lang, brand, type, subject, alias, content, sign, companyinfo, descri, createTime, updateTime) values ('" + lang + "','defaultbrand'," + type+ ",'"+ subject+"','CLIENTNAME','','','','"+ descri +"', now(),now());");
                System.out.println("update mail_template set content = \""+ content+"\" where lang = \"fr-rCA\";");
                System.out.println("update mail_template set sign = \""+ sign+"\" where lang = \"fr-rCA\";");
                StringBuilder company_2 = new StringBuilder();
                company_2.append(no)
                        .append("</br>")
                        .append(tclcom)
                        .append("<a href=\\\"http://tcl.com/\\\">tcl.com</a>")
                        .append("</br>")
                        .append(address);
                System.out.println("update mail_template set companyinfo = \""+ company_2+"\" where lang = \"fr-rCA\";");
            }else if (lang.equals("sr")){

            }
            else {
                System.out.println("insert into mail_template (lang, brand, type, subject, alias, content, sign, companyinfo, descri, createTime, updateTime) values ('" + lang + "','defaultbrand'," + type+ ",'"+ subject+"','CLIENTNAME','"+ content+"','"+sign+"','"+company+"','"+ descri +"', now(),now());");

            }
        }
    }

    public static void main(String[] args) {
        String path = "D:\\file\\temp\\通讯邮件模板.xlsx";

//
//        excel2007Reader(path, 2, 11, 10, "注册账号");
//        excel2007Reader(path, 12, 21, 4, "登录忘记密码");
//        excel2007Reader(path, 22, 31, 6, "修改流程忘记密码");
        excel2007Reader(path, 32, 41, 9, "邮箱换绑邮箱");
    }
}
