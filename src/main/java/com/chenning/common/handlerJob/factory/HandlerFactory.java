package com.chenning.common.handlerJob.factory;


import com.chenning.common.handlerJob.handler.BaseHandler;

/**
 * @author: Mr.Chen
 * @create: 2021-02-03 16:26
 **/
public class HandlerFactory {
    public  static BaseHandler getStatisticalHandler(Integer type) throws Exception{
        Class<BaseHandler> clazz= (Class<BaseHandler>) Class.forName("com.chenning.common.handerJobDemon.handler.Handler"+type);
        BaseHandler newInstance = clazz.newInstance();
        return newInstance;
    }
}
