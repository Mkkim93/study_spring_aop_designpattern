package hello.proxy;

import hello.proxy.config.v3_proxyfactory.advice.ProxyFactoryConfigV2;
import hello.proxy.config.v4_postprocessor.postprocessor.BeanProcessorConfig;
import hello.proxy.config.v5_autoproxy.AutoProxyConfig;
import hello.proxy.config.v6_aop.AopConfig;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(AppV1Config.class) // 특정 class 를 스프링 Bean 으로 등록하기 위한 애노테이션
//@Import(InterfaceProxyConfig.class)
// @Import({AppV1Config.class, AppV2Config.class})
//@Import(ConcreteProxyConfig.class)
//@Import(DynamicProxyBasicConfig.class)
// @Import(DynamicProxyFilterConfig.class)
//@Import(ProxyFactoryConfigV1.class)
//@Import(ProxyFactoryConfigV2.class)
//@Import(BeanProcessorConfig.class)
// @Import(AutoProxyConfig.class)
@Import(AopConfig.class)
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의 app 과 app 하위만 컴포넌트 스캔의 대상으로 설정한다.
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}
}
