package com.labrielevis.sectionservice.dataacceslayer;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Section {

    @Id
    private String id;          //private id -from database
    private String sectionId;   //public id -we create
    private Integer courseNumber;
    private Integer roomNumber;
    //private String teacherId;

}
