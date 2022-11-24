package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {
    private  Integer id;
    private String name;
    private  Integer duration;
    private LocalDate createdDate;
    private  Double price;
}
