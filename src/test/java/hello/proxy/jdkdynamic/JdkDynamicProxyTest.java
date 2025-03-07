package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AImpl target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        // 동적 proxy 인스턴스 생성
        // proxy 인스턴스 내부 파라미터 (로드할 클래스, 가져올 인터페이스 클래스(배열 타입), 핸들러)
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        proxy.call();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }


    @Test
    void dynamicB() {
        BImpl target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        // 동적 proxy 인스턴스 생성
        // proxy 인스턴스 내부 파라미터 (로드할 클래스, 가져올 인터페이스 클래스(배열 타입), 핸들러)
        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);
        proxy.call();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
}
