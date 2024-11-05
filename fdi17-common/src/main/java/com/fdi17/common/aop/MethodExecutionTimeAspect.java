//
//package com.fdi17.task.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class MethodExecutionTimeAspect {
//
//    private static final Logger logger = LoggerFactory.getLogger(MethodExecutionTimeAspect.class);
//
//    // 定义切入点，这里指定为service包下所有类的所有方法
//    @Pointcut("execution(* com.fdi17.task.service.*.*(..))")
//    public void serviceMethods() {}
//
//    // 使用Around增强来围绕切入点执行
//    @Around("serviceMethods()")
//    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//
//        // 执行目标方法
//        Object result = joinPoint.proceed();
//
//        long endTime = System.currentTimeMillis();
//        logger.info("Execution time of {} is {} ms",
//                joinPoint.getSignature().toShortString(),
//                endTime - startTime);
//
//        return result;
//    }
//}
