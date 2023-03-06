package com.wxz.wiki.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxz.wiki.service.TestService;

import jakarta.annotation.Resource;

import com.wxz.wiki.domain.Test;
import java.util.List;

@RestController
public class TestController {

  @Resource
  private TestService testService;
  @GetMapping("/test/list")
  public List<Test> list() {
    return testService.list();
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello World";
  }
}
