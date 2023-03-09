package com.wxz.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxz.wiki.service.EbookService;

import jakarta.annotation.Resource;

import com.wxz.wiki.req.EbookQueryReq;
import com.wxz.wiki.req.EbookSaveReq;
import com.wxz.wiki.resp.CommonResp;
import com.wxz.wiki.resp.EbookQueryResp;
import com.wxz.wiki.resp.PageResp;

@RestController
@RequestMapping("/ebook")
public class EbookController {

  @Resource
  private EbookService ebookService;

  @GetMapping("/list")
  public CommonResp<PageResp<EbookQueryResp>> list(EbookQueryReq req) {
    CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
    resp.setContent(ebookService.list(req));
    return resp;
  }
  
  @PostMapping("/save")
  // @RequestBody = post json
  public CommonResp save(@RequestBody EbookSaveReq req) {
    CommonResp resp = new CommonResp<>();
    ebookService.save(req);
    return resp;
  }
}
