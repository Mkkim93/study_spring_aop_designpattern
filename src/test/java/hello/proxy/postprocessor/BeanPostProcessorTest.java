package hello.proxy.postprocessor;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {

    @Test
    void basicConfig() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        // A는 Bean 으로 등록된다.
        B b = applicationContext.getBean("beanA", B.class);
        b.helloB();

        // B는 Bean 으로 동록 되지 않는다.

        // B 클래스가 스프링빈에 등록되어 있으면 오류 발생 ( =NoSuchBeanDefinitionException)
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(B.class));
    }

    @Slf4j
    @Configuration
    static class BeanPostProcessorConfig {

        @Bean(name = "beanA") // A 라는 클래스를 BasicConfig 를 통해 스프링 빈에 등록하게 된다.
        public A a() {
            return new A();
        }

        @Bean
        public AtoBPostProcessor BeanPostProcessor() {
            return new AtoBPostProcessor();
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

    @Slf4j
    static class AtoBPostProcessor implements BeanPostProcessor {

        @Override // 객체 변환 로직
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName={} bean={}", beanName, bean);
            if (bean instanceof A) { // 조건 : 넘어오는 Bean 객체 타입이 A 이면 객체 B를 반환
                return new B();
            }
            return bean;
        }
    }
}