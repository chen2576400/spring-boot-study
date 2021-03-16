package com.chenning.springbootlearn.MybatisPlusDemonTest.mysql;

import com.chenning.springbootlearn.SpringBootLearnApplication;
import com.chenning.springbootlearn.demonBuild.mapper.UserMapper;
import com.chenning.springbootlearn.demonBuild.model.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/16 15:19
 */
@SpringBootTest(classes = SpringBootLearnApplication.class)
@RunWith(SpringRunner.class)
public class MapperTest {
    @Autowired
    UserMapper mapper;


    /** association: 处理一对一、多对一    JavaType  一对一 多对一 映射pojo中属性的类型
        collection: 处理一对多             ofType指定的是一对多 映射到list集合属性中pojo的类型
     * 一对多
     */
    @Test
    public void test1() {
        List<UserVo> userAndCard = mapper.findUserAndCard();
        userAndCard.stream().forEach(userVo -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("stream===============>" + new Timestamp(System.currentTimeMillis()) + "()" + userVo.getUserName());
                }

        );
        userAndCard.parallelStream().forEach(userVo -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("parallelStream===============>" + new Timestamp(System.currentTimeMillis()) + "()" + userVo.getUserName());
                }

        );
    }

    @Test
    public void test2() {
        List<UserVo> userAndCard = mapper.findUserAndCardByID(1);
        userAndCard.parallelStream().forEach(userVo -> System.out.println("===============>" +userVo.getUserName()));
    }

    @Test
    public void test3() {
        List<UserVo> userAndCard = mapper.findUserAndCardInIDs(Arrays.asList(1,2));
        userAndCard.parallelStream().forEach(userVo -> System.out.println("===============>" +userVo.getUserName()));
    }


    @Test
    public void test4() {
        Map map=new HashMap();
        map.put("id",1);
        map.put("name","张");
        List<UserVo> userAndCard = mapper.findUserAndCardByModelParm(map);
        userAndCard.parallelStream().forEach(userVo -> System.out.println("===============>" +userVo.getUserName()));
    }
}
