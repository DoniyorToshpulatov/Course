package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.entity.CourseEntity;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.AudioFileFormat;
import java.time.LocalDate;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<?> create(@RequestBody CourseDTO courseDTO) {
        courseService.create(courseDTO);
        return ResponseEntity.ok("Success add");
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<?> getByIdCourse(@PathVariable Integer id) {
        CourseDTO byIdCourse = courseService.getByIdCourse(id);
        return ResponseEntity.ok(byIdCourse);
    }

    @GetMapping("/course")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @DeleteMapping("/course/delete")
    public  ResponseEntity<?> delete(@RequestParam("id") Integer id){
        return ResponseEntity.ok(courseService.deleteById(id));
    }

    @PutMapping("/course/update")
    public ResponseEntity<?> update(@RequestBody CourseDTO courseDTO,@RequestParam("id") Integer id){
        return ResponseEntity.ok(courseService.updateById(courseDTO,id));
    }

    @GetMapping("/course/name")
    public ResponseEntity<?> getByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(courseService.getByName(name));
    }

    @GetMapping("/course/price")
    public ResponseEntity<?> getByPrice(@RequestParam("price") Double price) {
        return ResponseEntity.ok(courseService.getByPrice(price));
    }

    @GetMapping("/course/duration")
    public ResponseEntity<?> getByPrice(@RequestParam("duration") Integer duration) {
        return ResponseEntity.ok(courseService.getByDuration(duration));
    }

    @GetMapping("/course/price/between")
    public ResponseEntity<?> getByPriceBetween(@RequestParam("from") Double from, @RequestParam("to") Double to) {
        return ResponseEntity.ok(courseService.getBetweenByPrice(from,to));
    }

   @GetMapping("/course/between/createdDate")
    public  ResponseEntity<?> getByCreatedDate(@RequestParam("from") String from,@RequestParam("to") String to){
        return ResponseEntity.ok(courseService.getBetweenCreatedDate(LocalDate.parse(from),LocalDate.parse(to)));
   }
   @GetMapping("/course/pagination")
    public  ResponseEntity<Page<CourseDTO>> getPage(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        return ResponseEntity.ok(courseService.getAllPage(page,size));
   }
   @GetMapping("/course/paginationSort")
    public  ResponseEntity<Page<CourseDTO>> getSortPage(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        return ResponseEntity.ok(courseService.getAllPageSortCreatedDate(page,size));
   }

    @GetMapping("/course/pagination/price/between")
    public  ResponseEntity<Page<CourseDTO>> getSortPagePriceBetWeen(@RequestParam("page") Integer page,
                                                             @RequestParam("size") Integer size,
                                                             @RequestParam("from") Double from,
                                                             @RequestParam("to")Double to){
        return ResponseEntity.ok(courseService.getAllPageSortPriceBetween(page,size,from,to));
    }
    @GetMapping("/course/pagination/price")
    public  ResponseEntity<Page<CourseDTO>> getSortPagePrice(@RequestParam("page") Integer page,
                                                             @RequestParam("size") Integer size,
                                                             @RequestParam("price") Double price){
        return ResponseEntity.ok(courseService.getAllPageSortPrice(page,size,price));
    }

}

