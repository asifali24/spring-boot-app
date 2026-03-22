package com.learn.learnRESTAPI.service;

import com.learn.learnRESTAPI.dto.AddStudentDto;
import com.learn.learnRESTAPI.dto.StudentDto;

import java.util.List;

public interface StudentServices  {
    List<StudentDto>getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createStudent(AddStudentDto addStudentDto);
}
