Controller 类上 
@Api()：表示这个类是 swagger 资源 tags：表示说明内容，只写 tags 就可以省略属性名
value：同样是说明，不过会被 tags 替代，没卵用
----------------------------------------------------------------------------------------------
Controller 方法上
@ApiOperation() ：对方法的说明，注解位于方法上 value：方法的说明 notes：额外注释说明 response：返回的对象
tags：这个方法会被单独摘出来，重新分组，若没有，所有的方法会在一个Controller分组下
----------------------------------------------------------------------------------------------
Controller 方法的参数上 
@ApiParam()：对方法参数的具体说明，用在方法入参括号里，该注解在post请求参数时，参数名不显示 name：参数名 value：参数说明 required：是否必填
============================================================================================== Controller 方法上
@Parameters 里 
@ApiImplicitParam对方法参数的具体说明，用在方法上@ApiImplicitParams之内，该注解在get,post请求参数时，参数名均正常显示 name 参数名称 value 参数的简短描述
required 是否为必传参数 dataType 参数类型，可以为类名，也可以为基本类型（String，int、boolean等）指定也不起作用，没卵用 paramType 参数的传入（请求）类型，可选的值有path, query,
body, header or form。
----------------------------------------------------------------------------------------------
DTO类上 
@ApiModel描述一个Model的信息（一般用在请求参数无法使用@ApiImplicitParam注解进行描述的时候） value model的别名，默认为类名 description model的详细描述
============================================================================================== DTO属性上
@ApiModelProperty
描述一个model的属性 value 属性简短描述 example 属性的示例值 required 是否为必须值
----------------------------------------------------------------------------------------------


swagger2	OpenAPI 3	注解位置
@Api	@Tag(name = “接口类描述”)	Controller 类上
@ApiOperation	@Operation(summary =“接口方法描述”)	Controller 方法上
@ApiImplicitParams	@Parameters	Controller 方法上
@ApiImplicitParam	@Parameter(description=“参数描述”)	Controller 方法上 @Parameters 里
@ApiParam	@Parameter(description=“参数描述”)	Controller 方法的参数上
@ApiIgnore	@Parameter(hidden = true) 或 @Operation(hidden = true) 或 @Hidden	-
@ApiModel	@Schema	DTO类上
@ApiModelProperty	@Schema	DTO属性上


[@ApiParam和@ApiImplicitParam的功能是相同的，但是@ApiImplicitParam的适用范围更广
@ApiParam推荐传入json对象时使用必须在entity对象中定义@ApiModel()且要在每个属性前面加上@ApiModelProperty()]()

|  swagger2	   | OpenAPI 3   |    注解位置 |
|-----|-----|----|
|   @Api  | @Tag(name = “接口类描述”)    | Controller 类上 |
|   @ApiOperation  |  @Operation(summary =“接口方法描述”)   |   Controller 方法上 |
|    @ApiImplicitParams |  @Parameters   |   Controller 方法上 |
|   @ApiImplicitParam	  | @Parameter(description=“参数描述”)	    |  Controller 方法上 @Parameters 里|
| @ApiParam	    |  @Parameter(description=“参数描述”)	   |  Controller 方法的参数上|
|  @ApiIgnore	   |   @Parameter(hidden = true) 或 @Operation(hidden = true) 或 @Hidden	  | -|
|  @ApiModel	   |   @Schema	  |  DTO类上|
| @ApiModelProperty	    |  @Schema	   | DTO属性上|

