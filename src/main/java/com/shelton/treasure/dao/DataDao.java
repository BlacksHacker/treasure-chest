package com.shelton.treasure.dao;

import com.shelton.treasure.dto.DataDto;
import com.shelton.treasure.dto.DataParamDto;

import java.util.List;

/**
 * @ClassName DataDao
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public interface DataDao {
    List<DataDto> getDataList(DataParamDto dataParamDto);
}
