package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

  /**
   * Save 方法
   * boolean save(Student student);
   * boolean saveBatch(List<Student> students);
   * boolean saveBatch(List<Student> students, int batchSize);
   * boolean saveOrUpdate(Student student);  // 若主键存在，则更新，若不存在，则插入
   * boolean saveOrUpdate(Student student, UpdateWrapper<Student> updateWrapper);
   * boolean saveOrUpdateBatch(List<Student> students);
   * boolean saveOrUpdateBatch(List<Student> students, int batchSize);
   */

  /**
   * Remove 方法
   * boolean remove(QueryWrapper<Student> queryWrapper);
   * boolean removeById(Integer id);
   * boolean removeByMap(Map<String, Object> columnMap);
   * boolean removeByIds(List<Integer> idList);
   */

  /**
   * Update 方法
   * boolean update(UpdateWrapper<Student> updateWrapper);
   * boolean update(Student student, UpdateWrapper<Student> updateWrapper);  // 根据 updateWrapper 的条件进行更新
   * boolean updateById(Student student);  // 根据 id 进行更新
   * boolean updateBatchById(List<Student> students);
   * boolean updateBatchById(List<Student> students, int batchSize);
   */

  /**
   * Get 方法
   * Student getById(Integer id);
   * Student getOne(QueryWrapper<Student> queryWrapper);
   * Student getMap(QueryWrapper<Student> queryWrapper);
   * int count();
   * int count(QueryWrapper<Student> queryWrapper);
   * List<Student> list();
   * List<Student> list(QueryWrapper<Student> queryWrapper);
   * List<Student> listByIds(List<Integer> idList);
   * List<Studnet> listByMap(Map<String, Object> columnMap);
   * List<Map<String, Object>> listMaps();
   * List<Map<String, Object> listMaps(QueryWrapper<Student> queryWrapper);
   */

}

