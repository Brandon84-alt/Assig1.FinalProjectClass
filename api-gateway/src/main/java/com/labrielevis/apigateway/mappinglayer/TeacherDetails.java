package com.labrielevis.apigateway.mappinglayer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
public class TeacherDetails {

    private String teacherId;   //public id -we create
    private String firstName;
    private String lastName;
    private String email;

    public TeacherDetails(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
