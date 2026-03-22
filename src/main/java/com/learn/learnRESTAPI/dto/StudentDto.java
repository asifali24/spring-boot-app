package com.learn.learnRESTAPI.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // for creating all getter, setter
@AllArgsConstructor // creating construtor with all argument
@NoArgsConstructor // need to know why
public class StudentDto {
    public  Long id;
    public String name;
    public String email;
}
