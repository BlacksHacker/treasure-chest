package com.shelton.treasure.demo;

/**
 * @ClassName SqlDemo
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public class SqlDemo {
    public static void main(String[] args) {
        for (int i = 0 ; i < 64 ; i++){
            String table = "user_" + i;
            String abbr = "BH";
            //String sql = "select * from "+ table +" where countryAbbr is null";
            String sql = "update "+ table +" set countryAbbr = '"+ abbr +"' where countryAbbr is null;";
            System.out.println(sql);
        }
    }
}
