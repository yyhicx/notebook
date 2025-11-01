package com.example.controller;

import com.example.entity.Headline;
import com.example.service.HeadlineService;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {
  private final HeadlineService headlineService;

  @Autowired
  public HeadlineController(HeadlineService headlineService) {
    this.headlineService = headlineService;
  }

  @PostMapping("publish")
  public Result publish(@RequestBody Headline headline, @RequestHeader("Authorization") String rawToken) {
    return headlineService.publishHeadline(headline, rawToken.replace("Bearer ", ""));
  }

  @PostMapping("findHeadlineByHid")
  public Result findHeadlineByHid(@RequestParam("hid") Integer hid) {
    return headlineService.findHeadlineByHid(hid);
  }

  @PostMapping("update")
  public Result update(@RequestBody Headline headline) {
    return headlineService.updateHeadline(headline);
  }

  @PostMapping("removeByHid")
  public Result removeByHid(@RequestParam("hid") Integer hid) {
    return headlineService.deleteHeadlineByHid(hid);
  }
}
