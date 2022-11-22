package com.ta.operations.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ta.operations.model.DBModel;



@Repository
public class OperationService implements DBRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int save(DBModel DBModel) {
    return jdbcTemplate.update("INSERT INTO DBModels (title, description, published) VALUES(?,?,?)",
        new Object[] { DBModel.getTitle(), DBModel.getDescriptions(), DBModel.isPublished() });
  }

  @Override
  public int update(DBModel DBModel) {
    return jdbcTemplate.update("UPDATE DBModels SET title=?, description=?, published=? WHERE id=?",
        new Object[] { DBModel.getTitle(), DBModel.getDescriptions(), DBModel.isPublished(), DBModel.getId() });
  }

  @Override
  public DBModel findById(Long id) {
    try {
      DBModel DBModel = jdbcTemplate.queryForObject("SELECT * FROM DBModels WHERE id=?",
          BeanPropertyRowMapper.newInstance(DBModel.class), id);

      return DBModel;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
  }

  @Override
  public int deleteById(Long id) {
    return jdbcTemplate.update("DELETE FROM DBModels WHERE id=?", id);
  }

  @Override
  public List<DBModel> findAll() {
    return jdbcTemplate.query("SELECT * from DBModels", BeanPropertyRowMapper.newInstance(DBModel.class));
  }

  @Override
  public List<DBModel> findByPublished(boolean published) {
    return jdbcTemplate.query("SELECT * from DBModels WHERE published=?",
        BeanPropertyRowMapper.newInstance(DBModel.class), published);
  }

  @Override
  public List<DBModel> findByTitleContaining(String title) {
    String q = "SELECT * from DBModels WHERE title LIKE '%" + title + "%'";

    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(DBModel.class));
  }

  @Override
  public int deleteAll() {
    return jdbcTemplate.update("DELETE from DBModels");
  }
}
