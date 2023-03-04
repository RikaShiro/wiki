package com.wxz.wiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // return string
@Controller
public class TestController {

  @RequestMapping("/hello")
  public String hello() {
    return "Hello World";
  }
}
