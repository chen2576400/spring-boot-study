package com.chenning.common.swagger.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: Mr.Nchen
 * @create: 2022-04-07 10:16
 **/
@Data
public class Prop {
    @NotNull(message = "pid不能为空")
    @Min(value = 1, message = "pid必须为正整数")
    @ApiModelProperty("嵌套模型ID")
    private Long pid;

}
