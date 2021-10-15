package com.chengning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController
 */
@RestController
public class HelloWordController {
    /**
     * http://localhost:1001/hello
     */
    @Autowired
    CodeInit codeInit;

    @GetMapping("/hello")
    public String helloword() {

        int i = 1;
        while (true) {
            CodeInit codeInit = new CodeInit();
            codeInit.todo();
            if (i > 3) {
                break;
            }
            i++;
        }

        return "Hello springboot  get method";
    }


    @PostMapping("/hello")
    public String helloword_post() {
        int i = 1;
        while (true) {
            codeInit.todo();
            if (i > 3) {
                break;
            }
            i++;
        }

        return "Hello springboot  post method";
    }
}
