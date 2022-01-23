package com.chenning.common.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 正常情况 @Around @Before 目标方法 @Around @After @AfterReturning;
 * 异常情况 @Around @Before 目标方法 @After @AfterThrowing;
 *
 * @Author nchen
 * @Date 2021/7/6 17:26
 * @Version 1.0
 * @Description
 * @Before: 前置通知, 在方法执行之前执行
 * @After: 后置通知, 在方法执行之后执行 。
 * @AfterRunning: 返回通知, 在方法返回结果之后执行
 * @AfterThrowing: 异常通知, 在方法抛出异常之后
 * @Around: 环绕通知, 围绕着方法执行
 */
@Component
@Aspect
@Log4j2
public class AOPAspect {
    /**
     * execution(* com.sample.service.impl..*.*(..))
     * execution()  表达式的主体；
     * 第一个”*“符号  表示返回值的类型任意；
     * com.sample.service.imp  AOP所切的服务的包名，即，我们的业务部分
     *包名后面的”..“  表示当前包及子包
     *第二个”*“      表示类名，*即所有类。此处可以自定义
     * .*(..)       表示任何方法名，括号表示参数，两个点表示任何参数类型
     */


    /** eg:
     * execution(public * *(..))
     * 匹配所有目标类的public方法，但不匹配SmartSeller和protected voidshowGoods()方法。
     * 第一个*代表返回类型，第二个*代表方法名，而..代表任意入参的方法；
     *
     * execution(* joke(String,int)))
     * 匹 配joke(String,int)方法，且joke()方法的第一个入参是String，第二个入参是int。它匹配NaughtyWaiter#joke(String,int)方法。
     * 如果方法中的入参类型是Java.lang包下的类，可以直接使用类名，否则必须使用全限定类名，如joke(java.util.List,int)；
     *
     *
     *  execution(* joke(String,..)))l
     * 匹配目标类中的joke()方法，该方法第 一个入参为String，
     * 后面可以有任意个入参且入参类型不限，如joke(Strings1)、joke(String s1,String s2)和joke(String s1,double d2,Strings3)都匹配。
     */


    /**
     * 这里我是通过controller调用的当前service (aop动态代理对象要交给容器管理)
     */
    @Pointcut("execution(* com.chenning.common.crud.service.impl.UserServiceImpl.findUserAndCardByID(..))")
    private void pointcut() {
    }


    /**
     * 用@Before标识的方法为前置方法，在目标方法的执行之前执行，即在连接点之前进行执行。
     */
//  @Before("execution(public int lzj.com.spring.aop.ArithmeticCalculator.*(int, int))")
    @Before(value = "pointcut()")
    public void doBefore(JoinPoint point) throws Throwable{
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        System.out.println("uri="+request.getRequestURI());
        String classname = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
//        if (1!=2){//执行这里 拦截方法将不再执行
//            throw new Exception("no privilege");
//        }
        System.out.println("@before Execute! --class name: " + classname + ", method name: " + methodName + " " + args );
    }





    @After(value = "pointcut()")
    public void doAfter(JoinPoint point) throws Throwable{
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        System.out.println("uri="+request.getRequestURI());
        String classname = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
//        if (1!=2){//执行这里 拦截方法将不再执行
//            throw new Exception("no privilege");
//        }
        System.out.println("@before Execute! --class name: " + classname + ", method name: " + methodName + " " + args );
    }


    /**
     * 环绕通知方法可以包含上面四种通知方法，环绕通知的功能最全面。
     * 环绕通知需要携带 ProceedingJoinPoint 类型的参数，
     * 且环绕通知必须有返回值, 返回值即为目标方法的返回值
     *
     *
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object doArroud(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        System.out.println("获取的方法参数为" + args);

        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        System.out.println("获取的方法类名" + className + "获取的方法名" + methodName);
        log.info("@Around：执行目标方法之前...");
        {
            //修改原来方法返回值就不要执行point.proceed() 直接return自己自定义方法返回值
            //修改原来方法参数并执行原来方法   point.proceed(newargs)；
        }

        Object proceed = point.proceed();//只要执行了point.proceed() 就会放行拦截的方法
        log.info("@Around：执行目标方法之后...");
        System.out.println("返回值为" + proceed);
        return proceed;
    }
}
