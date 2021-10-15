package com.chengning.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * @author nchen
 * @date 2019/6/24 11:42 PM
 * 用于存储分布式锁
 */
@Entity
@Table(name = "method_lock")
public class MethodLock {
    @Id
    //方法名称,作为主键
    private String methodName;

    //占用的线程描述
    private String methodDesc;
    //操作时间
    private Date updateTime;
    //省略 get set方法 ...


    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
