package com.example.api.service.impl;

import com.example.api.model.entity.Course;
import com.example.api.repository.CourseRepository;
import com.example.api.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Date: 2023/3/5 13:50
 * @Description:
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseRepository repository;

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

}
