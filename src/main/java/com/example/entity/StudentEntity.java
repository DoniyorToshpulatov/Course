package com.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@ToString
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String surname;

    @Column
    private LocalDate createdDate;

    @Column
    private  Integer level;

    @Column
    private  Integer age;

    @Column
    private String gender;

}
