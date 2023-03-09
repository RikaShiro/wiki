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
import com.wxz.wiki.req.EbookQueryReq;
import com.wxz.wiki.req.EbookSaveReq;
import com.wxz.wiki.resp.EbookQueryResp;
import com.wxz.wiki.resp.PageResp;
import com.wxz.wiki.util.CopyUtil;
import com.wxz.wiki.util.SnowFlake;

import jakarta.annotation.Resource;

@Service
public class EbookService {

  private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
  @Resource
  private EbookMapper ebookMapper;
  @Resource
  private SnowFlake snowFlake;

  public PageResp<EbookQueryResp> list(EbookQueryReq req) {
    EbookExample ebookExample = new EbookExample();
    if (!ObjectUtils.isEmpty(req.getName())) {
      EbookExample.Criteria criteria = ebookExample.createCriteria();
      criteria.andNameLike("%" + req.getName() + "%");
    }
    PageHelper.startPage(req.getPage(), req.getSize());
    List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

    PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
    LOG.info("row count: {}", pageInfo.getTotal());
    LOG.info("page count: {}", pageInfo.getPages());

    PageResp<EbookQueryResp> pageResp = new PageResp();
    pageResp.setTotal(pageInfo.getTotal());
    pageResp.setList(CopyUtil.copyList(ebookList, EbookQueryResp.class));
    return pageResp;
  }

  // update or add
  public void save(EbookSaveReq req) {
    Ebook ebook = CopyUtil.copy(req, Ebook.class);
    if (ObjectUtils.isEmpty(req.getId())) {
      ebook.setId(snowFlake.nextId());
      ebookMapper.insert(ebook);
    } else {
      ebookMapper.updateByPrimaryKey(ebook);
    }
  }

  public void delete(Long id) {
    ebookMapper.deleteByPrimaryKey(id);
  }
}
