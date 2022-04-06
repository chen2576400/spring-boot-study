package com.chenning.common.swagger.controller;

import com.chenning.common.crud.model.User;
import com.chenning.common.util.result.Result;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-03-23 14:10
 **/
@RestController
@RequestMapping("/todo")
@Api(tags = "swagger-controller调用")
public class SwaggerTestController {


    /**
     * 语法：@RequestParam(value=”参数名”,required=”true/false”,defaultValue=””)
     * <p>
     * value：参数名
     * <p>
     * required：是否包含该参数，默认为true，表示该请求路径中必须包含该参数，如果不包含就报错。
     * <p>
     * defaultValue：默认参数值，如果设置了该值，required=true将失效，自动为false,如果没有传该参数，就使用默认值
     *
     * @param parm1
     * @return
     */
    @ApiOperation("测试示例-@RequestParam的使用")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "str", value = "接口参数变量", dataType = "String")
    })
    @GetMapping("/test1")
    public Result test1(@RequestParam("str") String parm1) {
        return Result.ok(parm1);
    }


    @ApiOperation("测试示例-@PathVariable的使用")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "str", value = "动态参数地址", dataType = "String")
    })
    @GetMapping("/test2/{str}")
    public Result test2(@PathVariable("str") String str) {
        return Result.ok(str);
    }


    /**
     * 结合swagger只能用post请求传递json
     *
     * @ApiParam推荐传入json对象时使用必须在entity对象中定义@ApiModel()且要在每个属性前面加上@ApiModelProperty()
     *
     * @param user
     * @return
     */
//    @ApiOperation("测试示例-@RequestBody的使用")
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(name ="userId",value ="用户ID",dataType = "Integer"),
//            @ApiImplicitParam(name ="password",value ="用户密码",dataType = "String"),
//            @ApiImplicitParam(name ="userName",value ="用户名称",dataType = "String"),
//            @ApiImplicitParam(name ="address",value ="用户地址",dataType = "String"),
//    })
//    @PostMapping("/test3")
//    public Result  test3(@RequestBody  User user){
//        return Result.ok(user);
//    }
    @ApiOperation("测试示例-@RequestBody的使用")
    @PostMapping("/test3")
    public Result test3( @RequestBody  @ApiParam(name = "User对象说明",
              value = "User对象说明",required = true)User user) {
        return Result.ok(user);
    }












}
