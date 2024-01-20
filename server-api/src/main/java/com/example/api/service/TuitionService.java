package com.example.api.service;

import com.example.api.model.entity.Tuition;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Date: 2023/3/5 14:00
 * @Description:
 */
public interface TuitionService {

    List<Tuition> findAll();


    List<Tuition> findAllByTitle(String keyWord);

    Page<Tuition> findAllByPage(String keyWord, int i, int pageSize);

    List<Tuition> findAllByDegree(String degree,String from,String types,String keyWord );

    Page<Tuition> findAllByDegreePage(String degree,String from,String types,String keyWord, int i, int pageSize);

    List<Tuition> findAllByFrom(String from);

    Page<Tuition> findAllByFromPage(String from, int i, int pageSize);

    List<Tuition> findAllByTypes(String types);

    Page<Tuition> findAllByTypesPage(String types, int i, int pageSize);

    List<Tuition> findAllByTitleName(String degree, String from, String types, String keyWord, String[] courseName);

    Page<Tuition> findAllByTitleNamePage(String degree, String from, String types, String keyWord, String[] courseName, int i, int pageSize);

    List<Tuition> findAllByTitles(String degree, String from, String types, String keyWord, String[] courseTitles);

    Page<Tuition> findAllByTitlesPage(String degree, String from, String types, String keyWord, String[] courseTitles, int i, int pageSize);

    List<Tuition> findAllByTitlesAndName(String degree, String from, String types, String keyWord, String[] courseTitles, String[] courseName);

    Page<Tuition> findAllByTitlesPageAndName(String degree, String from, String types, String keyWord, String[] courseTitles, String[] courseName, int i, int pageSize);

    List<Object[]> findCourse(String degree, String from,  String types);

    List<Map<String, Object>> findMajor(String degree, String from, String types);
}
