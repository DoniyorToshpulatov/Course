package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends PagingAndSortingRepository<CourseEntity,Integer> {


    List<CourseEntity> findByName(String name);

    List<CourseEntity> findByPrice(Double price);

    List<CourseEntity> findByDuration(Integer duration);

    List<CourseEntity> findByPriceBetween(Double from, Double to);

   // List<CourseEntity> findByDurationBetween(Integer from, Integer to);

    List<CourseEntity> findByCreatedDateBetween(LocalDate from, LocalDate to);

    Page<CourseEntity> findByOrderByCreatedDate(Pageable pageable);

    Page<CourseEntity> findByPriceBetweenOrderByCreatedDate(Double from, Double to, Pageable pageable);

    Page<CourseEntity> findByPriceOrderByCreatedDate(Double price, Pageable pageable);

}
