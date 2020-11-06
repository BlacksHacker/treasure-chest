package com.shelton.treasure.dao.mapper;

import com.shelton.treasure.dto.DataDto;
import com.shelton.treasure.dto.DataParamDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName dataMapper
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public interface dataMapper {
    @Select("SELECT phone, email from user where createTime = #{createTime} and countryAbbr = #{countryAbbr} limit ${startNum}, ${size}")
    @Results(value = {
            @Result(column = "biz_tag", property = "key"),
            @Result(column = "max_id", property = "maxId"),
            @Result(column = "step", property = "step"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<DataDto> getDataDtoList(@Param("dataDto") DataParamDto dataParamDto);
}
