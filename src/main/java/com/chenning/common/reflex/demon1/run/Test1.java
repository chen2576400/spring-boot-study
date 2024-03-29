package com.chenning.common.reflex.demon1.run;

import com.chenning.common.SpringBootLearnApplication;
import com.chenning.common.crud.model.User;
import com.chenning.common.quartz.config.SpringContextUtils;
import com.chenning.common.reflex.demon1.service.BaseService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

/**
 * @author: Mr.Chen
 * @create: 2021-02-04 16:40
 **/
@SpringBootTest(classes = SpringBootLearnApplication.class)
@RunWith(SpringRunner.class)
public class Test1 {
    /**
     * Field getField(name)：根据字段名获取某个public的field（包括父类）
     * Field getDeclaredField(name)：根据字段名获取当前类的某个field（不包括父类）
     * Field[] getFields()：获取所有public的field（包括父类）
     * Field[] getDeclaredFields()：获取当前类的所有field（不包括父类）
     */
    @SneakyThrows
    @Test
    public void test() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("哮天犬");
        Method method = null;

        Class<?> aClass;
//               aClass = Class.forName("com.chenning.common.reflex.model.service.BaseService");
//               aClass = SpringContextUtils.getBean(BaseService.class).getClass();//这种方式前提该Demon1类要交给spring管理
        aClass = BaseService.class;
        /* getDeclaredMethod 可以获取任意方法 像，protected修饰的getMethod 只可以获取 修饰符为 public 的方法*/
        method = aClass.getDeclaredMethod("x", User.class);
//      method = aClass.getClass().getDeclaredMethod("x", new Class<?>[]{User.class});


        //newInstance 只适用于无参的 相当于new
        //invoke方法第一个要放类的实例，如果没有类的实例就要写成  method.invoke(BaseService.class.newInstance(), user);
        //这个类的实例可以从容器取 也可以new对象
        //由于我这里BaseService交给了容器管理，所以通过工厂获取该对象可拿到里面的注入对象
        method.invoke(SpringContextUtils.getBean(BaseService.class), user);

    }


}
