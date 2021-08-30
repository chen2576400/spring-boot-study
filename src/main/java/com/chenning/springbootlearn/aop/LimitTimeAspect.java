package com.chenning.springbootlearn.aop;

import com.chenning.springbootlearn.util.result.Result;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author nchen
 * @Date 2021/8/7 11:09
 * @Version 1.0
 * @Description
 */
@Log4j2
public class LimitTimeAspect {
    @Getter
    private Map<String, List<Long>> limitMap = new HashMap<>();

    @Pointcut("@annotation(limitTime1)")
    public void pointcut(LimitTime limitTime1) {
    }

    @Around("pointcut(limitAccess)")   //这里名字要和方法注解名称必须相同
    public Object aroundLog(ProceedingJoinPoint point, LimitTime limitAccess) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != attributes) { //if里面retrun直接覆盖原来逻辑 只要不执行point.proceed原来的方法不会走
            String className = point.getTarget().getClass().getName();
            String methodName = point.getSignature().getName();
            HttpServletRequest request = attributes.getRequest();
            String key = className + "." + methodName + "#" + request.getSession().getId();
            List<Long> millisecondList = limitMap.get(key);
            long now = System.currentTimeMillis();
            if (null == millisecondList) {
                List<Long> list = new ArrayList<>();
                list.add(now);
                limitMap.put(key, list);
            } else {
                List<Long> newMillisecondList = new ArrayList<>(millisecondList.size());
                millisecondList.forEach(millisecond -> {
                    // 当前访问时间 - 历史访问时间 < 限制时间
                    if (now - millisecond < limitAccess.timeout()) {
                        newMillisecondList.add(millisecond);
                    }
                });
                // 时间段内超过访问频次上限 - 阻断
                if (newMillisecondList.size() >= limitAccess.time()) {
                    log.info("接口调用过于频繁 {}", key);
                    StringBuffer buffer=new StringBuffer();
                    buffer.append(limitAccess.timeout()/1000);
                    buffer.append("s内只能允许访问");
                    buffer.append(limitAccess.time()+"次");
                    return Result.error(buffer.toString());
                }
                newMillisecondList.add(now);
                // 更新接口访问记录
                limitMap.put(key, newMillisecondList);
            }
        }
        return point.proceed();
    }
}