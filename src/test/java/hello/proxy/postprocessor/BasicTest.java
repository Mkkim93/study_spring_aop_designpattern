package hello.proxy.postprocessor;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    void basicConfig() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        // A는 Bean 으로 등록된다.
        A a = applicationContext.getBean("beanA", A.class);
        a.helloA();

        // B는 Bean 으로 동록 되지 않는다.

        // Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(A.class));
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(B.class)); // B는 빈으로 등록되지 않았기 때문에 테스트가 성공
    }

    @Slf4j
    @Configuration
    static class BasicConfig {

        @Bean(name = "beanA") // A 라는 클래스를 BasicConfig 를 통해 스프링 빈에 등록하게 된다.
        public A a() {
            return new A();
        }
    }

    @Slf4j
    static class A {
        public void helloA() {
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }
}