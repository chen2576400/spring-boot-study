package com.chenning.common.quartz.config;


import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
    public class SchedulerConfig implements ApplicationListener<ContextRefreshedEvent> {
        @Autowired
        private JobFactory jobFactory;
        @Autowired
        private DataSource primaryDataSource;

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println("任务已经启动..."+event.getSource());
        }

        @Bean
        public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
            //获取配置属性
            PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        //    propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
            //在quartz.properties中的属性被读取并注入后再初始化对象
            propertiesFactoryBean.afterPropertiesSet();
            //创建SchedulerFactoryBean
            SchedulerFactoryBean factory = new SchedulerFactoryBean();
            factory.setQuartzProperties(propertiesFactoryBean.getObject());
            //使用数据源，自定义数据源
            factory.setDataSource(this.primaryDataSource);
            factory.setJobFactory(jobFactory);
            factory.setWaitForJobsToCompleteOnShutdown(true);//这样当spring关闭时，会等待所有已经启动的quartz job结束后spring才能完全shutdown。
            factory.setOverwriteExistingJobs(false);
            //QuartzScheduler 延时启动，应用启动5秒后 QuartzScheduler 再启动
            factory.setStartupDelay(5);
            return factory;
        }


        /*
         * 通过SchedulerFactoryBean获取Scheduler的实例
         */
        @Bean(name="scheduler")
        public Scheduler scheduler() throws IOException {
            return schedulerFactoryBean().getScheduler();
        }


        @Bean
        public QuartzInitializerListener executorListener() {
            return new QuartzInitializerListener();
        }
    }



