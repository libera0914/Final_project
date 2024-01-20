package com.example.api.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Date: 2023/3/5 13:22
 * @Description:Tuition
 */
@Data
@Entity
@NoArgsConstructor
public class Tuition {

    @Id
    private Integer  id;

    private String courseCode;

    private String courseTitle;

    private String tuitionFee;

    private String firstyEnrolled;

    private String degree;

    private String types;

    private String from;

    private Integer majorId;

    private String courseName;
}
