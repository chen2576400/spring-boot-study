package com.chenning.common.swagger.controller;

import com.chenning.common.util.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-03-23 14:10
 **/
@RestController
@RequestMapping("/todo")
public class SwaggerTestController {

    @ApiOperation("测试示例-test1")
    @RequestMapping("/test1")
    public Result  test1(){
        return Result.ok();
    }
}
