package com.example;

import com.example.dto.CourseDTO;
import com.example.dto.StudentDTO;
import com.example.repository.CourseRepository;
import com.example.service.CourseService;
import com.example.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class CourseApplicationTests {
@Autowired
private CourseService courseService;
@Autowired
private StudentService studentService;
	/*@Test
	void contextLoads() {
		Page<CourseDTO> courseDTOS=courseService.getAllPageSortCreatedDate(0,1);
		for (CourseDTO course:courseDTOS) {
			System.out.println(course);
		}
	}
	@Test
	void studentPage() {
		Page<StudentDTO> studentDTOPage=studentService.getAllPageSort(0,1,2);
		for (StudentDTO studentDTO:studentDTOPage) {
			System.out.println(studentDTO);
		}
	}
	@Test
	void create() {

	}*/
}
