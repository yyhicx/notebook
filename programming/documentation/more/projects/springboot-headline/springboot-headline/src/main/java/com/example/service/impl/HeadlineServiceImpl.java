package com.example.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Headline;
import com.example.mapper.HeadlineMapper;
import com.example.service.HeadlineService;
import com.example.utils.JwtHelper;
import com.example.utils.Result;
import com.example.vo.PortalVO;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeadlineServiceImpl implements HeadlineService {
  private final JwtHelper jwtHelper;
  private final HeadlineMapper headlineMapper;

  @Autowired
  public HeadlineServiceImpl(JwtHelper jwtHelper, HeadlineMapper headlineMapper) {
    this.jwtHelper = jwtHelper;
    this.headlineMapper = headlineMapper;
  }

  @Override
  public Result findAll(PortalVO portalVO) {
    LambdaQueryWrapper<Headline> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.like(!StringUtils.isEmpty(portalVO.getKeywords()), Headline::getTitle, portalVO.getKeywords()).
      eq(portalVO.getType() != null, Headline::getType, portalVO.getType());

    Page<Headline> page = new Page<>(portalVO.getPageNum(), portalVO.getPageSize());
    headlineMapper.selectPageMap(page, portalVO);

    Map<String, Object> pageInfo = new HashMap<>();
    pageInfo.put("pageData", page.getRecords());
    pageInfo.put("pageNum", page.getCurrent());
    pageInfo.put("pageSize", page.getSize());
    pageInfo.put("totalPage", page.getPages());
    pageInfo.put("totalSize", page.getTotal());

    Map<String, Object> pageInfoMap = new HashMap<>();
    pageInfoMap.put("pageInfo", pageInfo);
    return Result.ok(pageInfoMap);
  }

  @Override
  public Result findHeadlineDetail(Integer hid) {
    // 根据 id 实现多表查询
    Map<String, Object> headlineDetail = headlineMapper.selectDetailMap(hid);

    // 拼接头条对象（阅读量和 Version）进行数据更新
    Headline headline = new Headline();
    headline.setHid(hid);
    headline.setPageViews((Integer) headlineDetail.get("pageViews") + 1);
    headline.setVersion((Integer) headlineDetail.get("version"));
    headlineMapper.updateById(headline);

    Map<String, Object> headlineDetailMap = new HashMap<>();
    headlineDetail.remove("version");
    headlineDetailMap.put("headlineDetail", headlineDetail);
    return Result.ok(headlineDetailMap);
  }

  @Override
  public Result findHeadlineByHid(Integer hid) {
    Headline headline = headlineMapper.selectById(hid);
    Map<String, Object> headlineMap = new HashMap<>();
    headlineMap.put("headline", headline);
    return Result.ok(headlineMap);
  }

  @Override
  public Result publishHeadline(Headline headline, String token) {
    int userId = jwtHelper.getUserId(token).intValue();
    headline.setPublisher(userId);
    headline.setCreateTime(new Date());
    headline.setUpdateTime(new Date());
    headline.setPageViews(0);
    headlineMapper.insert(headline);
    return Result.ok(null);
  }

  @Override
  public Result updateHeadline(Headline headline) {
    Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
    headline.setVersion(version);
    headline.setUpdateTime(new Date());
    headlineMapper.updateById(headline);
    return Result.ok(null);
  }

  @Override
  public Result deleteHeadlineByHid(Integer hid) {
    headlineMapper.deleteById(hid);
    return Result.ok(null);
  }
}
