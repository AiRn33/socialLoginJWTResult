package Ex.socialLoginJWT.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    /*
        RestTemplate란?
        spring 3.0 부터 지원하는 http 통신에 유용하게 쓸 수 있는 템플릿
        http 서버와의 통신을 단순화하고 RESTful 원칙을 지킬 수 있음
        [참고]
        url : https://velog.io/@soosungp33/%EC%8A%A4%ED%94%84%EB%A7%81-RestTemplate-%EC%A0%95%EB%A6%AC%EC%9A%94%EC%B2%AD-%ED%95%A8
    */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
