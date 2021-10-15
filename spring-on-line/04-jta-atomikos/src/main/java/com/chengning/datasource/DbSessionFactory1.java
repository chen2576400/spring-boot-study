package com.chengning.datasource;

import com.atomikos.jdbc.AtomikosDataSourceBean;

import com.chengning.config.DB1Config;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 让sqlsessionFactory作用域区分那些dao层,然后定义sqlSessionFactoryRef 达到每个mapper作用于哪个数据库
 */
@Configuration
@MapperScan(basePackages = {"com.chengning.mapper.db1"}, sqlSessionFactoryRef = "sqlSessionFactoryDb1")
public class DbSessionFactory1 {


    // 配置数据源
    @Bean(name = "db1")
    public DataSource businessDbDataSource(@Qualifier("DB1Config") DB1Config db1Conf) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(db1Conf.getJdbcUrl());
        mysqlXaDataSource.setPassword(db1Conf.getPassword());
        mysqlXaDataSource.setUser(db1Conf.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
    // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("db1");
        return xaDataSource;
    }

    /**
     * 配置SQL会话工厂
     * @param DataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactoryDb1")
    public SqlSessionFactory sqlSessionFactoryDb1(@Qualifier("db1") DataSource DataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(DataSource);
        return factoryBean.getObject();
    }

    /**
     * 配置SQL会话模板
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionTemplateDb1")
    public SqlSessionTemplate sqlSessionTemplateDb1(@Qualifier("sqlSessionFactoryDb1") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
