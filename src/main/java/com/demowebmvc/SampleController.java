package com.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

//    method 없으면 모두 허용, 있으면 해당 요청 RequestMethod 값 입력, 두가지 이상 입력 시 {} 사용 ex. method = {RequestMethod.GET, RequestMethod.PUT}
//    consumes : 특정한 타입의 데이터를 담고 있는 요청만 처리하는 핸들러
//    produces : 특정한 타입의 응답을 만드는 핸들러, Accept 해더로 필터링
    @RequestMapping(
            value = "/hello",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
