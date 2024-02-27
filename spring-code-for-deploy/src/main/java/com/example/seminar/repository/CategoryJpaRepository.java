package com.example.seminar.repository;

import com.example.seminar.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Integer> {
}
