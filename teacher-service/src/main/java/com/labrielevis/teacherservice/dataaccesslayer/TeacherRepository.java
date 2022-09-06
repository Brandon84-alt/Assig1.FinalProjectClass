package com.labrielevis.teacherservice.dataaccesslayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TeacherRepository extends ReactiveMongoRepository<Teacher, String> {

    Mono<Teacher> findTeacherByTeacherId(String TeacherIdString);

    Mono<Void> deleteTeacherByTeacherId(String TeacherIdString);

}
