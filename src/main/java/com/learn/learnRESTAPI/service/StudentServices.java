package com.learn.learnRESTAPI.service;

import com.learn.learnRESTAPI.dto.AddStudentDto;
import com.learn.learnRESTAPI.dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentServices  {
    List<StudentDto>getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createStudent(AddStudentDto addStudentDto);

    void deleteStudentById(Long id);

    StudentDto putStudent(AddStudentDto putStudent, Long id);

    StudentDto updatePartialStudent(Long id, Map<String, String> updateBody);
}
