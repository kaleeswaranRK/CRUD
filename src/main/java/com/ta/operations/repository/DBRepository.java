package com.ta.operations.repository;

import java.util.List;

import com.ta.operations.model.DBModel;

public interface DBRepository {
  int save(DBModel book);

  int update(DBModel book);

  DBModel findById(Long id);

  int deleteById(Long id);

  List<DBModel> findAll();

  List<DBModel> findByPublished(boolean published);

  List<DBModel> findByTitleContaining(String title);

  int deleteAll();
}