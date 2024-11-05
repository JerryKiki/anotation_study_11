package com.koreait.surl_project_11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home2") //url에서의 접두사
public class Home2Controller {

    //이 클래스는 ComponentA에 대해 의존성이 있다라고 표현한다
    @Autowired
    private ComponentA componentA;

    public Home2Controller(ComponentA componentA) {
        this.componentA = componentA;
    }

    //RequestMapping이 붙어있으므로 /home2/action1으로 접근해야함
    @GetMapping("/action1")
    @ResponseBody
    public String action() {
        return componentA.action();
    }
}
