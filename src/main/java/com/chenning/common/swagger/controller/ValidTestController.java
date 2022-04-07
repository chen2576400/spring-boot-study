package com.chenning.common.swagger.controller;

import com.chenning.common.swagger.model.ValidParm;
import com.chenning.common.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-04-06 17:17
 **/

@Api(tags = "swagger-Valid结合调用")
@RestController
@RequestMapping("/todo")
public class ValidTestController {


    @ApiOperation("测试示例-valid单个参数校验")
    @ApiImplicitParam(name = "str", value = "接口参数变量", dataType = "String")
    @GetMapping("/test4")
    public Result test4(@RequestParam("str") @Size(max = 3) String parm1,
                        @NotBlank(message = "parm2不能为空") String parm2) {
        return Result.ok(parm1);
    }





    @ApiOperation("测试示例-valid对象参数校验")
    @PostMapping("/test5")
    public Result test5(@RequestBody @ApiParam @Valid ValidParm validParm) {
        return Result.ok(validParm);
    }



    @ApiOperation("测试示例-valid嵌套对象参数校验")
    @PostMapping("/test6")
    public Result test6(@RequestBody @ApiParam @Valid ValidParm validParm) {
        return Result.ok(validParm);
    }



    @ApiOperation("测试示例-避免常见异常是否纳入全局异常管理")
    @PostMapping("/test7")
    public Result test6(@RequestParam("parm1")Integer parm1) {
        return Result.ok(parm1/0);
    }
}
