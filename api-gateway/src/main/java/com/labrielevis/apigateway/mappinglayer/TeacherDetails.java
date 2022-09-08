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

    private String sectionId;

    /*public TeacherDetails(String teacherId, firstName, String lastName, String email, String section){
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.section = section;
    }*/
}
