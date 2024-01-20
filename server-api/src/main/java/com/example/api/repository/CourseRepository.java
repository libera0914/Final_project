package com.example.api.repository;

import com.example.api.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Date: 2023/3/5 13:49
 * @Description:
 */
@Repository
public interface CourseRepository extends JpaRepository<Course,String> {

}
