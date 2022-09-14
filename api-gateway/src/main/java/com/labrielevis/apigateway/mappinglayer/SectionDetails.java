package com.labrielevis.apigateway.mappinglayer;


import lombok.*;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
