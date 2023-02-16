package com.aaa.dao;

import com.aaa.entily.FilmInfo;

import java.util.List;
import java.util.Map;

public interface filmDao {
    int add(FilmInfo filmInfo);
    List<Map<String, Object>> list();
    List<Map<String, Object>> lists();
    int delete(Integer id);
    int update(FilmInfo filmInfo);
}
