package com.sparta.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello/request")
public class HelloRequestController {
    @GetMapping("/form/html")
    public String helloForm() {
        return "hello-request-form";
    }

    // [Request sample]
// GET http://localhost:8080/hello/request/star/BTS/age/28
    @GetMapping("/star/{name}/age/{age}")
    @ResponseBody
    public String helloRequestPath(@PathVariable String name, @PathVariable int age)        // @PathVariable: url에 있는 정보를 빼냄
    {
        return String.format("Hello, @PathVariable.<br> name = %s, age = %d", name, age);
    }

    // [Request sample]
// GET http://localhost:8080/hello/request/form/param?name=BTS&age=28
    @GetMapping("/form/param")
    @ResponseBody
    public String helloGetRequestParam(@RequestParam String name, @RequestParam int age) {  // GET은 url안에 포함,  @RequestParam: get요청 url 뒤에 정보 들어온걸 뺴냄
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }

    // [Request sample]
// POST http://localhost:8080/hello/request/form/param
// Header
// Content type: application/x-www-form-urlencoded
// Body
// name=BTS&age=28
    @PostMapping("/form/param")
    @ResponseBody
    public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) { // POST는 정보가 Body에 들어감, requestParam 알아서 생략가능, get과 post 요청방식 다르지만 스프링이 알아서 받아줌
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }

    // [Request sample]
// POST http://localhost:8080/hello/request/form/model
// Header
// Content type: application/x-www-form-urlencoded
// Body
// name=BTS&age=28
    @PostMapping("/form/model")
    @ResponseBody
    public String helloRequestBodyForm(@ModelAttribute Star star) {                         // @ModelAttribute: 객체 받을 떄 사용, 객체에 반드시 @Setter가 있어야함!!!!, 위와 가져오는 데이터는 동일하나, 여러개의 매개변수 가져올떄는 객체로 받는게 낫다
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);   // 객체 안에 변수명을 가져올 수 있음
    }

    // [Request sample]
// POST http://localhost:8080/hello/request/form/json
// Header
// Content type: application/json
// Body
// {"name":"BTS","age":"28"}
    @PostMapping("/form/json")
    @ResponseBody                                                                                                   // 페이지 이동 X, 데이터만 이동
    public String helloPostRequestJson(@RequestBody Star star) {                                                    // @RequestBody: 객체 변수 JSON으로 받음
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);
    }
}