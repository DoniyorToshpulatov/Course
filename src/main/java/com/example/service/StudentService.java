package com.example.service;

import com.example.dto.CourseDTO;
import com.example.dto.StudentDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.exception.CreationException;
import com.example.exception.ItemNotFoundException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO toDto(StudentEntity student){
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setCreatedDate(student.getCreatedDate());
        studentDTO.setId(student.getId());
        studentDTO.setAge(student.getAge());
        studentDTO.setGender(student.getGender());
        studentDTO.setLevel(student.getLevel());
        return  studentDTO;
    }

    public StudentEntity toEntity(StudentDTO student){
        StudentEntity entity=new StudentEntity();
       entity.setName(student.getName());
       entity.setSurname(student.getSurname());
       entity.setCreatedDate(student.getCreatedDate());
       entity.setId(student.getId());
       entity.setAge(student.getAge());
       entity.setGender(student.getGender());
       entity.setLevel(student.getLevel());
        return  entity;
    }

    public  StudentDTO createStudent(StudentDTO studentDTO){
        if(studentDTO.getSurname().trim().length()<3){
            throw  new CreationException("SurName is invalid wrong input!!");
        }
        if(studentDTO.getName().trim().length()<3){
            throw  new CreationException("Name is invalid wrong input!!");
        }
        StudentEntity student=toEntity(studentDTO);
        student.setCreatedDate(LocalDate.now());
        studentRepository.save(student);
        return studentDTO;
    }

    public List<StudentDTO> getStudentList(){
        Iterable<StudentEntity> studentEntities=studentRepository.findAll();
        List<StudentDTO> studentDTOList=new LinkedList<>();

        for (StudentEntity st:studentEntities) {
        StudentDTO studentDTO=toDto(st);
        studentDTOList.add(studentDTO);
        }
        return studentDTOList;
    }

    public  StudentDTO getByIdStudent(Integer id){
        Optional<StudentEntity> student=studentRepository.findById(id);
        if(student.isEmpty()){
            throw new ItemNotFoundException("Is not object!!!!");
        }
        return toDto(student.get());
    }

    public  StudentDTO updateStudentById(StudentDTO studentDTO,Integer id){
        StudentDTO studentDTO1=getByIdStudent(id);
        studentDTO.setId(id);
        studentDTO.setCreatedDate(studentDTO1.getCreatedDate());
        StudentEntity entity=toEntity(studentDTO);
        studentRepository.save(entity);
        return studentDTO;
    }

    public  StudentDTO deleteStudentById(Integer id){
        StudentDTO studentDTO=getByIdStudent(id);
        studentRepository.deleteById(id);
        return studentDTO;
    }

    public  List<StudentDTO> getByNameStudent(String name){
        List<StudentEntity> student=studentRepository.findByName(name);
        List<StudentDTO> dto=new LinkedList<>();
        for (StudentEntity st:student) {
            StudentDTO studentDTO=toDto(st);
            dto.add(studentDTO);
        }
        return dto;
    }
    public  List<StudentDTO> getBySurnameStudent(String surname){
        List<StudentEntity> student=studentRepository.findBySurname(surname);
        List<StudentDTO> dto=new LinkedList<>();
        for (StudentEntity st:student) {
            StudentDTO studentDTO=toDto(st);
            dto.add(studentDTO);
        }
        return dto;
    }

    public  List<StudentDTO> getByLevelStudent(Integer level){
        List<StudentEntity> student=studentRepository.findByLevel(level);
        List<StudentDTO> dto=new LinkedList<>();
        for (StudentEntity st:student) {
            StudentDTO studentDTO=toDto(st);
            dto.add(studentDTO);
        }
        return dto;
    }
    public  List<StudentDTO> getByAgeStudent(Integer age){
        List<StudentEntity> student=studentRepository.findByAge(age);
        List<StudentDTO> dto=new LinkedList<>();
        for (StudentEntity st:student) {
            StudentDTO studentDTO=toDto(st);
            dto.add(studentDTO);
        }
        return dto;
    }
    public  List<StudentDTO> getByGenderStudent(String gender){
        List<StudentEntity> student=studentRepository.findByGender(gender);
        List<StudentDTO> dto=new LinkedList<>();
        for (StudentEntity st:student) {
            StudentDTO studentDTO=toDto(st);
            dto.add(studentDTO);
        }
        return dto;
    }

    public  List<StudentDTO> getByDateStudent(LocalDate date){
        List<StudentEntity> student=studentRepository.findByCreatedDate(date);
        List<StudentDTO> dto=new LinkedList<>();
        for (StudentEntity st:student) {
            StudentDTO studentDTO=toDto(st);
            dto.add(studentDTO);
        }
        return dto;
    }


    public  List<StudentDTO> getByBetweenDate(LocalDate from,LocalDate to){
        List<StudentEntity> studentEntities=studentRepository.findByCreatedDateBetween(from,to);
        List<StudentDTO> studentDTOList=new LinkedList<>();
        for (StudentEntity st:studentEntities) {
          StudentDTO studentDTO=toDto(st);
          studentDTOList.add(studentDTO);
        }
        return studentDTOList;
    }
    public Page<StudentDTO> getAllPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentEntity> pageObj = studentRepository.findAll(pageable);
        List<StudentEntity> studentEntities = pageObj.getContent();
        Long totalElements = pageObj.getTotalElements();
        List<StudentDTO> studentDTOS = new LinkedList<>();
        for (StudentEntity student : studentEntities) {
            StudentDTO studentDTO=toDto(student);
            studentDTOS.add(studentDTO);
        }
        Page<StudentDTO> studentDTOPage=new PageImpl<>(studentDTOS,pageable,totalElements);
        return studentDTOPage;
    }
    public Page<StudentDTO> getAllPageSort(Integer page, Integer size,Integer level) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentEntity> pageObj = studentRepository.findByLevelOrderById(level,pageable);
        List<StudentEntity> studentEntities = pageObj.getContent();
        Long totalElements = pageObj.getTotalElements();
        List<StudentDTO> studentDTOS = new LinkedList<>();
        for (StudentEntity student : studentEntities) {
            StudentDTO studentDTO=toDto(student);
            studentDTOS.add(studentDTO);
        }
        Page<StudentDTO> studentDTOPage=new PageImpl<>(studentDTOS,pageable,totalElements);
        return studentDTOPage;
    }
    public Page<StudentDTO> getAllPageSortByCreated(Integer page, Integer size,String  gender) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentEntity> pageObj = studentRepository.findByGenderOrderByCreatedDate(gender,pageable);
        List<StudentEntity> studentEntities = pageObj.getContent();
        Long totalElements = pageObj.getTotalElements();
        List<StudentDTO> studentDTOS = new LinkedList<>();
        for (StudentEntity student : studentEntities) {
            StudentDTO studentDTO=toDto(student);
            studentDTOS.add(studentDTO);
        }
        Page<StudentDTO> studentDTOPage=new PageImpl<>(studentDTOS,pageable,totalElements);
        return studentDTOPage;
    }

   
}
