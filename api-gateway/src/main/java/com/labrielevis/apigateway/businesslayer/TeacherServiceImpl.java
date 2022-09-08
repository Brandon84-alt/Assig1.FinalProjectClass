package com.labrielevis.apigateway.businesslayer;

import com.labrielevis.apigateway.domainclientlayer.TeacherServiceClient;
import com.labrielevis.apigateway.mappinglayer.TeacherDetails;
import com.labrielevis.apigateway.mappinglayer.TeacherSummaryModel;
import com.labrielevis.apigateway.util.EntityDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    TeacherServiceClient teacherServiceClient;

    public Flux<TeacherDetails> getAll() {
        return teacherServiceClient.getAllTeachers();
    }
    @Override
    public Mono<TeacherSummaryModel> getTeacherByTeacherId(String teacherIdString) {
        //System.out.println("getTeacherByTeacherId, TeacherServiceImpl in API Gateway");
        return teacherServiceClient.getTeacherByTeacherId(teacherIdString)
                .map(EntityDTOUtil::toTeacherSummaryModel);
    }

    @Override
    public Mono<TeacherSummaryModel> updateTeacher(String teacherId, Mono<TeacherDetails> teacherDTOMono) {
        return teacherServiceClient.updateTeacher(teacherId, teacherDTOMono)
                .map(EntityDTOUtil::toTeacherSummaryModel);

    }

    @Override
    public Mono<TeacherSummaryModel> insertTeacher(Mono<TeacherDetails> teacherDTOMono) {
        return teacherServiceClient.insertTeacher(teacherDTOMono)
                .map(EntityDTOUtil::toTeacherSummaryModel);
    }

    @Override
    public Mono<Void> deleteTeacher(String teacherId) {
        return teacherServiceClient.deleteTeacher(teacherId);
    }
}
