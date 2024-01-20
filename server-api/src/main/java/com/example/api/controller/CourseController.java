package com.example.api.controller;

import com.example.api.model.dto.PageDto;
import com.example.api.model.entity.Course;
import com.example.api.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2023/3/4 20:48
 * @Description:CourseController
 */
@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Resource
    private CourseService service;

    @GetMapping("")
    public List<Course> findAll() {
        return service.findAll();
    }


}
