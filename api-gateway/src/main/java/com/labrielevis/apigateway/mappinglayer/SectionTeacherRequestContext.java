package com.labrielevis.apigateway.mappinglayer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@ToString
@NoArgsConstructor
public class SectionTeacherRequestContext {
    private List<SectionDetails> sectionDetailsList;
    private List<TeacherDetails> teacherDetailsList;
    private CollegeAggregate collegeAggregate;

    public SectionTeacherRequestContext(List<SectionDetails> studentDTOList) {
        this.sectionDetailsList = studentDTOList;
    }
}
