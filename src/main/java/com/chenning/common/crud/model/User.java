package com.chenning.common.crud.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>
 *
 * </p>
 *
 * @author chen
 * @since 2021-02-02
 */
@TableName("user")// 数据库表名
@Data
@ApiModel("swagger-user参数实体类说明")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)// 数据库主键字段
    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户名称")
    private String userName;


    @ApiModelProperty("用户地址")
    private String address;


/*    @TableField(exist = false) // 非数据库字段
    private String OtherMation;*/


}
