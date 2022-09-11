package com.labrielevis.teacherservice.dataaccesslayer;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    private String id;          //private id -from database
    private String teacherId;   //public id -we create
    private String firstName;
    private String lastName;
    private String email;
    private String sectionId;
}
