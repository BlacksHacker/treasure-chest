package com.shelton.treasure.dto;

/**
 * @ClassName DataParamDto
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public class DataParamDto {
    private String createTime;
    private String countryAbbr;
    private int startNum;
    private int size;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCountryAbbr() {
        return countryAbbr;
    }

    public void setCountryAbbr(String countryAbbr) {
        this.countryAbbr = countryAbbr;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
