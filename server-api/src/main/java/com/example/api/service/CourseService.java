package com.example.api.service;

import com.example.api.model.entity.Course;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Date: 2023/3/5 13:50
 * @Description:
 */
public interface CourseService {
    
    List<Course> findAll();

}
