package com.learn.learnRESTAPI.service.impl;

import com.learn.learnRESTAPI.dto.AddStudentDto;
import com.learn.learnRESTAPI.dto.StudentDto;
import com.learn.learnRESTAPI.entity.Student;
import com.learn.learnRESTAPI.repository.StudentRepository;
import com.learn.learnRESTAPI.service.StudentServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tools.jackson.databind.cfg.MapperBuilder;

import java.util.List;
import java.util.Map;

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

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepo.existsById(id)) throw new IllegalArgumentException("Student does not exist with "+id);
        studentRepo.deleteById(id);
    }

    @Override
    public StudentDto putStudent(AddStudentDto putStudent, Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found by id "+id));

        modelMapper.map(putStudent, student);
        student = studentRepo.save(student);
        return modelMapper.map(student ,StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, String> updateBody) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found by id "+id));

        updateBody.forEach((k,v) ->{
            switch (k){
                case "name":
                    student.setName((String) v);
                    break;
                case "email":
                    student.setEmail((String) v);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid key "+k);
            }
        });
        Student savedStudent = studentRepo.save(student);
        return modelMapper.map(savedStudent, StudentDto.class );
    }
}
