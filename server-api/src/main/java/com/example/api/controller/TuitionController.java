package com.example.api.controller;

import com.example.api.model.dto.PageDto;
import com.example.api.model.entity.Tuition;
import com.example.api.service.TuitionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2023/3/5 13:59
 * @Description:
 */
@RestController
@RequestMapping("/api/tuition")
public class TuitionController {

    @Resource
    private TuitionService service;

    @GetMapping("")
    public List<Tuition> findAll() {
        return service.findAll();
    }

    @PostMapping("/course")
    public List<Object[]> findCourse(@RequestBody PageDto pageDto) {
        return service.findCourse(pageDto.getDegree(),pageDto.getFrom(),pageDto.getTypes());
    }

    @PostMapping("/major")
    public List<Map<String, Object>> findMajor(@RequestBody PageDto pageDto) {
        return service.findMajor(pageDto.getDegree(),pageDto.getFrom(),pageDto.getTypes());
    }

    @PostMapping("/page")
    public Map<String, Object> findAllByPage(@RequestBody PageDto pageDto) {
        Map<String, Object> map = new HashMap<>();

        if(!StringUtils.isEmpty(pageDto.getKeyWord())){
            Integer total = service.findAll().size();
            Page<Tuition> courses = service.findAllByPage(pageDto.getKeyWord(),pageDto.getPageNum()-1,pageDto.getPageSize());
            map.put("data", courses.getContent());
            map.put("total", total);
        }else {
            Integer total = service.findAll().size();
            Page<Tuition> courses = service.findAllByPage(pageDto.getKeyWord(),pageDto.getPageNum()-1,pageDto.getPageSize());
            map.put("data", courses.getContent());
            map.put("total", total);
        }




        if(!StringUtils.isEmpty(pageDto.getDegree())
                && !StringUtils.isEmpty(pageDto.getFrom())
                && !StringUtils.isEmpty(pageDto.getTypes())) {
            Integer total = service.findAllByDegree(pageDto.getDegree(),pageDto.getFrom(),pageDto.getTypes(),pageDto.getKeyWord()).size();
            Page<Tuition> courses = service.findAllByDegreePage(pageDto.getDegree(),pageDto.getFrom(),pageDto.getTypes(),pageDto.getKeyWord(),pageDto.getPageNum()-1,pageDto.getPageSize());
            map.put("data", courses.getContent());
            map.put("total", total);
        }

        if(!StringUtils.isEmpty(pageDto.getDegree())
                && !StringUtils.isEmpty(pageDto.getFrom())
                && !StringUtils.isEmpty(pageDto.getTypes())
                && pageDto.getCourseName().length >0
                ) {
            Integer total = service.findAllByTitleName(pageDto.getDegree(),pageDto.getFrom(),pageDto.getTypes(),pageDto.getKeyWord(), pageDto.getCourseName()).size();
            Page<Tuition> courses = service.findAllByTitleNamePage(pageDto.getDegree(),pageDto.getFrom(),pageDto.getTypes(),pageDto.getKeyWord(), pageDto.getCourseName(),pageDto.getPageNum()-1,pageDto.getPageSize());
            map.put("data", courses.getContent());
            map.put("total", total);
        }

        if(!StringUtils.isEmpty(pageDto.getDegree())
                && !StringUtils.isEmpty(pageDto.getFrom())
                && !StringUtils.isEmpty(pageDto.getTypes())
                && pageDto.getCourseTitles().length >0
        ) {
            Integer total = service.findAllByTitles(pageDto.getDegree(),pageDto.getFrom(),pageDto.getTypes(),pageDto.getKeyWord(), pageDto.getCourseTitles()).size();
            Page<Tuition> courses = service.findAllByTitlesPage(pageDto.getDegree(),pageDto.getFrom(),pageDto.getTypes(),pageDto.getKeyWord(), pageDto.getCourseTitles(),pageDto.getPageNum()-1,pageDto.getPageSize());
            map.put("data", courses.getContent());
            map.put("total", total);
        }

        if(!StringUtils.isEmpty(pageDto.getDegree())
                && !StringUtils.isEmpty(pageDto.getFrom())
                && !StringUtils.isEmpty(pageDto.getTypes())
                && pageDto.getCourseTitles().length >0
                && pageDto.getCourseName().length >0
        ) {
            Integer total = service.findAllByTitlesAndName(pageDto.getDegree(),pageDto.getFrom(),pageDto.getTypes(),pageDto.getKeyWord(), pageDto.getCourseTitles(), pageDto.getCourseName()).size();
            Page<Tuition> courses = service.findAllByTitlesPageAndName(pageDto.getDegree(),pageDto.getFrom(),pageDto.getTypes(),pageDto.getKeyWord(), pageDto.getCourseTitles(), pageDto.getCourseName(),pageDto.getPageNum()-1,pageDto.getPageSize());
            map.put("data", courses.getContent());
            map.put("total", total);
        }

        return map;
    }
}
