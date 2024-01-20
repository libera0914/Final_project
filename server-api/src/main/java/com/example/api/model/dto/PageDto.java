package com.example.api.model.dto;

import lombok.Data;

/**
 * Pagination entities
 * */
@Data
public class PageDto {

    private int pageNum = 1;

    private int pageSize = 10;

    private String keyWord;

    private String degree;

    private String from;

    private String types;

    private String courseTitle;

    private String[] courseName;

    private String[] courseTitles;



}
