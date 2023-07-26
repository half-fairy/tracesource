package com.wang.tracesource.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class timeAOP {
    //定义切入点
//    第一个 * 号的位置：表示返回值类型，* 表示所有类型。
//    包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，在本例中指 com.mutest.controller包、子包下所有类的方法。
//    第二个 * 号的位置：表示类名，* 表示所有类。
//            **(..)：这个星号表示方法名，* 表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
    @Pointcut("execution( * com.wang.tracesource.service.impl.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime= System.currentTimeMillis();

        Object proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        //获取当前拦截的方法
        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
        String methodName=signature.getDeclaringTypeName()+"."+signature.getName();

        long totalTime=System.currentTimeMillis()-startTime;
        System.out.println(methodName+"方法的执行时间为："+totalTime);
        return proceed;
    }
}
