package com.example.api.controller;

import com.example.api.model.dto.PageDto;
import com.example.api.model.entity.Course;
import com.example.api.model.entity.Major;
import com.example.api.service.CourseService;
import com.example.api.service.MajorService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2023/3/5 13:56
 * @Description:
 */
@RestController
@RequestMapping("/api/major")
public class MajorController {

    @Resource
    private MajorService service;

    @GetMapping("")
    public List<Major> findAll() {
        return service.findAll();
    }

}
