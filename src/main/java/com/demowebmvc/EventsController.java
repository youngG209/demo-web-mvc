package com.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class EventsController {

//    @PathVariable
//        ● 요청 URI 패턴의 일부를 핸들러 메소드 아규먼트로 받는 방법.
//        ● 타입 변환 지원.
//        ● (기본)값이 반드시 있어야 한다.
//        ● Optional 지원.
//    @MatrixVariable
//        ● 요청 URI 패턴에서 키/값 쌍의 데이터를 메소드 아규먼트로 받는 방법
//        ● 타입 변환 지원.
//        ● (기본)값이 반드시 있어야 한다.
//        ● Optional 지원.
//        ● 이 기능은 기본적으로 비활성화 되어 있음. 활성화 하려면 WebMvcConfigurer 설정 중 configurePathMatch 메소드 추가 해야함
//    Optional
//      NPE를 유발할 수 있는 null을 직접 다루지 않아도 됩니다.
//      수고롭게 null 체크를 직접 하지 않아도 됩니다.
//      명시적으로 해당 변수가 null일 수도 있다는 가능성을 표현할 수 있습니다. (따라서 불필요한 방어 로직을 줄일 수 있습니다.)
    @GetMapping("/event/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable Optional<Integer> id, @MatrixVariable String name) {
        Event event = new Event();
        if (id.isPresent())
            event.setId(id.get());
        else
            event.setId(0);
        event.setName(name);
        return event;

    }
}
