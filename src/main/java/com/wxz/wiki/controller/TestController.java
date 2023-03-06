package com.wxz.wiki.controller;

// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // return string or JSON object
// @Controller return a web page
public class TestController {

  @RequestMapping("/hello")
  public String hello() {
    return "Hello World abc";
  }
}
