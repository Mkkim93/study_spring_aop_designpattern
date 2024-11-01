package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect // 핵심
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    // execute 를 사용하면 @Around 어노테이션을 통해 특정 루트만 포인트컷으로 지정 가능
    @Around("execution(* hello.proxy.app..*(..))") // 포인트컷을 어노테이션으로 지정한다. 포인트컷 어드바이스 어드바이저 지정
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        TraceStatus status = null;

        try {

            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            //로직 호출
            Object result = joinPoint.proceed();
            logTrace.end(status);
            return result;

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
