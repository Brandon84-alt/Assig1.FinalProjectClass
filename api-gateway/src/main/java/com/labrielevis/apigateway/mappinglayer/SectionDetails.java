package com.labrielevis.apigateway.mappinglayer;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
public class SectionDetails {

    private String sectionId;   //public id -we create
    private Integer courseNumber;
    private Integer roomNumber;
    private Integer teacherId;

    public SectionDetails(Integer courseNumber, Integer roomNumber, Integer teacherId){
        this.courseNumber = courseNumber;
        this.roomNumber = roomNumber;
        this.teacherId = teacherId;
    }
}
