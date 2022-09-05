package com.labrielevis.sectionservice.dataacceslayer;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Section {

    @Id
    private String id;          //private id -from database
    private String sectionId;   //public id -we create
    private Integer courseNumber;
    private Integer roomNumber;
    private Integer teacherId;

}
