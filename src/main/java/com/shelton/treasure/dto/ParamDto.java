package com.shelton.treasure.dto;

/**
 * @ClassName ParamDto
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public class ParamDto {
    private String countryAbbr;
    private String language;
    private String acceptPush;
    private String protocol;

    public String getCountryAbbr() {
        return countryAbbr;
    }

    public void setCountryAbbr(String countryAbbr) {
        this.countryAbbr = countryAbbr;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAcceptPush() {
        return acceptPush;
    }

    public void setAcceptPush(String acceptPush) {
        this.acceptPush = acceptPush;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}