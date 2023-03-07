package com.wxz.wiki.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxz.wiki.service.DemoService;

import jakarta.annotation.Resource;

import com.wxz.wiki.domain.Demo;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

  @Resource
  private DemoService demoService;
  @GetMapping("/list")
  public List<Demo> list() {
    return demoService.list();
  }
}
