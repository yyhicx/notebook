package com.example.service.impl;

import com.example.dto.HeadlineTypeDTO;
import com.example.entity.HeadlineType;
import com.example.mapper.HeadlineTypeMapper;
import com.example.service.TypeService;
import com.example.utils.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {
  private final HeadlineTypeMapper headlineTypeMapper;

  @Autowired
  public TypeServiceImpl(HeadlineTypeMapper headlineTypeMapper) {
    this.headlineTypeMapper = headlineTypeMapper;
  }

  @Override
  public Result findAll() {
    List<HeadlineType> headlineTypes = headlineTypeMapper.selectList(null);
    List<HeadlineTypeDTO> headlineTypeDTOList = new ArrayList<>();
    for (HeadlineType headlineType : headlineTypes) {
      headlineTypeDTOList.add(new HeadlineTypeDTO(headlineType.getTid(), headlineType.getTname()));
    }
    Map<String, Object> data = new HashMap<>();
    data.put("types", headlineTypeDTOList);
    return Result.ok(data);
  }
}
