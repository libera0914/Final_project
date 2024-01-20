package com.example.api.service;

import com.example.api.model.entity.Major;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Date: 2023/3/5 13:57
 * @Description:
 */
public interface MajorService {
    List<Major> findAll();
}
