package com.learn.learnRESTAPI.service.impl;

import com.learn.learnRESTAPI.dto.AddStudentDto;
import com.learn.learnRESTAPI.dto.StudentDto;
import com.learn.learnRESTAPI.entity.Student;
import com.learn.learnRESTAPI.repository.StudentRepository;
import com.learn.learnRESTAPI.service.StudentServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // create the constuctor with required variables
public class StudentServicesImpl implements StudentServices {

    private final StudentRepository studentRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students =  studentRepo.findAll();

//        return students
//                .stream()
//                .map(stu -> new StudentDto(stu.getId(), stu.getName(),stu.getEmail()))
//                .toList();

        return students
                .stream()
                .map(stu -> modelMapper.map(stu, StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student studentById = studentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found by id "+id));
        StudentDto studentDto=  modelMapper.map(studentById,StudentDto.class);
        return studentDto;
    }

    @Override
    public StudentDto createStudent(AddStudentDto addStudentDto) {
        Student newStudent = modelMapper.map(addStudentDto,Student.class);
        Student student = studentRepo.save(newStudent);
        return modelMapper.map(student,StudentDto.class);
    }


}
