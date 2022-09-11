package com.labrielevis.apigateway.businesslayer;

import com.labrielevis.apigateway.mappinglayer.CollegeAggregate;
import com.labrielevis.apigateway.mappinglayer.SectionTeacherRequestContext;
import com.labrielevis.apigateway.mappinglayer.TeacherDetails;
import com.labrielevis.apigateway.mappinglayer.TeacherSummaryModel;
import reactor.core.publisher.Mono;

public interface CollegeAggregateService {

    Mono<CollegeAggregate> getAll();
    Mono<SectionTeacherRequestContext> getSections();
    Mono<SectionTeacherRequestContext> getTeachers(SectionTeacherRequestContext studentTeacherRequestContext);

    Mono<TeacherSummaryModel> createTeacher(Mono<TeacherDetails> teacherDTOMono);

    Mono<TeacherSummaryModel> updateTeacher(String teacherId, Mono<TeacherDetails> teacherDTOMono);

    Mono<Void> deleteAllSections();
}
