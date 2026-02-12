package com.example.mapper.impl;

import com.example.dto.Page;
import com.example.entity.User;
import com.example.mapper.UserDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public User createUser(User user) {
    String sql = "insert into user_info (username, password, email, phone, status, "
      + "created_time, updated_time, is_deleted) values (?,?,?,?,?,?,?,?)";

    // 用于获取自增主键
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPhone());
        ps.setBoolean(5, user.getStatus());
        if (user.getCreatedTime() != null) {
          ps.setTimestamp(6, Timestamp.valueOf(user.getCreatedTime()));
        } else {
          ps.setTimestamp(6, null);
        }
        if (user.getUpdatedTime() != null) {
          ps.setTimestamp(7, Timestamp.valueOf(user.getUpdatedTime()));
        } else {
          ps.setTimestamp(7, null);
        }
        ps.setBoolean(8, user.getIsDeleted());
        return ps;
      }
    }, keyHolder);

    // keyHolder.getKey() 可能为 null（极端情况），先校验
    Number key = keyHolder.getKey();
    if (key != null) {
      user.setId(key.intValue());
    }
    return user;
  }

  @Override
  public int updateUser(User user) {
    String sql = "update user_info set username=?, password=?, email=?, phone=?, status=?,"
      + " updated_time=? where id=?";

    Timestamp now = Timestamp.valueOf(user.getUpdatedTime());
    return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(),
      user.getPhone(), user.getStatus(), now, user.getId());
  }

  @Override
  public int deleteUserById(Integer id) {
    String sql = "update user_info set is_deleted=true where id=?";
    return jdbcTemplate.update(sql, id);
  }

  @Override
  public List<User> getAllUsers() {
    String sql = "select * from user_info where is_deleted=false order by created_time desc";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
  }

  @Override
  public Optional<User> getUserById(Integer id) {
    String sql = "select * from user_info where id=? and is_deleted=false";
    try {
      User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
      return Optional.ofNullable(user);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<User> getUserByUsername(String username) {
    String sql = "select * from user_info where username=? and is_deleted=false";
    try {
      User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
        username);
      return Optional.ofNullable(user);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    String sql = "select * from user_info where email=? and is_deleted=false";
    try {
      User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
        email);
      return Optional.ofNullable(user);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public Page<User> getUsersByPage(int pageNum, int pageSize) {
    // 防御性校验
    if (pageNum < 1) {
      pageNum = 1;
    }
    if (pageSize < 1) {
      pageSize = 10;
    }

    int offset = (pageNum - 1) * pageSize;

    // 总数
    String countSql = "select count(*) from user_info where is_deleted=false";
    Long total = jdbcTemplate.queryForObject(countSql, Long.class);
    if (total == null) {
      total = 0L;
    }

    // 分页数据
    String sql = "select * from user_info where is_deleted=false order by created_time desc limit ? offset ?";
    List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),
      pageSize, offset);

    int pages = (int) Math.ceil((double) total / pageSize);

    Page<User> page = new Page<>();
    page.setPageNum(pageNum);
    page.setPageSize(pageSize);
    page.setTotal(total);
    page.setList(users);
    page.setPages(pages);
    return page;
  }

  @Override
  public boolean existsByUsername(String username) {
    String sql = "select count(*) from user_info where username=? and is_deleted=false";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
    return count != null && count > 0;
  }

  @Override
  public boolean existsByEmail(String email) {
    String sql = "select count(*) from user_info where email=? and is_deleted=false";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
    return count != null && count > 0;
  }
}
