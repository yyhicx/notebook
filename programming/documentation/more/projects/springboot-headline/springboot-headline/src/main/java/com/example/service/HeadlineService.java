package com.example.service;

import com.example.entity.Headline;
import com.example.utils.Result;
import com.example.vo.PortalVO;

public interface HeadlineService {
  Result findAll(PortalVO portalVO);
  Result findHeadlineDetail(Integer hid);
  Result findHeadlineByHid(Integer hid);
  Result publishHeadline(Headline headline, String token);
  Result updateHeadline(Headline headline);
  Result deleteHeadlineByHid(Integer hid);
}
