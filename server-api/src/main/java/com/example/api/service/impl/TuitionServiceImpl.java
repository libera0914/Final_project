package com.example.api.service.impl;

import com.example.api.model.entity.Tuition;
import com.example.api.repository.TuitionRepository;
import com.example.api.service.TuitionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Date: 2023/3/5 14:00
 * @Description:
 */
@Service
public class TuitionServiceImpl implements TuitionService {

    @Resource
    private TuitionRepository repository;


    @Override
    public List<Tuition> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Tuition> findAllByTitle(String keyWord) {
        return repository.findAllByTitle(keyWord);
    }

    @Override
    public Page<Tuition> findAllByPage(String keyWord, int pageNum, int pageSize) {
        if(Objects.isNull(pageNum)){
            pageNum = 0;
        }
        if(Objects.isNull(pageSize)){
            pageSize = 10;
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return repository.findAllByPage(keyWord,pageable);
    }

    @Override
    public List<Tuition> findAllByDegree(String degree,String from,String types,String keyWord) {
        return repository.findAllByDegree(degree,from,types,keyWord);
    }

    @Override
    public Page<Tuition> findAllByDegreePage(String degree,String from,String types,String keyWord, int pageNum, int pageSize) {
        if(Objects.isNull(pageNum)){
            pageNum = 0;
        }
        if(Objects.isNull(pageSize)){
            pageSize = 10;
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return repository.findAllByDegreePage(degree,from,types,keyWord,pageable);
    }

    @Override
    public List<Tuition> findAllByFrom(String from) {
        return repository.findAllByFrom(from);
    }

    @Override
    public Page<Tuition> findAllByFromPage(String from, int pageNum, int pageSize) {
        if(Objects.isNull(pageNum)){
            pageNum = 0;
        }
        if(Objects.isNull(pageSize)){
            pageSize = 10;
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return repository.findAllByFromPage(from,pageable);
    }

    @Override
    public List<Tuition> findAllByTypes(String types) {
        return repository.findAllByTypes(types);
    }

    @Override
    public Page<Tuition> findAllByTypesPage(String types, int pageNum, int pageSize) {
        if(Objects.isNull(pageNum)){
            pageNum = 0;
        }
        if(Objects.isNull(pageSize)){
            pageSize = 10;
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return repository.findAllByTypesPage(types,pageable);
    }

    @Override
    public List<Tuition> findAllByTitleName(String degree, String from, String types, String keyWord, String[] courseName) {
        return repository.findAllByTitleName(degree,from,types,keyWord,courseName);
    }

    @Override
    public Page<Tuition> findAllByTitleNamePage(String degree, String from, String types, String keyWord, String[] courseName, int pageNum, int pageSize) {
        if(Objects.isNull(pageNum)){
            pageNum = 0;
        }
        if(Objects.isNull(pageSize)){
            pageSize = 10;
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return repository.findAllByTitleNamePage(degree,from,types,keyWord,courseName,pageable);
    }

    @Override
    public List<Tuition> findAllByTitles(String degree, String from, String types, String keyWord, String[] courseTitles) {
        return repository.findAllByTitles(degree,from,types,keyWord,courseTitles);
    }

    @Override
    public Page<Tuition> findAllByTitlesPage(String degree, String from, String types, String keyWord, String[] courseTitles, int pageNum, int pageSize) {
        if(Objects.isNull(pageNum)){
            pageNum = 0;
        }
        if(Objects.isNull(pageSize)){
            pageSize = 10;
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return repository.findAllByTitlesPage(degree,from,types,keyWord,courseTitles,pageable);
    }

    @Override
    public List<Tuition> findAllByTitlesAndName(String degree, String from, String types, String keyWord, String[] courseTitles, String[] courseName) {
        return repository.findAllByTitlesAndName(degree,from,types,keyWord,courseTitles,courseName);
    }

    @Override
    public Page<Tuition> findAllByTitlesPageAndName(String degree, String from, String types, String keyWord, String[] courseTitles, String[] courseName, int pageNum, int pageSize) {
        if(Objects.isNull(pageNum)){
            pageNum = 0;
        }
        if(Objects.isNull(pageSize)){
            pageSize = 10;
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return repository.findAllByTitlesPageAndName(degree,from,types,keyWord,courseTitles,courseName,pageable);
    }

    @Override
    public List<Object[]> findCourse(String degree, String from,  String types) {
        return repository.findCourse(degree,from, types);
    }

    @Override
    public List<Map<String, Object>> findMajor(String degree, String from, String types) {
        return repository.findMajor(degree,from, types);
    }
}
