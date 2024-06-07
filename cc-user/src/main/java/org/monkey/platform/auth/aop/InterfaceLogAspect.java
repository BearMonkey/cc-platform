package org.monkey.platform.auth.aop;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.monkey.platform.api.common.Result;

/**
 * InterfaceLogAspect
 *
 * @author cc
 * @since 2024/5/31 11:41
 */
@Aspect
@Component
@Slf4j
public class InterfaceLogAspect {
    @Pointcut("execution(* org.monkey.platform.auth.controller.*Controller.*(..))") // 根据实际情况修改包路径
    public void interfaceLoggingPointcut() {
    }

    @Before("interfaceLoggingPointcut()")
    public void logBeforeCall(JoinPoint joinPoint) {
        // 可以记录更多的信息，比如参数值
        Object[] args = joinPoint.getArgs();
        log.info("Method: {}, args: {}", joinPoint.getSignature().getName(), JSONArray.toJSONString(args));
    }

    //@After("interfaceLoggingPointcut()")
    @AfterReturning(pointcut = "interfaceLoggingPointcut()", returning = "result")
    public void logAfterReturn(JoinPoint joinPoint, Result<Object> result) {
        // 可以记录更多的信息，比如参数值
        log.info("Method: {}, result: {}", joinPoint.getSignature().getName(), result);
    }
}
