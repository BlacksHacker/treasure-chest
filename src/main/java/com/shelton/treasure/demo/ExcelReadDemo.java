package com.shelton.treasure.demo;

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
 * @ClassName ExcelReadUtils
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public class ExcelReadDemo {

    private static String zhuce = "1~11";
    private static String loginPWD = "1-31-40";
    private static String quickLogin = "1-43-52";
    private static String changepwd = "1-54-63";
    private static String changeEmail = "1-65-74";
    private static String phonebindEmail = "1-76-85";
    private static String emailBindEmail = "1-87-96";

    public static void registerEmail(String filePath){
        File excelFile = null;
        InputStream is = null;
        excelFile = new File(filePath);
        List<List<String>> list = new ArrayList<>();
        int start = 1;
        try {
            is = new FileInputStream(excelFile);
            Workbook workbook2007 = new XSSFWorkbook(is);
            Sheet sheet = workbook2007.getSheetAt(0);
            for (int i = 1; i <= 11; i++){
                int count = 0;
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                for (int j = 4; j < row.getLastCellNum(); j++){
                    Cell cell = row.getCell(j);
                    String value = cell.getStringCellValue();
                    if (i == 1){
                        List<String> countryList = new ArrayList<>();
                        countryList.add(value);
                        list.add(countryList);
                    }else {
                        //Dear
                        if (i == start + 2){
                            value = value +"<br/><br/>";
                        }
                        //Please enter
                        if (i == start + 4){
                            value = value + "<br/><br/>VALIDCODE<br/><br/>";
                        }
                        //This code will
                        if (i == start + 5){
                            value = value + "<br/>";
                        }
                        //if you
                        if (i == start + 6){
                            value = value + "<br/><br/><br/>";
                        }
                        //thank
                        if (i == start + 7){
                            value = value + "<br/><br/>TCL<br/><br/><br/><br/><br/><br/>";
                        }
                        //please
                        if (i == start + 10){
                            value = value + "<br/>TCL Corporation, < a href='http://tcl.com/'>tcl.com</ a><br/>Block F5, TCL International E City, Zhong Shan Yuan Road, Nanshan District, Shenzhen, Guangdong, P.R. China<br/>";
                        }
                        value = value.replace("+【username】", "USERNAME").replace("【图片验证码】","").replace("（阳性）", "").replace("（阴性）","").replace(" + 【username】", "USERNAME").replace("EMAIL","E-MAIL");
                        value = value.replace("TCL (TCL为专有名词，不需翻译)", "");
                        value = value.replace("親愛的 USERNAME", "親愛的USERNAME");
                        value = value.replace("【username】", "USERNAME");
                        value = value.replace("【圖片驗證碼】", "");
                        list.get(j - 4).add(value);
                    }
//                    System.out.println(value);
//                    System.out.println(list.size());
                    count++;
                }
//                System.out.println("------------------------------------------" + count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++){
//            System.out.println(i);
//            System.out.println(list.get(i));
        }

        for (int i = 0; i< list.get(0).size(); i++){
//            System.out.println(i);
//            System.out.println(list.get(0).get(i));
        }

        Map<String, String> map = new HashMap();
        map.put("sv", "瑞典语");
        map.put("et", "爱沙尼亚语");
        map.put("da", "丹麦语");
        map.put("fi","芬兰语");
        map.put("no","挪威语");
        map.put("ro","罗马尼亚语");
        map.put("lv","拉脱维亚语");
        map.put("sr","塞尔维亚语");
        map.put("zh-rHK","繁体中文");
        map.put("he","希伯来语");

        System.out.println(list.size());


        for (int x = 0; x < list.size(); x++){

            StringBuilder sqlBuilder = new StringBuilder("");
            StringBuilder sqlBuilder2 = new StringBuilder("");
            List<String> one = list.get(x);
            String lang = one.get(0);
            String subject = one.get(1);
            String content = one.get(2)+one.get(3)+one.get(4)+one.get(5)+one.get(6);
            String sign = one.get(7);
            String companyinfo = one.get(9)+one.get(10);
            String chinese = map.get(lang);
            if (x < 19){
                sqlBuilder.append("update mail_template set subject = \""+subject+"\",content=\""+content+"\",sign=\""+sign+"\", updateTime = now()").append(" where lang = \""+lang+"\" and type = 2;");
            }else {
                sqlBuilder.append("insert into mail_template (lang, brand, type, subject, alias, content, sign, descri, createTime, updateTime) values")
                        .append("(\""+lang+"\",\"defaultbrand\", 2, \""+subject+"\", \"CLIENTNAME\", \""+content+"\", \""+sign+"\", \"海外官网-注册模板"+chinese+"\", now(), now());");
            }
            sqlBuilder2.append("update mail_template set companyinfo = \"" + companyinfo + "\"").append(" where lang = \""+lang+"\" and type = 2;");
            System.out.println(sqlBuilder.toString());
            System.out.println(sqlBuilder2.toString());
        }
    }

    public static void forgetPWD(String filePath){
        File excelFile = null;
        InputStream is = null;
        String cellStr = null;
        excelFile = new File(filePath);
        List<List<String>> list = new ArrayList<>();
        int start = 30;
        try {
            is = new FileInputStream(excelFile);
            Workbook workbook2007 = new XSSFWorkbook(is);
            Sheet sheet = workbook2007.getSheetAt(0);
            for (int i = 1; i <= 40; i++){
//                System.out.println("row-----" + i);
                if (i > 1 && i < 31){
//                    System.out.println("row------"+i);
                    continue;
                }
                int count = 0;
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                for (int j = 4; j < row.getLastCellNum(); j++){
                    Cell cell = row.getCell(j);
                    String value = cell.getStringCellValue();
                    if (i == 1){
                        List<String> countryList = new ArrayList<>();
                        countryList.add(value);
                        list.add(countryList);
                    }else {
                        //Dear
                        if (i == start + 2){
                            value = value +"<br/><br/>";
                        }
                        //Please enter
                        if (i == start + 4){
                            value = value + "<br/><br/>VALIDCODE<br/><br/>";
                        }
                        //This code will
                        if (i == start + 5){
                            value = value + "<br/>";
                        }
                        //if you
                        if (i == start + 6){
                            value = value + "<br/><br/><br/>";
                        }
                        //thank
                        if (i == start + 7){
                            value = value + "<br/><br/>TCL<br/><br/><br/><br/><br/><br/>";
                        }
                        //please
                        if (i == start + 10){
                            value = value + "<br/>TCL Corporation, < a href='http://tcl.com/'>tcl.com</ a><br/>Block F5, TCL International E City, Zhong Shan Yuan Road, Nanshan District, Shenzhen, Guangdong, P.R. China<br/>";
                        }
                        value = value.replace("+【username】", "USERNAME").replace("【图片验证码】","").replace("（阳性）", "").replace("（阴性）","").replace(" + 【username】", "USERNAME").replace("EMAIL","E-MAIL");
                        value = value.replace("TCL (TCL为专有名词，不需翻译)", "");
                        value = value.replace("親愛的 USERNAME", "親愛的USERNAME");
                        value = value.replace("【username】", "USERNAME");
                        value = value.replace("【圖片驗證碼】", "");
                        list.get(j - 4).add(value);
                    }
//                    System.out.println(value);
//                    System.out.println(list.size());
                    count++;
                }
//                System.out.println("------------------------------------------" + count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++){
//            System.out.println(i);
//            System.out.println(list.get(i));
        }

        for (int i = 0; i< list.get(0).size(); i++){
//            System.out.println(i);
//            System.out.println(list.get(0).get(i));
        }

//        Map<String, String> map = new HashMap();
//        map.put("sv", "瑞典语");
//        map.put("et", "爱沙尼亚语");
//        map.put("da", "丹麦语");
//        map.put("fi","芬兰语");
//        map.put("no","挪威语");
//        map.put("ro","罗马尼亚语");
//        map.put("lv","拉脱维亚语");
//        map.put("sr","塞尔维亚语");
//        map.put("zh-rHK","繁体中文");
//        map.put("he","希伯来语");
        Map<String, String> map = getMap();

        System.out.println(list.size());


        for (int x = 0; x < list.size(); x++){

            StringBuilder sqlBuilder = new StringBuilder("");
            StringBuilder sqlBuilder2 = new StringBuilder("");
            List<String> one = list.get(x);
            String lang = one.get(0);
            String subject = one.get(1);
            String content = one.get(2)+one.get(3)+one.get(4)+one.get(5)+one.get(6);
            String sign = one.get(7);
            String companyinfo = one.get(9)+one.get(10);
            String chinese = map.get(lang);
//            if (x < 19){
//                sqlBuilder.append("update mail_template set subject = \""+subject+"\",content=\""+content+"\",sign=\""+sign+"\", updateTime = now()").append(" where lang = \""+lang+"\" and type = 2;");
//            }else {
                sqlBuilder.append("insert into mail_template (lang, brand, type, subject, alias, content, sign, descri, createTime, updateTime) values")
                        .append("(\""+lang+"\",\"defaultbrand\", 4, \""+subject+"\", \"CLIENTNAME\", \""+content+"\", \""+sign+"\", \"海外官网-登录忘记密码"+chinese+"版本\", now(), now());");
//            }
            sqlBuilder2.append("update mail_template set companyinfo = \"" + companyinfo + "\"").append(" where lang = \""+lang+"\" and type = 4;");
            System.out.println(sqlBuilder.toString());
            System.out.println(sqlBuilder2.toString());
        }
    }

    public static void quckLogin(String filePath){
        File excelFile = null;
        InputStream is = null;
        String cellStr = null;
        excelFile = new File(filePath);
        List<List<String>> list = new ArrayList<>();
        int start = 42;
        try {
            is = new FileInputStream(excelFile);
            Workbook workbook2007 = new XSSFWorkbook(is);
            Sheet sheet = workbook2007.getSheetAt(0);
            for (int i = 1; i <= 52; i++){
//                System.out.println("row-----" + i);
                if (i > 1 && i < 43){
//                    System.out.println("row------"+i);
                    continue;
                }
                int count = 0;
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                for (int j = 4; j < row.getLastCellNum(); j++){
                    Cell cell = row.getCell(j);
                    String value = cell.getStringCellValue();
                    if (i == 1){
                        List<String> countryList = new ArrayList<>();
                        countryList.add(value);
                        list.add(countryList);
                    }else {
                        //Dear
                        if (i == start + 2){
                            value = value +"<br/><br/>";
                        }
                        //Please enter
                        if (i == start + 4){
                            value = value + "<br/><br/>VALIDCODE<br/><br/>";
                        }
                        //This code will
                        if (i == start + 5){
                            value = value + "<br/>";
                        }
                        //if you
                        if (i == start + 6){
                            value = value + "<br/><br/><br/>";
                        }
                        //thank
                        if (i == start + 7){
                            value = value + "<br/><br/>TCL<br/><br/><br/><br/><br/><br/>";
                        }
                        //please
                        if (i == start + 10){
                            value = value + "<br/>TCL Corporation, < a href='http://tcl.com/'>tcl.com</ a><br/>Block F5, TCL International E City, Zhong Shan Yuan Road, Nanshan District, Shenzhen, Guangdong, P.R. China<br/>";
                        }
                        value = value.replace("+【username】", "USERNAME").replace("【图片验证码】","").replace("（阳性）", "").replace("（阴性）","").replace(" + 【username】", "USERNAME").replace("EMAIL","E-MAIL");
                        value = value.replace("TCL (TCL为专有名词，不需翻译)", "");
                        value = value.replace("親愛的 USERNAME", "親愛的USERNAME");
                        value = value.replace("【username】", "USERNAME");
                        value = value.replace("【圖片驗證碼】", "");
                        list.get(j - 4).add(value);
                    }
//                    System.out.println(value);
//                    System.out.println(list.size());
                    count++;
                }
//                System.out.println("------------------------------------------" + count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++){
//            System.out.println(i);
//            System.out.println(list.get(i));
        }

        for (int i = 0; i< list.get(0).size(); i++){
//            System.out.println(i);
//            System.out.println(list.get(0).get(i));
        }

//        Map<String, String> map = new HashMap();
//        map.put("sv", "瑞典语");
//        map.put("et", "爱沙尼亚语");
//        map.put("da", "丹麦语");
//        map.put("fi","芬兰语");
//        map.put("no","挪威语");
//        map.put("ro","罗马尼亚语");
//        map.put("lv","拉脱维亚语");
//        map.put("sr","塞尔维亚语");
//        map.put("zh-rHK","繁体中文");
//        map.put("he","希伯来语");
        Map<String, String> map = getMap();

        System.out.println(list.size());


        for (int x = 0; x < list.size(); x++){

            StringBuilder sqlBuilder = new StringBuilder("");
            StringBuilder sqlBuilder2 = new StringBuilder("");
            List<String> one = list.get(x);
            String lang = one.get(0);
            String subject = one.get(1);
            String content = one.get(2)+one.get(3)+one.get(4)+one.get(5)+one.get(6);
            String sign = one.get(7);
            String companyinfo = one.get(9)+one.get(10);
            String chinese = map.get(lang);
//            if (x < 19){
//                sqlBuilder.append("update mail_template set subject = \""+subject+"\",content=\""+content+"\",sign=\""+sign+"\", updateTime = now()").append(" where lang = \""+lang+"\" and type = 2;");
//            }else {
            sqlBuilder.append("insert into mail_template (lang, brand, type, subject, alias, content, sign, descri, createTime, updateTime) values")
                    .append("(\""+lang+"\",\"defaultbrand\", 5, \""+subject+"\", \"CLIENTNAME\", \""+content+"\", \""+sign+"\", \"海外官网-快速登录"+chinese+"版本\", now(), now());");
//            }
            sqlBuilder2.append("update mail_template set companyinfo = \"" + companyinfo + "\"").append(" where lang = \""+lang+"\" and type = 5;");
            System.out.println(sqlBuilder.toString());
            System.out.println(sqlBuilder2.toString());
        }
    }

    public static void changePwd(String filePath, int max, int min, String title, int type){
        File excelFile = null;
        InputStream is = null;
        String cellStr = null;
        excelFile = new File(filePath);
        List<List<String>> list = new ArrayList<>();
        int start = min - 1;
        try {
            is = new FileInputStream(excelFile);
            Workbook workbook2007 = new XSSFWorkbook(is);
            Sheet sheet = workbook2007.getSheetAt(0);
            for (int i = 1; i <= max; i++){
//                System.out.println("row-----" + i);
                if (i > 1 && i < min){
//                    System.out.println("row------"+i);
                    continue;
                }
                int count = 0;
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                for (int j = 4; j < row.getLastCellNum(); j++){
                    Cell cell = row.getCell(j);
                    String value = cell.getStringCellValue();
                    if (i == 1){
                        List<String> countryList = new ArrayList<>();
                        countryList.add(value);
                        list.add(countryList);
                    }else {
                        //Dear
                        if (i == start + 2){
                            value = value +"<br/><br/>";
                        }
                        //Please enter
                        if (i == start + 4){
                            value = value + "<br/><br/>VALIDCODE<br/><br/>";
                        }
                        //This code will
                        if (i == start + 5){
                            value = value + "<br/>";
                        }
                        //if you
                        if (i == start + 6){
                            value = value + "<br/><br/><br/>";
                        }
                        //thank
                        if (i == start + 7){
                            value = value + "<br/><br/>TCL<br/><br/><br/><br/><br/><br/>";
                        }
                        //please
                        if (i == start + 10){
                            value = value + "<br/>TCL Corporation, < a href='http://tcl.com/'>tcl.com</ a><br/>Block F5, TCL International E City, Zhong Shan Yuan Road, Nanshan District, Shenzhen, Guangdong, P.R. China<br/>";
                        }
                        value = value.replace("+【username】", "USERNAME").replace("【图片验证码】","").replace("（阳性）", "").replace("（阴性）","").replace(" + 【username】", "USERNAME").replace("EMAIL","E-MAIL");
                        value = value.replace("TCL (TCL为专有名词，不需翻译)", "");
                        value = value.replace("親愛的 USERNAME", "親愛的USERNAME");
                        value = value.replace("【username】", "USERNAME");
                        value = value.replace("【圖片驗證碼】", "");
                        list.get(j - 4).add(value);
                    }
//                    System.out.println(value);
//                    System.out.println(list.size());
                    count++;
                }
//                System.out.println("------------------------------------------" + count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++){
//            System.out.println(i);
//            System.out.println(list.get(i));
        }

        for (int i = 0; i< list.get(0).size(); i++){
//            System.out.println(i);
//            System.out.println(list.get(0).get(i));
        }

//        Map<String, String> map = new HashMap();
//        map.put("sv", "瑞典语");
//        map.put("et", "爱沙尼亚语");
//        map.put("da", "丹麦语");
//        map.put("fi","芬兰语");
//        map.put("no","挪威语");
//        map.put("ro","罗马尼亚语");
//        map.put("lv","拉脱维亚语");
//        map.put("sr","塞尔维亚语");
//        map.put("zh-rHK","繁体中文");
//        map.put("he","希伯来语");
        Map<String, String> map = getMap();

        System.out.println(list.size());


        for (int x = 0; x < list.size(); x++){

            StringBuilder sqlBuilder = new StringBuilder("");
            StringBuilder sqlBuilder2 = new StringBuilder("");
            List<String> one = list.get(x);
            String lang = one.get(0);
            String subject = one.get(1);
            String content = one.get(2)+one.get(3)+one.get(4)+one.get(5)+one.get(6);
            String sign = one.get(7);
            String companyinfo = one.get(9)+one.get(10);
            String chinese = map.get(lang);
//            if (x < 19){
//                sqlBuilder.append("update mail_template set subject = \""+subject+"\",content=\""+content+"\",sign=\""+sign+"\", updateTime = now()").append(" where lang = \""+lang+"\" and type = 2;");
//            }else {
            sqlBuilder.append("insert into mail_template (lang, brand, type, subject, alias, content, sign, descri, createTime, updateTime) values")
                    .append("(\""+lang+"\",\"defaultbrand\","+ type +" , \""+subject+"\", \"CLIENTNAME\", \""+content+"\", \""+sign+"\", \"海外官网-"+title+chinese+"版本\", now(), now());");
//            }
            sqlBuilder2.append("update mail_template set companyinfo = \"" + companyinfo + "\"").append(" where lang = \""+lang+"\" and type = "+ type+";");
            System.out.println(sqlBuilder.toString());
            System.out.println(sqlBuilder2.toString());
        }
    }


    public static void main(String[] args) {
        String path = "D:\\file\\temp\\译文-邮箱短信模板20201022.xlsx";
        //registerEmail(path);
//        forgetPWD(path);
 //       quckLogin(path);
     //   changePwd(path, 63, 54, "（忘记密码）修改密码流程", 6);
       // changePwd(path, 74,65, "换绑邮箱（登录态)", 7);
        //changePwd(path, 85, 76, "手机绑定邮箱", 8);
        //changePwd(path, 96, 87, "邮箱换绑邮箱", 9);
        changePwd(path, 11, 2, "注册账号（通讯更新最新版）", 10);
        //
    }
    public static Map<String, String> getMap(){
        String  hanyu= "英语\t德语\t法语\t越南语\t泰语\t缅甸语\t葡萄牙语\t西班牙语\t阿拉伯语\t意大利语\t波兰语\t捷克语\t匈牙利语\t印尼语\t斯洛伐克语\t菲律宾语\t乌克兰语\t荷兰语\t希腊语\t瑞典语\t爱沙尼亚语\t丹麦语\t芬兰语\t挪威语\t罗马尼亚语\t拉脱维亚语\t塞尔维亚语\t繁体中文\t希伯来语";
        String yingyu = "en\tde\tfr\tvi\tth\tmy\tpt\tes\tar\tit\tpl\tcs\thu\tid\tsk\ttl\tuk\tnl\tel\tsv\tet\tda\tfi\tno\tro\tlv\tsr\tzh-rHK\the";
        String[] array1 = hanyu.split("\t");
        String[] array2 = yingyu.split("\t");
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < array1.length; i++){
            map.put(array2[i], array1[i]);
        }
        return map;
    }
}
