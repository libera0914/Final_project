package com.example.api.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Date: 2023/3/3 18:21
 * @Description: Major
 */
@Data
@Entity
@NoArgsConstructor
public class Major {

    @Id
    private Integer  id;

    private String majorName;

    private Integer courseId;
}
