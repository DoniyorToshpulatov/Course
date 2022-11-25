package com.example.service;

import com.example.dto.CourseDTO;
import com.example.dto.StudentDTO;
import com.example.entity.CourseEntity;
import com.example.exception.CreationException;
import com.example.exception.ItemNotFoundException;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.PushBuilder;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.findByName;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseDTO toDto(CourseEntity course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDuration(course.getDuration());
        courseDTO.setCreatedDate(course.getCreatedDate());
        courseDTO.setPrice(course.getPrice());
        return courseDTO;
    }

    public CourseEntity toEntity(CourseDTO course) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(course.getId());
        courseEntity.setName(course.getName());
        courseEntity.setDuration(course.getDuration());
        courseEntity.setCreatedDate(course.getCreatedDate());
        courseEntity.setPrice(course.getPrice());
        return courseEntity;
    }

    public CourseDTO create(CourseDTO courseDTO) {
        if (courseDTO.getName().trim().length() < 3) {
            throw new CreationException("Name is invalid wrong!!");
        }
        courseDTO.setCreatedDate(LocalDate.now());
        courseRepository.save(toEntity(courseDTO));
        return courseDTO;
    }

    public CourseDTO getByIdCourse(Integer id) {
        Optional<CourseEntity> courseEntity = courseRepository.findById(id);
        if (courseEntity.isEmpty()) {
            throw new ItemNotFoundException("Is not found!!");
        }
        return toDto(courseEntity.get());
    }

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> all = courseRepository.findAll();
        List<CourseDTO> courseDTOS = new LinkedList<>();
        for (CourseEntity course : all) {
            CourseDTO courseDTO = toDto(course);
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    public CourseDTO updateById(CourseDTO courseDTO, Integer id) {
        CourseDTO byIdCourse = getByIdCourse(id);
        courseDTO.setId(id);
        courseRepository.save(toEntity(courseDTO));
        return courseDTO;
    }

    public CourseDTO deleteById(Integer id) {
        CourseDTO byIdCourse = getByIdCourse(id);
        courseRepository.deleteById(id);
        return byIdCourse;
    }

    public List<CourseDTO> getByName(String name) {
        List<CourseEntity> courseEntities = courseRepository.findByName2(name);
        List<CourseDTO> courseDTOS = new LinkedList<>();
        for (CourseEntity course : courseEntities) {
            CourseDTO courseDTO = toDto(course);
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    public List<CourseDTO> getByPrice(Double price) {
        List<CourseEntity> courseEntities = courseRepository.findByPrice2(price);
        List<CourseDTO> courseDTOS = new LinkedList<>();
        for (CourseEntity course : courseEntities) {
            CourseDTO courseDTO = toDto(course);
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    public List<CourseDTO> getByDuration(Integer duration) {
        List<CourseEntity> courseEntities = courseRepository.findByDuration2(duration);
        List<CourseDTO> courseDTOS = new LinkedList<>();
        for (CourseEntity course : courseEntities) {
            CourseDTO courseDTO = toDto(course);
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    public List<CourseDTO> getBetweenByPrice(Double from, Double to) {
        List<CourseEntity> courseEntityList = courseRepository.findByPriceBetween2(from, to);
        List<CourseDTO> courseDTOList = new LinkedList<>();
        for (CourseEntity course : courseEntityList) {
            CourseDTO courseDTO = toDto(course);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }

    public List<CourseDTO> getBetweenCreatedDate(LocalDate from, LocalDate to) {
        List<CourseEntity> courseEntityList = courseRepository.findByCreatedDateBetween2(from, to);
        List<CourseDTO> courseDTOList = new LinkedList<>();
        for (CourseEntity course : courseEntityList) {
            CourseDTO courseDTO = toDto(course);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }

    public Page<CourseDTO> getAllPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CourseEntity> pageObj = courseRepository.findAll(pageable);
        List<CourseEntity> courseEntityList = pageObj.getContent();
        Long totalElements = pageObj.getTotalElements();
        List<CourseDTO> courseDTOS = new LinkedList<>();
        for (CourseEntity course : courseEntityList) {
        CourseDTO courseDTO=toDto(course);
        courseDTOS.add(courseDTO);
        }
        Page<CourseDTO> courseDTOPage=new PageImpl<>(courseDTOS,pageable,totalElements);
        return courseDTOPage;
    }
    public Page<CourseDTO> getAllPageSortCreatedDate(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CourseEntity> pageObj = courseRepository.findByOrderByCreatedDate2(pageable);
        List<CourseEntity> courseEntityList = pageObj.getContent();
        Long totalElements = pageObj.getTotalElements();
        List<CourseDTO> courseDTOS = new LinkedList<>();
        for (CourseEntity course : courseEntityList) {
            CourseDTO courseDTO=toDto(course);
            courseDTOS.add(courseDTO);
        }
        Page<CourseDTO> courseDTOPage=new PageImpl<>(courseDTOS,pageable,totalElements);
        return courseDTOPage;
    }
    public Page<CourseDTO> getAllPageSortPriceBetween(Integer page, Integer size,Double from,Double to) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CourseEntity> pageObj = courseRepository.findByPriceBetweenOrderByCreatedDateAS(from,to,pageable);
        List<CourseEntity> courseEntityList = pageObj.getContent();
        Long totalElements = pageObj.getTotalElements();
        List<CourseDTO> courseDTOS = new LinkedList<>();
        for (CourseEntity course : courseEntityList) {
            CourseDTO courseDTO=toDto(course);
            courseDTOS.add(courseDTO);
        }
        Page<CourseDTO> courseDTOPage=new PageImpl<>(courseDTOS,pageable,totalElements);
        return courseDTOPage;
    }
    public Page<CourseDTO> getAllPageSortPrice(Integer page, Integer size,Double price) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CourseEntity> pageObj = courseRepository.getPrice(price,pageable);
        List<CourseEntity> courseEntityList = pageObj.getContent();
        Long totalElements = pageObj.getTotalElements();
        List<CourseDTO> courseDTOS = new LinkedList<>();
        for (CourseEntity course : courseEntityList) {
            CourseDTO courseDTO=toDto(course);
            courseDTOS.add(courseDTO);
        }
        Page<CourseDTO> courseDTOPage=new PageImpl<>(courseDTOS,pageable,totalElements);
        return courseDTOPage;
    }
}
