package com.wxz.wiki.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wxz.wiki.domain.Ebook;
import com.wxz.wiki.domain.EbookExample;
import com.wxz.wiki.mapper.EbookMapper;
import com.wxz.wiki.req.EbookReq;
import com.wxz.wiki.resp.EbookResp;

import jakarta.annotation.Resource;

@Service
public class EbookService {
  @Resource
  private EbookMapper ebookMapper;

  public List<EbookResp> list(EbookReq req) {
    EbookExample ebookExample = new EbookExample();
    EbookExample.Criteria criteria = ebookExample.createCriteria();
    criteria.andNameLike("%" + req.getName() + "%");
    List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
    List<EbookResp> arr = new ArrayList<>();
    for (Ebook e : ebookList) {
      EbookResp ebookResp = new EbookResp();
      BeanUtils.copyProperties(e, ebookResp);
      ebookResp.setId(12345L);
      arr.add(ebookResp);
    }
    return arr;
  }
}
