package com.labrielevis.apigateway.mappinglayer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class CollegeAggregate {

    private List<SectionDetails> sectionDetails;

    private List<TeacherDetails> teacherDetails;


    public CollegeAggregate(List<SectionDetails> sectionDetails) {
        this.sectionDetails = sectionDetails;
    }

}
