package com.wxz.wiki.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wxz.wiki.domain.Test;
import com.wxz.wiki.mapper.TestMapper;

import jakarta.annotation.Resource;

@Service
public class TestService {
  @Resource
  private TestMapper testMapper;

  public List<Test> list() {
    return testMapper.list();
  }
}
