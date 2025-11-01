package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Student;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基于 Mapper 接口的 CRUD 操作（推荐）
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

  /**
   * Insert 方法
   * 默认主键生成策略是雪花算法，不需要指定主键值
   * 或者通过 type = IdType.AUTO，指定主键生成策略为自动生成
   * int insert(Student student);
   */

  /**
   * Delete 方法
   * int deleteById(Integer id);
   * int deleteById(Student student);  // 根据 ID 进行删除
   * int deleteByMap(Map<String, Object> columnMap);
   * int delete(QueryWrapper<Student> queryWrapper);
   * int deleteBatchIds(List<Integer> idList);
   */

  /**
   * Update 方法
   * int updateById(Student student);  // 根据 ID 进行更新
   * int update(Student student, UpdateWrapper<Student> updateWrapper);
   */

  /**
   * Select 方法
   * Student selectById(Integer id);
   * List<Student> selectBatchIds(List<Integer> idList);
   * List<Student> selectByMap(Map<String, Object> columnMap);
   * Student selectOne(QueryWrapper<Student> queryWrapper);
   * boolean exist(QueryWrapper<Student> queryWrapper);
   * Long count(QueryWrapper<Student> queryWrapper);
   * List<Student> selectList(QueryWrapper<Student> queryWrapper);
   * Page<Student> selectPage(Page<Student> page, QueryWrapper<Student> queryWrapper);
   */

  /**
   * 自定义方法
   */
  List<Student> queryAll();
  Page<Student> queryPage(Page<Student> page, Integer id);
}
