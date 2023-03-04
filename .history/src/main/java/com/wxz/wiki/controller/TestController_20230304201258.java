package com.wxz.wiki.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class TestController {

  @RequestMapping("./hello")
  public String hello() {
    return "Hello World";
  }
}
