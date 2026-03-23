package com.learn.learnRESTAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name="Student", indexes = {
                @Index(name = "idx_student_email", columnList = "email"),
                @Index(name = "idx_student_name", columnList = "name")
        }
)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true , nullable = false)
    @Email(message = "Invalid email formate")
    private  String email;

    private Boolean status =true ;
}
