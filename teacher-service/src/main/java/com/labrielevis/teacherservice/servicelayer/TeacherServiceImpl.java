package com.labrielevis.teacherservice.servicelayer;

import com.labrielevis.teacherservice.dataaccesslayer.TeacherRepository;
import com.labrielevis.teacherservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TeacherServiceImpl implements TeacherService{

    @Autowired
    TeacherRepository repo;

    @Override
    public Flux<TeacherDTO> getAll() {
        return repo.findAll()
                .map(EntityDtoUtil::toDTO);
    }

    @Override
    public Mono<TeacherDTO> insertTeacher(Mono<TeacherDTO> teacherDTOMono) {
        return teacherDTOMono
                .map(EntityDtoUtil::toEntity)
                .doOnNext(e -> e.setTeacherId(EntityDtoUtil.));
    }

    @Override
    public Mono<TeacherDTO> updateTeacher(String teacherIdString, Mono<TeacherDTO> TeacherDTOMono) {
        return null;
    }
}
