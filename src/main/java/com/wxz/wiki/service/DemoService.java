package com.wxz.wiki.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wxz.wiki.domain.Demo;
import com.wxz.wiki.mapper.DemoMapper;

import jakarta.annotation.Resource;

@Service
public class DemoService {
  @Resource
  private DemoMapper demoMapper;

  public List<Demo> list() {
    return demoMapper.selectByExample(null);
  }
}
