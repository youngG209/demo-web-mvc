package com.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {

    @GetMapping("/file")
    public String fileUploadForm() {
        return "files/index";
    }

//    MultipartFile
//        ● 파일 업로드시 사용하는 메소드 아규먼트
//        ● MultipartResolver 빈이 설정 되어 있어야 사용할 수 있다. (스프링 부트 자동 설정이 해 줌)
//        ● POST multipart/form-data 요청에 들어있는 파일을 참조할 수 있다.
//        ● List<MultipartFile> 아큐먼트로 여러 파일을 참조할 수도 있다.
    @PostMapping("/file")
    public String fileUpload(@RequestParam MultipartFile file,
                             RedirectAttributes redirectAttributes) {
//        save process
        String message = file.getOriginalFilename() + " is uploaded";
        System.out.println(message);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/file";
    }
}
