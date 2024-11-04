package com.koreait.surl_project_11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class SurlProject11Application {

    public static void main(String[] args) {
        SpringApplication.run(SurlProject11Application.class, args);
    }

}

//@Controller == 붙는 위치 : 컨트롤러 / 역할 : 해당 클래스의 객체를 스프링부트가 생성하고(1개만) HTTP 요청을 처리할 때마다 사용하도록
//@GetMapping == 붙는 위치 : 메서드 / 역할 : 해당 메서드를 브라우저를 통해서 호출 가능하도록
//@ResponseBody == 붙는 위치 : 메서드 / 역할 : 해당 메서드의 return 값을 String 형태로 변환하여 응답본문으로 설정하도록

//운영환경(실제 서비스 환경)에서는 클라이언트(고객, 브라우저)와 서버(주인, 스프링부트)는 다른 컴퓨터에서 실행된다.
//==> 클라이언트와 서버 사이에는 통신이 필요하다
//브라우저와 스프링부트 사이에는 이미 통신기능이 들어있다. ==> HTTP 방식으로 통신한다.
//SpringBoot 프로젝트에서 ~Controller를 고객과의 소통을 담당하는 컨트롤러로 만들어야 함. -> @Controller
//컨트롤러의 모든 메서드를 외부(브라우저)에서 호출(URL호출)할 수 있도록 만들어야 한다? ==> 이건 X!!!
//특정 메서드를 외부에서 호출 가능한 상태로 만들려면? @GetMapping("경로") 추가 (꼭 Get은 아닐 수도 있음)
//컨트롤러 안에서 필요한 특정 메서드만 골라서 액션 메서드로 만들 수 있다. (==특정 메서드만 URL로 접근해서 동작하게 할 수 있다.)
//고객의 요청(주소창에 입력 enter, F5(재요청))마다 액션 메서드가 실행된다. (O)
//액션 메서드가 수행된 후의 return 값이 해당 요청에 대한 응답으로 화면에 표시되길 원한다면 -> @ResponseBody

//SpringBoot의 목적은 고객의 입장에서 보면 원격지원에 있다.
//==> 직전에 만든 todoApp처럼 고객이 직접 스프링부트가 실행되고 있는 컴퓨터로 와서 입력할 필요가 없다.
//@Controller, @GetMapping, @ResponseBody 같은 것은...
//==> 자바가 이해하는 주석/코멘트(비유적)라고 볼 수 있음 -> 스프링부트에게 개발자(나)의 의도를 알리기 위함
