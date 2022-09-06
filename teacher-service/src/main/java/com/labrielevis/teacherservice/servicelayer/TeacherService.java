package com.labrielevis.teacherservice.servicelayer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherService {

    Flux<TeacherDTO> getAll();

    Mono<TeacherDTO> getTeacherByTeacherIdString(String teacherIdString);

    Mono<TeacherDTO> insertTeacher(Mono<TeacherDTO> teacherDTOMono);

    Mono<TeacherDTO> updateTeacher(String teacherIdString, Mono<TeacherDTO> TeacherDTOMono);

    Mono<Void> deleteTeacherByTeacherIDString(String teacherIDString);

}
