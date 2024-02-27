package com.example.seminar.service.category;

import com.example.seminar.common.exception.CategoryException;
import com.example.seminar.domain.Category;
import com.example.seminar.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.xml.catalog.CatalogException;

@Component
@RequiredArgsConstructor
public class CategoryFinder {

    private final CategoryJpaRepository categoryJpaRepository;

    public Category findById(int id) {
        return categoryJpaRepository.findById(id)
                .orElseThrow( () -> new CategoryException("존재하지 않는 카테고리입니다."));
    }
}
