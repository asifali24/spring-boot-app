package com.learn.learnRESTAPI.controller;

import com.learn.learnRESTAPI.dto.AddStudentDto;
import com.learn.learnRESTAPI.dto.StudentDto;
import com.learn.learnRESTAPI.service.StudentServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentServices studentservices;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){

        return ResponseEntity.status(HttpStatus.OK).body(studentservices.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentsById(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(studentservices.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudents(@Valid @RequestBody AddStudentDto addStudentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentservices.createStudent(addStudentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id ){
        studentservices.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> putStudent(@PathVariable Long id, @RequestBody AddStudentDto putStudent){
        return ResponseEntity.status(HttpStatus.OK).body(studentservices.putStudent(putStudent,id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long id, @RequestBody Map<String,String> updateBody){
        return ResponseEntity.status(HttpStatus.OK).body(studentservices.updatePartialStudent(id,updateBody));
    }
}
