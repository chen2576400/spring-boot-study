package com.chenning.common.mapStruct;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author nchen
 * @Date 2021/12/2 10:32
 * @Version 1.0
 * @Description
 *
 * @Mapper 只有在接口加上这个注解， MapStruct 才会去实现该接口
 *     @Mapper 里有个 componentModel 属性，主要是指定实现类的类型，有如下4种方式
 *     default: 这是默认的情况，MapStruct不使用任何组件类型, 可以通过Mappers.getMapper(Class)方式获取自动生成的实例对象。
 *     cdi: the generated mapper is an application-scoped CDI bean and can be retrieved via @Inject。
 *     spring: 生成的实现类上面会自动添加一个@Component注解，可以通过Spring的 @Autowired方式进行注入。
 *     jsr330: 生成的实现类上会添加@javax.inject.Named 和@Singleton注解，可以通过 @Inject注解获取。
 *
 * @Mapping：属性映射，若源对象属性与目标对象名字一致，会自动映射对应属性
 *     source：源属性
 *     target：目标属性
 *     dateFormat：String 到 Date 日期之间相互转换，通过 SimpleDateFormat，该值为 SimpleDateFormat 的日期格式
 *     numberFormat：数值格式化, 例："0.00"
 *     expression： 自定义java代码实现属性映射
 *     ignore: 忽略这个字段
 * @Mappings：配置多个@Mapping
 * @MappingTarget 用于更新已有对象
 * @InheritConfiguration 用于继承配置
 *
 *
 */

/**
 * @Mapper 定义这是一个MapStruct对象属性转换接口，在这个类里面规定转换规则
 *          在项目构建时，会自动生成改接口的实现类，这个实现类将实现对象属性值复制
 */
@Mapper
//@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS) //自动跳过非空source属性赋值
public interface UserRoleMapper {
    /**
     * 获取该类自动生成的实现类的实例
     * 接口中的属性都是 public static final 的 方法都是public abstract的
     */
    UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);




    /**
     * 这个方法就是用于实现对象属性复制的方法
     * @Mapping 用来定义属性复制规则 source 指定源对象属性 target指定目标对象属性
     * @param user 这个参数就是源对象，也就是需要被复制的对象
     * @return 返回的是目标对象，就是最终的结果对象
     */
    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "user.username", target = "name"),
            @Mapping(source = "user.role.roleName", target = "roleName"),
            @Mapping(source = "user.userTimestamp", target = "timestamp",dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    ExampleUserRoleDto toUserRoleDto(ExampleUser user);


    /**
     * 自动引用的toUserRoleDto方法  循环添加进去的
     * @param user
     * @return
     */
    List<ExampleUserRoleDto> toUserRoleDtoList(List<ExampleUser> user);



    /**
     * 多个参数中的值绑定
     * @param user 源1
     * @param role 源2
     * @return 从源1、2中提取出的结果
     */
    @Mappings({
            @Mapping(source = "user.id", target = "userId"), // 把user中的id绑定到目标对象的userId属性中
            @Mapping(source = "user.username", target = "name"), // 把user中的username绑定到目标对象的name属性中
            @Mapping(source = "role.roleName", target = "roleName") // 把role对象的roleName属性值绑定到目标对象的roleName中
    })
    ExampleUserRoleDto toUserRoleDto(ExampleUser user, ExampleRole role);


    /**
     * 直接使用参数作为值
     * @param user
     * @param parmName
     * @return
     */
    @Mappings({
            @Mapping(source = "user.id", target = "userId"),  // 把user中的id绑定到目标对象的userId属性中
            @Mapping(source = "user.username", target = "name"), // 把user中的username绑定到目标对象的name属性中
            @Mapping(source = "parmName", target = "roleName")// 把role对象的roleName属性值绑定到目标对象的roleName中
    })
    ExampleUserRoleDto useParameter(ExampleUser user,String parmName);







    /**
     * @MappingTarget来指定目标类是谁（谁的属性需要被更新）。@Mapping还是用来定义属性对应规则
     * source源对象  ->  ExampleUser
     * target 目标对象  ->  ExampleUserRoleDto
     * 相同字段名自动copy
     *
     * @param user
     * @param userRoleDto
     */
    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "username", target = "name"),
            @Mapping(source = "role.roleName", target = "roleName")
    })
    ExampleUserRoleDto update(ExampleUser user, @MappingTarget ExampleUserRoleDto userRoleDto);





}
