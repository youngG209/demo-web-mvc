package com.demowebmvc;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private ResourceLoader resourceLoader;

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

//    파일 리소스를 읽어오는 방법
//        ● 스프링 ResourceLoader 사용하기
//    파일 다운로드 응답 헤더에 설정할 내용
//        ● Content-Disposition: 사용자가 해당 파일을 받을 때 사용할 파일 이름
//        ● Content-Type: 어떤 파일인가
//        ● Content-Length: 얼마나 큰 파일인가
//    파일의 종류(미디어 타입) 알아내는 방법
//        ● Tika 사용해봄
    @GetMapping("/file/{filename}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String filename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + filename);
        File file = resource.getFile();
        Tika tika = new Tika();
        String type = tika.detect(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachement; filename=\""+resource.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, type)
                .header(HttpHeaders.CONTENT_LENGTH, file.length()+"")
                .body(resource)
                ;
    }
}
