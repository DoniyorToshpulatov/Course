package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends PagingAndSortingRepository<CourseEntity, Integer> {

    @Query("from CourseEntity where name=:name")
    List<CourseEntity> findByName2(@Param("name") String name);

    @Query(" from CourseEntity as c where c.price=:price")
    List<CourseEntity> findByPrice2(@Param("price") Double price);

    @Query("from  CourseEntity  where duration=:duration")
    List<CourseEntity> findByDuration2(@Param("duration") Integer duration);

    @Query("from CourseEntity where  price between  :from and :to ")
    List<CourseEntity> findByPriceBetween2(@Param("from") Double from, @Param("to") Double to);

    // List<CourseEntity> findByDurationBetween(Integer from, Integer to);

    @Query("from CourseEntity where  createdDate between  :from and :to")
    List<CourseEntity> findByCreatedDateBetween2(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query("select  c from CourseEntity as c order by c.createdDate")
    Page<CourseEntity> findByOrderByCreatedDate2(Pageable pageable);


    @Query("select  c from CourseEntity as c where c.price between :from and :to order by c.createdDate")
    Page<CourseEntity> findByPriceBetweenOrderByCreatedDateAS(@Param("from") Double from, @Param("to") Double to, Pageable pageable);
    @Query("select c  from CourseEntity as c where c.price=:price order by c.createdDate")
    Page<CourseEntity> getPrice(@Param("price") Double price, Pageable pageable);
    //    Page<CourseEntity> findByPriceOrderByCreatedDateAS(@Param("price") Double price, Pageable pageable);

}
