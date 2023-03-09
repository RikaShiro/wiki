package com.wxz.wiki.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxz.wiki.domain.Ebook;
import com.wxz.wiki.domain.EbookExample;
import com.wxz.wiki.mapper.EbookMapper;
import com.wxz.wiki.req.EbookReq;
import com.wxz.wiki.resp.EbookResp;
import com.wxz.wiki.util.CopyUtil;

import jakarta.annotation.Resource;

@Service
public class EbookService {

  private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
  @Resource
  private EbookMapper ebookMapper;

  public List<EbookResp> list(EbookReq req) {
    EbookExample ebookExample = new EbookExample();
    if (!ObjectUtils.isEmpty(req.getName())) {
      EbookExample.Criteria criteria = ebookExample.createCriteria();
      criteria.andNameLike("%" + req.getName() + "%");
    }
    PageHelper.startPage(1, 3);
    List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
    
    PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
    
    LOG.info("row count: {}", pageInfo.getTotal());
    LOG.info("page count: {}", pageInfo.getPages());
    return CopyUtil.copyList(ebookList, EbookResp.class);
  }
}
