package com.koreait.surl_project_11;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfig {

    //이렇게 하면, 컴포넌트가 아니라 컨피규레이션에 등록된 Bean이지만 컴포넌트와 동일한 역할을 할 수 있게 된다
    //남이 만든 클래스를 컴포넌트로 써먹어야되는데 컴포넌트가 안붙어있을 때 이렇게 할 수 있다
    //(멋대로 컴포넌트를 붙였다가 그의 코드가 망가지는 것을 방지)
    //Component C, D, E의 생성자에 '~ 생성됨' 출력을 해놓고서 출력을 확인해보면, Autowired에 의해 자동으로 만들어지고 있음을 확인할 수 있다
    @Bean
    public ComponentC componentC() {
        return new ComponentC();
    }

    //Bean : 등록
    @Bean
    public ComponentD componentD() {
        return new ComponentD();
    }

    @Bean
    public ComponentE componentE() {
        return new ComponentE();
    }

}
