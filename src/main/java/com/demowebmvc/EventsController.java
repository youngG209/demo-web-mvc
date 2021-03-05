package com.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EventsController {

//    @InitBinder :
//        특정 컨트롤러에서 바인딩 또는 검증 설정을 변경하고 싶을 때 사용
//    바인딩 설정
//        ● webDataBinder.setDisallowedFields();
//    포매터 설정
//        ● webDataBinder.addCustomFormatter();
//    Validator 설정
//        ● webDataBinder.addValidators();
//    특정 모델 객체에만 바인딩 또는 Validator 설정을 적용하고 싶은 경우
//        ● @InitBinder(“event”)
    @InitBinder
    public void initEventBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

//    @ModelAttribute의 다른 용법
//        ● @RequestMapping을 사용한 핸들러 메소드의 아규먼트에 사용하기 (이미 살펴봤습니다.)
//        ● @Controller 또는 @ControllerAdvice (이 애노테이션은 뒤에서 다룹니다.)를 사용한 클래스에서 모델 정보를 초기화 할 때 사용한다.
//        ● @RequestMapping과 같이 사용하면 해당 메소드에서 리턴하는 객체를 모델에 넣어 준다.
//            ○ RequestToViewNameTranslator
    @ModelAttribute
    public void categories(Model model) {
        model.addAttribute("categories", List.of("study","study","study","study"));
    }

    @GetMapping("/event/form")
    public String eventForm(Model model) {
        Event newEvent = new Event();
        newEvent.setLimit(20);
        newEvent.setName("lee");
        model.addAttribute("event", newEvent);
        return "/event/form";
    }

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

//    @RequestParam
//        ● 요청 매개변수에 들어있는 단순 타입 데이터를 메소드 아규먼트로 받아올 수 있다.
//        ● 값이 반드시 있어야 한다.
//            ○ required=false 또는 Optional을 사용해서 부가적인 값으로 설정할 수도 있다.
//        ● String이 아닌 값들은 타입 컨버전을 지원한다.
//        ● Map<String, String> 또는 MultiValueMap<String, String>에 사용해서 모든 요청 매개변수를 받아 올 수도 있다.
//        ● 이 애노테이션은 생략 할 수 잇다.
    @PostMapping("/event2")
    @ResponseBody
    public Event getEventParam(@RequestParam String name, @RequestParam Integer limit) {
        Event event = new Event();
        event.setName(name);
        event.setLimit(limit);
        return event;

    }

//    @ModelAttribute
//        ● 여러 곳에 있는 단순 타입 데이터를 복합 타입 객체로 받아오거나 해당 객체를 새로 만들 때 사용할 수 있다.
//        ● 여러 곳? URI 패스, 요청 매개변수, 세션 등
//        ● 생략 가능
//    값을 바인딩 할 수 없는 경우에는?
//        ● BindException 발생 400 에러
//    바인딩 에러를 직접 다루고 싶은 경우
//        ● BindingResult 타입의 아규먼트를 바로 오른쪽에 추가한다.
//    바인딩 이후에 검증 작업을 추가로 하고 싶은 경우
//        ● @Valid 또는 @Validated 애노테이션을 사용한다.
//          -   스프링 MVC 핸들러 메소드 아규먼트에 사용할 수 있으며 validation group이라는 힌트를 사용할 수 있다.
//          -   @Valid 애노테이션에는 그룹을 지정할 방법이 없다.
//              해당 애노테이션 사용을 위해 dependency spring-boot-starter-validation 추가해야함(spring 3.4이후)
//          -   @Validated는 스프링이 제공하는 애노테이션으로 그룹 클래스를 설정할 수 있다.
    @PostMapping("/event3")
    @ResponseBody
    public Event getEventModelAttribute(@Validated(Event.ValidateLimit.class) @ModelAttribute Event event,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println("-----------------------------------------------------");
            bindingResult.getAllErrors().forEach(objectError -> {
                System.out.println(objectError.toString());
            });
        }
        return event;
    }

//    Post / Redirect / Get (PRG) 패턴
//        ● https://en.wikipedia.org/wiki/Post/Redirect/Get
//        ● Post 이후에 브라우저를 리프래시 하더라도 폼 서브밋이 발생하지 않도록 하는 패턴
    @PostMapping("/event4")
    public String CreateEvent(@Validated @ModelAttribute Event event,
                           BindingResult bindingResult,
                           Model model){
        if (bindingResult.hasErrors()) {
            System.out.println("-----------------------------------------------------");
            bindingResult.getAllErrors().forEach(objectError -> {
                System.out.println(objectError.toString());
            });
            return "/event/form";
        }

        // DB 저장 코드
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        model.addAttribute("eventList", eventList);
//        get으로 이동 하여 리스트 뽑아 보여줌
//        return "redirect:/event/list";
        return "/event/list";
    }

//    @GetMapping("/event/list")
//    public String getEvent(Model model){
//        DB로 부터 list 가지고옴
//        Event event = new Event();
//        event.setLimit(20);
//        event.setName("lee");
//         
//        List<Event> eventList = new ArrayList<>();
//        eventList.add(event);
//
//        model.addAttribute("eventList", eventList);
//
//        return "/event/list";
//    }
}
