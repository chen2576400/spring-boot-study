package com.chenning.common.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

/*      AssertFalse	带注释的元素必须为false
        AssertTrue	带注释的元素必须为true
        DecimalMax	带注释的元素必须是一个数字，其值必须小于或等于指定的最大值
        DecimalMin	带注释的元素必须是一个数字，其值必须大于或等于指定的最小值
        Digits	字段必须为数值，且正数部分不能超过 i 位，小数部分不能超过 j 位,null 元素被视为有效。
        Email	所注解的元素需满足Email格式
        Future	带注释的元素必须是将来的日期
        FutureOrPresent	字段必须为未来的时间或当前的时间
        Past	所注解的元素必须是某个过去的日期
        PastOrPresent	字段必须为过去的时间或当前的时间从
        Max	带注释的元素必须是一个数字，其值必须小于或等于指定的最大值
        Min	带注释的元素必须是一个数字，其值必须大于或等于指定的最小值
        NotBlank	所注解的元素值有内容
        NotEmpty	字段不能为 null 且不能为空,可以作用于字符串，其长度不能为 0,可作用于 Array、Collection、Map，其大小不能为 0
        NotNull	所注解的元素值不能为null  一般用来校验Integer类型不能为空
        Null	所注解的元素值为null
        Pattern	带注释的元素必须是字符串  所注解的元素必须满足给定的正则表达式
        Positive	字段必须为正数，即数值大于 0
        PositiveOrZero	字段必须为正数或 0，即数值大于等于 0
        Negative	字段必须为负数，即数值小于 0
        NegativeOrZero	字段必须为负数或 0，即数值小于等于 0
        Size	所注解的元素必须是String、集合或数组，且长度大小需保证在给定范围之内*/

@Data
@ApiModel("ValidParm参数变量说明")
public class ValidParm {


    /**
     * 名字
     */
    @ApiModelProperty("名字")
    @Size(max = 4)
    @NotNull(message = "name 不能为空")
    private String name;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    @Pattern(regexp = "(^男$|^女$)", message = "sex 值不在可选范围")
    @NotNull(message = "sex 不能为空")
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    @Max(value = 99)
    @Min(value = 1)
    private Integer age;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @Email(message = "请填写正确的邮箱地址")
    private String email;


    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @Pattern(regexp ="(^[1][3,4,5,6,7,8,9][0-9]{9}$)", message = "手机号格式有误")
    @NotNull
    private String mobileNo;

}
