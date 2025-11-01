package com.example.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.vo.PortalVO;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HeadlineMapper extends BaseMapper<Headline> {
  // 分页查询
  Page<Map<String, Object>> selectPageMap(Page<Headline> page, @Param("portalVO") PortalVO portalVO);
  Map<String, Object> selectDetailMap(Integer hid);
}




