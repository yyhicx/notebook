package com.example.controller;

import com.example.service.HeadlineService;
import com.example.service.TypeService;
import com.example.utils.Result;
import com.example.vo.PortalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {
  private final HeadlineService headlineService;
  private final TypeService typeService;

  @Autowired
  public PortalController(HeadlineService headlineService, TypeService typeService) {
    this.headlineService = headlineService;
    this.typeService = typeService;
  }

  @GetMapping("findAllTypes")
  public Result findAllTypes() {
    return typeService.findAll();
  }

  @PostMapping("findHeadlines")
  public Result findHeadlines(@RequestBody PortalVO portalVO) {
    return headlineService.findAll(portalVO);
  }

  @PostMapping("findHeadlineDetail")
  public Result findHeadlineDetail(Integer hid) {
    return headlineService.findHeadlineDetail(hid);
  }
}
