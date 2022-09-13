package com.labrielevis.apigateway.presentationlayer;

import com.labrielevis.apigateway.domainclientlayer.TeacherServiceClient;
import com.labrielevis.apigateway.mappinglayer.TeacherDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
//@WebFluxTest(controllers = TeacherController.class)
@SpringBootTest
@AutoConfigureWebTestClient
class TeacherControllerTest {
//private TeacherDTO dto = buildTeacherDTO();
//private final String TEACHER_ID_OKAY_UUID = dto.getTeacherId();
private TeacherDetails teacherDetails;

    @Autowired
    private WebTestClient client;
    @MockBean
    TeacherServiceClient teacherServiceClient;

    @Test
            public void getATeacherDetails() {

        when(teacherServiceClient.getTeacherByTeacherId(anyString())).thenReturn(Mono.just(teacherDetails));

        //use web client to send request (get request) --> to(teachers/teacherId)
    }

}