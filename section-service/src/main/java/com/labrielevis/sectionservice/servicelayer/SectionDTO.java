package com.labrielevis.sectionservice.servicelayer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class SectionDTO {

    private String sectionId;       //public id - we create
    private Integer courseNumber;
    private Integer roomNumber;
    private String teacherId;

    //public constructor
    public SectionDTO(Integer courseNumber, Integer roomNumber, String teacherId){

        this.courseNumber = courseNumber;
        this.roomNumber = roomNumber;
        this.teacherId = teacherId;
    }

}
