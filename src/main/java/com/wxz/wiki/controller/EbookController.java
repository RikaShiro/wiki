package com.wxz.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxz.wiki.service.EbookService;

import jakarta.annotation.Resource;

import com.wxz.wiki.domain.Ebook;
import com.wxz.wiki.resp.CommonResp;

import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

  @Resource
  private EbookService ebookService;

  @GetMapping("/list")
  public CommonResp<List<Ebook>> list() {
    CommonResp<List<Ebook>> resp = new CommonResp<>();
    resp.setContent(ebookService.list());
    return resp;
  }
}
