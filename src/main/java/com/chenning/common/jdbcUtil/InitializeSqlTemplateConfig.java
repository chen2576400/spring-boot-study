package com.chenning.common.jdbcUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Author nchen
 * @Date 2021/5/21 15:23
 * @Version 1.0
 * @Description 初始化加载执行sql文件
 */
@Slf4j
@Configuration
public class InitializeSqlTemplateConfig {

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) throws IOException {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(this.databasePopulator());
        return initializer;
    }


    private Resource[] getResources() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:static/stsql/*.sql");
        System.out.println("加载初始化脚本文件---------start");
        for (Resource resource : resources) {
            log.info(resource.getFilename());
        }
        System.out.println("加载初始化脚本文件---------end");
        return resources;
    }


    private DatabasePopulator databasePopulator() throws IOException {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(this.getResources());
        return populator;
    }


}
