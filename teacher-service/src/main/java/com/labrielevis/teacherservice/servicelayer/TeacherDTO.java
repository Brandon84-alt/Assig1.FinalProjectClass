package com.labrielevis.teacherservice.servicelayer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class TeacherDTO {

    private String teacherId; //public id - we create
    private String firstName;
    private String lastName;
    private String email;
    private String sectionId;


    public TeacherDTO(String firstName, String lastName, String email,String sectionId){

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.sectionId = sectionId;
    }

}

