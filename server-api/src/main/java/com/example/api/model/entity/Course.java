package com.example.api.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Date: 2023/3/4 13:20
 * @Description:Course
 */
@Data
@Entity
@NoArgsConstructor
public class Course {

    @Id
    private  Integer  id;

    private String name;

}
