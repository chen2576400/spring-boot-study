package com.chenning.common.applicationEvent.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author nchen
 * @Date 2021/7/5 15:16
 * @Version 1.0
 * @Description
 */
@Getter
public class DemonRegisterEvent extends ApplicationEvent {
    private String eventName;

    /**
     *
     * @param eventName  队列名字（key）
     * @param source  传递的对象（data）
     */
    public DemonRegisterEvent(String eventName,Object source) {
        super(source);
        this.eventName = eventName;
    }
}
