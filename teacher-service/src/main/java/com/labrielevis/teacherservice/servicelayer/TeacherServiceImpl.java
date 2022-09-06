package com.labrielevis.teacherservice.servicelayer;

import com.labrielevis.teacherservice.dataaccesslayer.TeacherRepository;
import com.labrielevis.teacherservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    TeacherRepository repo;

    @Override
    public Flux<TeacherDTO> getAll() {
        return repo.findAll()
                .map(EntityDtoUtil::toDTO);
    }

    @Override
    public Mono<TeacherDTO> getTeacherByTeacherIdString(String teacherIdString) {
        return repo.findTeacherByTeacherId(teacherIdString)
                .map(EntityDtoUtil::toDTO);
    }

    @Override
    public Mono<TeacherDTO> insertTeacher(Mono<TeacherDTO> teacherDTOMono) {
        return teacherDTOMono
                .map(EntityDtoUtil::toEntity)
                .doOnNext(e -> e.setTeacherId(EntityDtoUtil.generateTeacherIdString()))
                .flatMap(repo::insert)
                .map(EntityDtoUtil::toDTO);
    }

    @Override
    public Mono<TeacherDTO> updateTeacher(String teacherIdString, Mono<TeacherDTO> TeacherDTOMono) {
        repo.findTeacherByTeacherId(teacherIdString)
                .flatMap(p -> TeacherDTOMono
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(e -> e.setTeacherId(p.getTeacherId()))
                        .doOnNext(e -> e.setId(p.getId())))
                .flatMap(repo::save)
                .map(EntityDtoUtil::toDTO);

        return TeacherDTOMono;
    }

    @Override
    public Mono<Void> deleteTeacherByTeacherIDString(String teacherIDString) {
        return repo.deleteTeacherByTeacherId(teacherIDString);
    }
}
