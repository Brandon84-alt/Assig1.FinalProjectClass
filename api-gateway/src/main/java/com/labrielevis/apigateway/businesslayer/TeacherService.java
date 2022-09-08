package com.labrielevis.apigateway.businesslayer;

import com.labrielevis.apigateway.mappinglayer.TeacherDetails;
import com.labrielevis.apigateway.mappinglayer.TeacherSummaryModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherService {

    Flux<TeacherDetails> getAll();

    Mono<TeacherSummaryModel> getTeacherByTeacherId(String teacherId);

    Mono<TeacherSummaryModel> updateTeacher(String teacherId, Mono<TeacherDetails> teacherDTOMono);

    Mono<TeacherSummaryModel> insertTeacher(Mono<TeacherDetails> teacherDTO);

    Mono<Void> deleteTeacher(String teacherId);
}
