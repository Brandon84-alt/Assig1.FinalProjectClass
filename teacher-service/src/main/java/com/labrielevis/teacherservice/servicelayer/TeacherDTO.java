package com.labrielevis.teacherservice.servicelayer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class TeacherDTO {

    private Integer teacherId; //public id - we create
    private String firstName;
    private String lastName;
    private String email;

    private Integer sectionId;


    public TeacherDTO(String firstName, String lastName, String email,Integer sectionId){

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.sectionId = sectionId;
    }

}

