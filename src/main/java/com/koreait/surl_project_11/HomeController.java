package com.koreait.surl_project_11;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("a")
    @ResponseBody
    public String hi(
            String age,
            String id
    ) {
        return "안녕, %s번, %s살이야.".formatted(id, age);
    }

    @GetMapping("b")
    @ResponseBody
    public String plus(
            //http://localhost:8080/b?a=20&b=40
            @RequestParam("a") int num1,
            @RequestParam("b") int num2,
            @RequestParam(name = "c", defaultValue = "10") int num3
    ) {

        System.out.println("a : " + num1);
        System.out.println("b : " + num2);

        return "a + b + c = %d".formatted(num1 + num2 + num3);
    }

    @GetMapping("c")
    @ResponseBody
    public String c(
            boolean married
    ) {
        return married ? "기혼" : "미혼";
    }

    @GetMapping("d")
    @ResponseBody
    public String d(
            Boolean married
    ) {
        if (married == null) return "정보 입력해";

        return married ? "기혼" : "미혼";
    }

    @Getter
    @Setter
    @ToString //주석처리된 형태로 ToString을 자동으로 Override
    @AllArgsConstructor //필드 변수를 다 써먹을거면 All, 안써먹을거면 No, 필드변수들이 final이면 required
    public static class Person {

        private String name;
        private int age;

//        @Override
//        public String toString() {
//            return "Person{" +
//                    "name='" + name + '\'' +
//                    ", age=" + age +
//                    '}';
//        }
    }

    @GetMapping("person1")
    @ResponseBody
    public String person1(
            String name,
            int age
    ) {
        Person person = new Person(name, age);

        return person.toString();
    }

    @GetMapping("person2")
    @ResponseBody
    public String person2(
            Person person
    ) {
        return person.toString();
    }

    @GetMapping("e")
    @ResponseBody
    public int e() {
        int age = 10;
        return age;
    }

    @GetMapping("f")
    @ResponseBody
    //이렇게 String이 아닌 것을 return 해도, 'jackson'이라는 라이브러리가 알아서 String(JS와의 공용어)로 바꿔서 보냄
    public ArrayList<int[]> f() {
        ArrayList<int[]> arr = new ArrayList<>();
        arr.add(new int[]{1, 2, 3});
        arr.add(new int[]{2});
        arr.add(new int[]{3});

        return arr;
    }

    @AllArgsConstructor
    @Getter //@Data == Getter Setter ToString EqualsAndHashCode RequiredArgsConstructor 다 만들어주는 놈
    @Setter
    @Builder //이 어노테이션이 클래스에 붙어있어야 빌더패턴을 사용할 수 있다.
    @ToString
    public static class Post {
        @ToString.Exclude //이렇게 하면 id는 ToString을 만들 때 제외된다.
        @JsonIgnore //화면에도 표시 안되게 하고싶어! (ToString 때 제외하는 것과는 별개다)
        private Long id;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        @Builder.Default //이 어노테이션이 있어야 "제목이야"라는 디폴트값을 사용할 수 있다.
        private String subject = "제목이야";
        private String body;
    }

    @GetMapping("/posts")
    @ResponseBody
    public List<Post> getPost() {
        List<Post> posts = new ArrayList<>() {{
            //여기서 생성과 동시에 객체 생성
            add(new Post(1L, LocalDateTime.now(), LocalDateTime.now(), "제목1", "내용1"));
            add(new Post(2L, LocalDateTime.now(), LocalDateTime.now(), "제목2", "내용2"));
            add(new Post(3L, LocalDateTime.now(), LocalDateTime.now(), "제목3", "내용3"));
            add(new Post(4L, LocalDateTime.now(), LocalDateTime.now(), "제목4", "내용4"));
            add(new Post(5L, LocalDateTime.now(), LocalDateTime.now(), "제목5", "내용5"));
        }};

        return posts;
    }

    @GetMapping("/posts2")
    @ResponseBody
    public List<Post> getPost2() {
        List<Post> posts = new ArrayList<>() {{
            add(Post
                    .builder()
                    .id(1L)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .subject("제목1")
                    .body("내용1")
                    .build());
            add(Post
                    .builder()
                    .id(2L)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .subject("제목2")
                    .body("내용2")
                    .build());
            add(Post
                    .builder()
                    .id(3L)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .subject("제목3")
                    .body("내용3")
                    .build());
            add(Post
                    .builder()
                    .id(4L)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .subject("제목4")
                    .body("내용4")
                    .build());
        }};

        return posts;
    }

    @GetMapping("/posts3")
    @ResponseBody
    public Post getPost3() {

        Post post = Post.builder()
                .id(1L)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .body("내용1")
                .build();

        System.out.println(post);
        return post;
    }
}

/*
- 우리가 지금 사용하고 있는 방식은 HTTP 통신
- HTTP는 편지를 이용해서 고객(브라우저)과 점원(서버의 컨트롤러)이 대화를 나누는 방법에 대한 규칙이 정해져있다
- 고객 == 클라이언트 == 브라우저 == 서비스 이용자
- 점원 == 주인/사장 == 서버 == 스프링부트 == 서비스 제공자
- 고객은 요청 편지를 점원에게 보내고, 점원은 그에 대한 응답 편지를 보낸다.
- 편지는 header와 body로 나뉜다.
- 헤더에는 부가정보, 바디에는 내용이 담긴다.
- 고객의 생각(데이터)이 점원에게 전해지려면 양쪽이 모두 다 이해할 수 있는 String을 사용해야 한다.
- Why? 브라우저(고객)의 모국어는 자바스크립트, 스프링부트(점원)의 모국어는 자바
==> 따라서 브라우저는 자바의 int, boolean, char, float, 배열, 리스트, 맵... 을 이해할 수 없다. 나한텐 저런 타입이 없으니까.
==> 스프링부트도 브라우저 측의 number, 객체... 를 이해할 수 없다.
==> 동시에 이해하는 게 String이다.
==> 둘이 소통하려면 String을 기본으로 사용해야 한다.
- String만으로 객체를 표현하는 데에 어려움이 있어서 JSON({키, 밸류}), XML(<>태그) 등을 사용 (==String을 위한 수단)
*/
