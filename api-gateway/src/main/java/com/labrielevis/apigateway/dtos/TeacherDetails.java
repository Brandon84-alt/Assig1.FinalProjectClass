package com.labrielevis.apigateway.dtos;

import lombok.Data;

@Data
public class TeacherDetails {

    private String teacherId;   //public id -we create
    private String firstName;
    private String lastName;
    private String email;
}
