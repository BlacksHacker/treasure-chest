package com.shelton.treasure.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONWriter;
import com.shelton.treasure.constant.Constants;
import com.shelton.treasure.dao.DataDao;
import com.shelton.treasure.dao.DataDaoImpl;
import com.shelton.treasure.dto.DataDto;
import com.shelton.treasure.dto.DataParamDto;
import com.shelton.treasure.utils.HttpUtil;
import com.shelton.treasure.utils.PropertiesUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.net.www.http.HttpClient;

import java.net.HttpURLConnection;
import java.util.List;

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
        String url = "";
        HttpURLConnection connection = HttpUtil.getHttpConnection(url);
        if (connection != null){

        }
    }

    public void execute(JSONWriter jsonWriter, int threadNum, String threadName){
        for (int i =0; i < threadNum ; i++){

        }
    }
//    class DataRunner implements Runnable{
//        private DataParamDto dataParamDto;
//        private
//        @Override
//        public void run() {
//          List<DataDto> data = dataDao.getDataList(dataParamDto);
//          if (!CollectionUtils.isEmpty(data)){
//              for (DataDto dto : data){
////                HttpURLConnection
//              }
//          }
//        }
//    }

}
