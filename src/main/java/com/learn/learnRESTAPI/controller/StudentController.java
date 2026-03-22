package com.learn.learnRESTAPI.controller;

import com.learn.learnRESTAPI.dto.AddStudentDto;
import com.learn.learnRESTAPI.dto.StudentDto;
import com.learn.learnRESTAPI.service.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentServices studentservices;

    @GetMapping("/student")
    public StudentDto getStudent(){
        return new StudentDto(123747474L,"Asif","a@ali.com");
    }


    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents(){

        return ResponseEntity.status(HttpStatus.OK).body(studentservices.getAllStudents());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentsById(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(studentservices.getStudentById(id));
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDto> createStudents(@RequestBody AddStudentDto addStudentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentservices.createStudent(addStudentDto));
    }


}
