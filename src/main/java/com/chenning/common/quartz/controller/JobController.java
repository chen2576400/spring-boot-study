package com.chenning.common.quartz.controller;


import com.chenning.common.quartz.config.SpringContextUtils;
import com.chenning.common.quartz.model.ScheduleJob;
import com.chenning.common.quartz.service.QuartzService;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cn")
public class JobController {
    @Autowired
    private QuartzService quartzService;


    //http://localhost:1025/jwt/login?userName=张三&password=123456登录
    //http://localhost:1025/cn/addJob?beanName=userServiceImpl&methodName=findAllUser&cronExpression=*/5 * * * * ?
    //添加一个job
    @RequestMapping(value = "/addJob")
    public String addjob(ScheduleJob scheduleJob) throws Exception {
        String beanName = scheduleJob.getBeanName();
        String cron = scheduleJob.getCronExpression();
        if (null == SpringContextUtils.getBean(beanName)) {
            return "BeanName名称不正确，或没有注册到Spring容器中";
        }
        if (!CronExpression.isValidExpression(cron)) {
            return "cron表达式错误，请重新设置！";
        }
        quartzService.insertJob(scheduleJob);

        return "success";
    }


    //暂停job
    @RequestMapping(value = "/pauseJob")
    public String pausejob(Integer[] jobIds) throws Exception {
        for (Integer jobId : jobIds) {
            quartzService.pausejob(jobId);
        }
        return "success";
    }

    //恢复job
    @RequestMapping(value = "/resumeJob")
    public String resumejob(Integer[] jobIds) throws Exception {
        for (Integer jobId : jobIds) {
            quartzService.resumeJob(jobId);
        }
        return "success";
    }


    //http://localhost:1025/cn/deletJob?jobIds=6&jobIds=4
    //删除job
    @RequestMapping(value = "/deletJob")
    public String deletjob(Integer[] jobIds) throws Exception {
        if (jobIds.length > 0) {
            for (Integer jobId : jobIds) {
                quartzService.deleteJob(jobId);
            }
        }
        return "success";
    }

    //启动job
    @RequestMapping(value = "/runJob")
    public String runjob(Integer[] jobIds) throws Exception {
        if (jobIds.length > 0) {
            for (Integer jobId : jobIds) {
                quartzService.runJob(jobId);
            }
        }
        return "success";
    }


}
