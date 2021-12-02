package com.sparta.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// http://localhost:8080/hello.html : static 에 있는 hello.html 파일 찾으므로 controller 필요 없음

@Controller
@RequestMapping("/hello/response")  // 공통적인 부분
public class HelloResponseController {
    // [Response header]
// Location: http://localhost:8080/hello.html   (리다이렉트)
    @GetMapping("/html/redirect")
    public String htmlFile() {
        return "redirect:/hello.html";                  // 리다이렉트
    }

////////////////////////////////////////////////////////////////////////////

    // [Response header]
// Content-Type: text/html
// [Response body]
// * resources/templates/hello.html 의 text 내용
// <!DOCTYPE html>
// <html>
// <head><meta charset="UTF-8"><title>By Templates engine</title></head>
// <body>Hello, Spring 정적 웹 페이지!!</body>
// </html>
    @GetMapping("/html/templates")
    public String htmlTemplates() {
        return "hello";                                 // String 리턴하면 템플릿엔진(타임리프) 뷰를 전달 ( /templates/폴더명.html )
    }

////////////////////////////////////////////////////////////////////////////

    // [Response header]
// Content-Type: text/html
// [Response body]
// <!DOCTYPE html>
// <html>
// <head><meta charset="UTF-8"><title>By @ResponseBody</title></head>
// <body>Hello, Spring 정적 웹 페이지!!</body>
// </html>
    @GetMapping("/body/html")
    @ResponseBody                                           // ResponseBody 있으면 뷰를 제공하는게 아니라 Return 내용을 그대로 전달 (그대로 Body에 넣어줌)
    public String helloStringHTML() {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head><meta charset=\"UTF-8\"><title>By @ResponseBody</title></head>" +
                "<body> Hello, 정적 웹 페이지!!</body>" +
                "</html>";
    }

////////////////////////////////////////////////////////////////////////////
    // [Response header]
// Content-Type: text/html
// [Response body]
// * resources/templates/hello-visit.html 의 text 내용
    @GetMapping("/html/dynamic")
    public String helloHtmlFile(Model model) {                       // 모델을 설정
        visitCount++;
        model.addAttribute("visits", visitCount);       // 설정한 변수를 모델로 설정
        return "hello-visit";                                        // resources/templates/hello-visit.html
    }

    private static long visitCount = 0;

////////////////////////////////////////////////////////////////////////////

    // [Response header]
// Content-Type: text/html                          // 텍스트로 내려보내면 안됨
// [Response body]
// {"name":"BTS","age":28}
    @GetMapping("/json/string")
    @ResponseBody
    public String helloStringJson() {
        return "{\"name\":\"BTS\",\"age\":28}";
    }

////////////////////////////////////////////////////////////////////////////

    // [Response header]
// Content-Type: application/json                   // JSON으로 내려줌, 프론트랑 잘맞춰야함
// [Response body]
// {"name":"BTS","age":28}
    @GetMapping("/json/class")
    @ResponseBody
    public Star helloJson() {
        return new Star("BTS", 28);
    }
}