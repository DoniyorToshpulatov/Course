package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<?> createStudent(@RequestBody StudentDTO studentDTO){
    studentService.createStudent(studentDTO);
    return new ResponseEntity<>("Success add", HttpStatus.OK);
    }
    @GetMapping("/student")
    public  ResponseEntity<?> getAllStudent(){
        return new ResponseEntity<>(studentService.getStudentList(),HttpStatus.OK);
    }
    @GetMapping("/student/{id}")
    public  ResponseEntity<?> getByIdStudent(@PathVariable Integer id){
        return  new ResponseEntity<>(studentService.getByIdStudent(id),HttpStatus.OK);
    }
    @PutMapping("/student/{id}")
    public  ResponseEntity<?> updateById(@RequestBody StudentDTO studentDTO,@PathVariable Integer id){
        studentService.updateStudentById(studentDTO,id);
        return  new ResponseEntity<>("Success update",HttpStatus.OK);
    }
    @PostMapping("/student/{id}")
    public  ResponseEntity<?> deleteStudentById(@PathVariable Integer id){
        studentService.deleteStudentById(id);
        return  new ResponseEntity<>("Success delete",HttpStatus.OK);
    }
    @PutMapping("/student/name")
    public  ResponseEntity<?> getStudentByName(@RequestBody StudentDTO studentDTO){
        List<StudentDTO> studentDTOList=studentService.getByNameStudent(studentDTO.getName());
        return  new ResponseEntity<>(studentDTOList,HttpStatus.OK);
    }
    @PutMapping("/student/surname")
    public  ResponseEntity<?> getStudentBySurname(@RequestBody StudentDTO studentDTO){
        List<StudentDTO> studentDTOList=studentService.getBySurnameStudent(studentDTO.getSurname());
        return  new ResponseEntity<>(studentDTOList,HttpStatus.OK);
    }
    @PutMapping("/student/gender")
    public  ResponseEntity<?> getStudentByGender(@RequestBody StudentDTO studentDTO){
        List<StudentDTO> studentDTOList=studentService.getByGenderStudent(studentDTO.getGender());
        return  new ResponseEntity<>(studentDTOList,HttpStatus.OK);
    }
    @PutMapping("/student/level")
    public  ResponseEntity<?> getStudentByLevel(@RequestBody StudentDTO studentDTO){
//        List<StudentDTO> studentDTOList=studentService.getByLevelStudent(studentDTO.getLevel());
//        return  new ResponseEntity<>(studentDTOList,HttpStatus.OK);
        return ResponseEntity.ok(studentService.getByLevelStudent(studentDTO.getLevel()));
    }
    @PutMapping("/student/age")
    public  ResponseEntity<?> getStudentByAge(@RequestBody StudentDTO studentDTO){
        List<StudentDTO> studentDTOList=studentService.getByAgeStudent(studentDTO.getAge());
        return  new ResponseEntity<>(studentDTOList,HttpStatus.OK);
    }
    @PutMapping("/student/date")
    public  ResponseEntity<?> getByDate(@RequestBody StudentDTO studentDTO){
        List<StudentDTO> studentDTOList=studentService.getByDateStudent(studentDTO.getCreatedDate());
        return  new ResponseEntity<>(studentDTOList,HttpStatus.OK);
    }
    @GetMapping("/student/between")
    public  ResponseEntity<?> betweenStudent(@RequestParam("from") String from,@RequestParam("to") String to){
        return ResponseEntity.ok(studentService.getByBetweenDate(LocalDate.parse(from),LocalDate.parse(to)));
    }

    @GetMapping("/student/page")
    public  ResponseEntity<Page<StudentDTO>> getAllPage(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        return  ResponseEntity.ok(studentService.getAllPage(page,size));
    }
    @GetMapping("/student/page/level")
    public  ResponseEntity<Page<StudentDTO>> getPageLevel(@RequestParam("page") Integer page,
                                                          @RequestParam("size") Integer size,
                                                          @RequestParam("level") Integer level){
        return  ResponseEntity.ok(studentService.getAllPageSort(page,size,level));
    }
    @GetMapping("/student/page/created")
    public  ResponseEntity<Page<StudentDTO>> getPageSortCreated(@RequestParam("page") Integer page,
                                                          @RequestParam("size") Integer size,
                                                          @RequestParam("gender") String  gender){
        return  ResponseEntity.ok(studentService.getAllPageSortByCreated(page,size,gender));
    }
}
