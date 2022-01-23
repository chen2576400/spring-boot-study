package com.chenning.common.mapStruct;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * @Author nchen
 * @Date 2021/12/2 10:37
 * @Version 1.0
 * @Description
 */
public class Test1 {


    /**
     *单个参数
     */
    @Test
    public void test1() {
        ExampleRole role = new ExampleRole(2L, "administrator", "超级管理员",new Timestamp(System.currentTimeMillis()),"commonName2");
        ExampleUser user = new ExampleUser(1L, "zhangsan", "12345", "17677778888", "123@qq.com", role,new Timestamp(System.currentTimeMillis()),"commonName1");
        //ExampleUser user = new ExampleUser(1L, "zhangsan", "12345", "17677778888", "123@qq.com", null);//为null不会报错
        ExampleUserRoleDto userRoleDto = UserRoleMapper.INSTANCE.toUserRoleDto(user);
        System.out.println(userRoleDto);
    }

    /**
     * 可以使用多个参数
     */
    @Test
    public void test2() {
        ExampleRole role = new ExampleRole(2L, "administrator", "超级管理员",new Timestamp(System.currentTimeMillis()),"commonName2");
        ExampleUser user = new ExampleUser(1L, "zhangsan", "12345", "17677778888", "123@qq.com", role,new Timestamp(System.currentTimeMillis()),"commonName1");
        ExampleUserRoleDto userRoleDto = UserRoleMapper.INSTANCE.toUserRoleDto(user, role);
        System.out.println(userRoleDto);
    }


    /**
     * 直接使用参数作为属性值
     */
    @Test
    public void test3() {
        ExampleRole role = new ExampleRole(2L, "administrator", "超级管理员",new Timestamp(System.currentTimeMillis()),"commonName2");
        ExampleUser user = new ExampleUser(1L, "zhangsan", "12345", "17677778888", "123@qq.com", role,new Timestamp(System.currentTimeMillis()),"commonName1");
        ExampleUserRoleDto userRoleDto = UserRoleMapper.INSTANCE.useParameter(user, "myUserRole");
        System.out.println(userRoleDto);
    }


    /**
     * 直接修改对象值
     */
    @Test
    public void test4() {
        ExampleUserRoleDto exampleUserRoleDto = new ExampleUserRoleDto();
        ExampleRole role = new ExampleRole(2L, "administrator", "超级管理员",new Timestamp(System.currentTimeMillis()),"commonName2");
        ExampleUser user = new ExampleUser(1L, "zhangsan", "12345", "17677778888", "123@qq.com", role,new Timestamp(System.currentTimeMillis()),"commonName1");
        ExampleUserRoleDto update = UserRoleMapper.INSTANCE.update(user, exampleUserRoleDto);
        System.out.println(update);
    }


    /**
     * 集合转换
     */
    @Test
    public void test5() {
        ExampleRole role = new ExampleRole(2L, "administrator", "超级管理员",new Timestamp(System.currentTimeMillis()),"commonName2");
        ExampleUser user = new ExampleUser(1L, "zhangsan", "12345", "17677778888", "123@qq.com", role,new Timestamp(System.currentTimeMillis()),"commonName1");
        List<ExampleUserRoleDto> exampleUserRoleDtos = UserRoleMapper.INSTANCE.toUserRoleDtoList(Arrays.asList(user));
        System.out.println(exampleUserRoleDtos);
    }


}
