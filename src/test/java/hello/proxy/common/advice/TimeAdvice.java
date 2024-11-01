package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * MethodInterceptor 는 타겟을 호출/생성할 필요가 없음 (내부에 존재함)
 */
@Slf4j
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

//        Object result = method.invoke(target, args); // 핵심!! target 의 객체에 따라 동적으로 실행된다. 나머지 코드는 모두 동일

        // target 을 호출할 필요가 없다.
        Object result = invocation.proceed();// 메서드나 모든 정보를 꺼낼 수 있다 proceed : 자동으로 target 을 찾아서 실행을 해준다.

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("TimeProxy 종료 resultTime={}", resultTime);

        return result;
    }
}
