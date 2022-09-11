package com.labrielevis.teacherservice.servicelayer;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

