package com.labrielevis.teacherservice.dataaccesslayer;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Teacher {

    @Id
    private String id;          //private id -from database
    private String teacherId;   //public id -we create
    private String firstName;
    private String lastName;
    private String email;
    private String sectionId;
}
