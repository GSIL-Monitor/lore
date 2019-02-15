package mng.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author caopeihe
 */
@Aspect
@Component
public class WebControllerAop {
    /**
     * 指定切点
     * 匹配 com.example.demo.controller包及其子包下的所有类的所有方法
     */
    @Pointcut("execution(public * mng.service..*.*(..))")
    public void webLog() {
    }

    /**
     * 前置通知，方法调用前被调用
     *
     * @param joinPoint JoinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
//        System.out.println("我是前置通知!!!");
//        Object[] obj = joinPoint.getArgs();
//        Signature signature = joinPoint.getSignature();
//        System.out.println("方法：" + signature.getName());
//        System.out.println("方法所在包:" + signature.getDeclaringTypeName());
//        signature.getDeclaringType();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        String[] strings = methodSignature.getParameterNames();
//        System.out.println("参数名：" + Arrays.toString(strings));
//        System.out.println("参数值ARGS : " + Arrays.toString(joinPoint.getArgs()));
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest req = attributes.getRequest();
//        System.out.println("请求URL : " + req.getRequestURL().toString());
//        System.out.println("HTTP_METHOD : " + req.getMethod());
//        System.out.println("IP : " + req.getRemoteAddr());
//        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    /**
     * 处理完请求返回内容
     *
     * @param ret ret
     * @throws Throwable 异常
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws
            Throwable {
//        System.out.println("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     *
     * @param jp JoinPoint
     */
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp) {
        System.out.println("方法异常时执行.....");
    }

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     *
     * @param jp JoinPoint
     */
    @After("webLog()")
    public void after(JoinPoint jp) {
    }

    /**
     * 环绕通知,环绕增强，相当于MethodInterceptor
     *
     * @param pjp JoinPoint
     * @return around
     */
    @Around("webLog()")
    public Object around(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();
        try {
            Object result = pjp.proceed();
            long end = System.currentTimeMillis();
            System.out.println("+++++around " + pjp + "\tUse time : " + (end - start) + " ms!");
            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            System.out.println("+++++around " + pjp + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
