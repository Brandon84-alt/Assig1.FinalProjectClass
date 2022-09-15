package com.labrielevis.apigateway.mappinglayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TeacherSummaryModel {
    private String firstName;
    private String lastName;
    private String email;
    private String sectionId;
}
