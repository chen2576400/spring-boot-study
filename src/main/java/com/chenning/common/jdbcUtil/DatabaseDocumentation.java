package com.chenning.common.jdbcUtil;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;

/**
 * @Author nchen
 * @Date 2021/9/23 15:49
 * @Version 1.0
 * @Description 一键生成数据库说明文档
 */
public class DatabaseDocumentation {

    public static void main(String[] args) {
        // 数据源
        HikariConfig hikariConfig = new HikariConfig();
        //hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/learn?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&nullCatalogMeansCurrent=true");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        //// 设置可以获取tables remarks信息
        //hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);

        // 1、生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir("D:\\doctment")
                // 打开目录
                .openOutputDir(false)
                // 文件类型
                .fileType(EngineFileType.WORD)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker).build();

        // 忽略表名
        List<String> ignoreTableName = Arrays.asList("test");
        // 忽略表前缀
        List<String> ignorePrefix = Arrays.asList("a_", "a");
        // 忽略表后缀
        List<String> ignoreSuffix = Arrays.asList("_t", "t");
        // 指定表
        List<String> designatedTableName = Arrays.asList("user");

        // 2、配置想要忽略的表
        // 指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
        ProcessConfig processConfig = ProcessConfig.builder().ignoreTableName(ignoreTableName)
                .ignoreTablePrefix(ignorePrefix).ignoreTableSuffix(ignoreSuffix)
                .designatedTableName(designatedTableName).build();


        // 3、生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder().version("1.0.0").description("数据库文档").dataSource(dataSource)
                .engineConfig(engineConfig).produceConfig(processConfig).build();


        // 4、执行生成
        new DocumentationExecute(config).execute();
    }

}
