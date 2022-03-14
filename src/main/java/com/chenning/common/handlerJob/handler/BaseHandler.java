package com.chenning.common.handlerJob.handler;

import com.chenning.common.handlerJob.DemonModel;
import lombok.Data;

import java.util.Map;


@Data
public abstract class BaseHandler {
    private DemonModel demonModel;

    public abstract void handler(Map<String, Object> params);

}
