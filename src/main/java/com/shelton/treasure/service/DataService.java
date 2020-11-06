package com.shelton.treasure.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.shelton.treasure.constant.Constants;
import com.shelton.treasure.dao.DataDao;
import com.shelton.treasure.dao.DataDaoImpl;
import com.shelton.treasure.utils.PropertiesUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * @ClassName DataService
 * @Description TODO
 * @Author xiaosheng1.li
 **/
@Service("dataService")
@DependsOn("propertiesUtils")
public class DataService {

    private DruidDataSource dataSource;

    private DataDao dataDao;

    public DataService() throws Exception{
        dataSource = new DruidDataSource();
        dataSource.setUrl(PropertiesUtils.getProperty(Constants.JDBC_URL));
        dataSource.setUsername(PropertiesUtils.getProperty(Constants.JDBC_USERNAME));
        dataSource.setPassword(PropertiesUtils.getProperty(Constants.JDBC_PASSWORD));
        dataSource.init();
        this.dataDao = new DataDaoImpl(dataSource);
    }

    public void startJob(int size, int num, String countryAbbr, int level, String appId, String language ){

    }
}
