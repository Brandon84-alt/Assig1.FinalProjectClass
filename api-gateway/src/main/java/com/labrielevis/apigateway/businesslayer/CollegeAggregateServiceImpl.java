package com.labrielevis.apigateway.businesslayer;

import com.labrielevis.apigateway.domainclientlayer.SectionServiceClient;
import com.labrielevis.apigateway.domainclientlayer.TeacherServiceClient;
import com.labrielevis.apigateway.mappinglayer.*;
import com.labrielevis.apigateway.util.EntityDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CollegeAggregateServiceImpl implements CollegeAggregateService{
    @Autowired
    SectionServiceClient sectionServiceClient;
    @Autowired
    TeacherServiceClient teacherServiceClient;

    @Override
    public Mono<CollegeAggregate> getAll() {
        return this.getSections()
                .doOnNext(EntityDTOUtil::setCollegeAggregate)
                .flatMap(this::getTeachers)
                .map(EntityDTOUtil::getCollegeAggregate);
    }

    @Override
    public Mono<SectionTeacherRequestContext> getSections() {
        return this.sectionServiceClient
                .getAllSections()
                .collectList()
                .map(SectionTeacherRequestContext::new);
    }

    @Override
    public Mono<SectionTeacherRequestContext> getTeachers(SectionTeacherRequestContext sectionTeacherRequestContext) {
        return this.teacherServiceClient
                .getAllTeachers()
                .log()
                .collectList()
                .doOnNext(sectionTeacherRequestContext::setTeacherDetailsList)
                .thenReturn(sectionTeacherRequestContext);
    }

    @Override
    public Mono<TeacherSummaryModel> createTeacher(Mono<TeacherDetails> teacherDetailsMono) {
        return teacherServiceClient.insertTeacher(teacherDetailsMono)
                .flatMap(t -> teacherDetailsMono
                        .doOnNext(e -> t.setFirstName(t.getFirstName()))
                        .doOnNext(e -> t.setLastName(t.getLastName()))
                        .doOnNext(e -> t.setEmail(t.getEmail()))
                        .doOnNext(e ->t.setTeacherId(t.getTeacherId()))
                )
                .flatMap(t -> teacherServiceClient.insertTeacher(teacherDetailsMono))
                .map(EntityDTOUtil::toTeacherSummaryModel);
    }

    @Override
    public Mono<TeacherSummaryModel> updateTeacher(String teacherId, Mono<TeacherDetails> teacherDetailsMono) {
        return teacherServiceClient.getTeacherByTeacherId(teacherId)
                .flatMap(t -> teacherDetailsMono
                        .doOnNext(e -> t.setFirstName(t.getFirstName()))
                        .doOnNext(e -> t.setLastName(t.getLastName()))
                        .doOnNext(e -> t.setEmail(t.getEmail()))
                )
                .flatMap(t -> teacherServiceClient.updateTeacher(teacherId, Mono.just(t)))
                .map(EntityDTOUtil::toTeacherSummaryModel);
    }

    @Override
    public Mono<Void> deleteAllSections() {
        return sectionServiceClient.deleteAllSections();
    }
}