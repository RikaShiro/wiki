package com.wxz.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxz.wiki.service.EbookService;

import jakarta.annotation.Resource;

import com.wxz.wiki.req.EbookReq;
import com.wxz.wiki.resp.CommonResp;
import com.wxz.wiki.resp.EbookResp;

import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

  @Resource
  private EbookService ebookService;

  @GetMapping("/list")
  public CommonResp<List<EbookResp>> list(EbookReq req) {
    CommonResp<List<EbookResp>> resp = new CommonResp<>();
    resp.setContent(ebookService.list(req));
    return resp;
  }
}
