package com.shelton.treasure.dao;

import com.shelton.treasure.dto.DataDto;
import com.shelton.treasure.dto.DataParamDto;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.List;

/**
 * @ClassName DataDaoImpl
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public class DataDaoImpl implements DataDao {
    SqlSessionFactory sqlSessionFactory;

    public DataDaoImpl(DataSource dataSource) {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(DataDaoImpl.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    @Override
    public List<DataDto> getDataList(DataParamDto dataParamDto) {
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        try {
            return sqlSession.selectList("com.tcl.user.account.idcenter.core.segment.dao.IDAllocMapper.getAllLeafAllocs");
        } finally {
            sqlSession.close();
        }
    }
}
