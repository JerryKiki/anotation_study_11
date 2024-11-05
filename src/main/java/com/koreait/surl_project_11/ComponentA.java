package com.koreait.surl_project_11;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//사람이 보려는 주석처럼, 스프링부트에게 이 클래스는 어떤 목적으로 만들었음을 알려주기 위해 어노테이션을 사용한다
//중요 : Controller에서 Autowried 해서 쓰려면 꼭 이게 붙어있어야됨
@Component
@RequiredArgsConstructor //알아서 final 객체가 연결이 된다로 읽으면 됨
public class ComponentA {

    //final이 되고 + RequiredArgsConstructor를 붙여주면... Autowired가 필요없어짐
    //다른 것이긴 한데... '효과'는 같다
    //최근에는 final을 선호 (why? : 짧으니깐)
    private final ComponentB componentB;

    @Autowired //자동으로 객체 연결
    private ComponentC componentC;
    @Autowired //자동으로 객체 연결
    private ComponentD componentD;
    @Autowired //자동으로 객체 연결
    private ComponentE componentE;
    //Component C, D, E의 생성자에 '~ 생성됨' 출력을 해놓고서 출력을 확인해보면, Autowired에 의해 자동으로 만들어지고 있음을 확인할 수 있다

    public String action() {
        return "ComponentA Action / " + componentB.getAction();
    }
}
