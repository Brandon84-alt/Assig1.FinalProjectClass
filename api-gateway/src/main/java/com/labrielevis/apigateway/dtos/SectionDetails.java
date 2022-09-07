package com.labrielevis.apigateway.dtos;


import lombok.Data;

@Data
public class SectionDetails {

    private String sectionId;   //public id -we create
    private Integer courseNumber;
    private Integer roomNumber;
    private Integer teacherId;
}
