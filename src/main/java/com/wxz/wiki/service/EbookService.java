package com.wxz.wiki.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wxz.wiki.domain.Ebook;
import com.wxz.wiki.mapper.EbookMapper;

import jakarta.annotation.Resource;

@Service
public class EbookService {
  @Resource
  private EbookMapper ebookMapper;

  public List<Ebook> list() {
    return ebookMapper.selectByExample(null);
  }
}
