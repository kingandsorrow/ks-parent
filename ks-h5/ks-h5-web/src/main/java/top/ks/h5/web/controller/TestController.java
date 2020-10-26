package top.ks.h5.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/h5/")
public class TestController {

    @RequestMapping("test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        return "test success";
    }
}
