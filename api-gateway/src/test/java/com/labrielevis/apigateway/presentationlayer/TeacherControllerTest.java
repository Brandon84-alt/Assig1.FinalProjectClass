package com.labrielevis.apigateway.presentationlayer;

import com.labrielevis.apigateway.domainclientlayer.TeacherServiceClient;
import com.labrielevis.apigateway.mappinglayer.TeacherDetails;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

//@WebFluxTest(controllers = TeacherController.class)
@SpringBootTest
@AutoConfigureWebTestClient
//brandon
class TeacherControllerTest {
//private TeacherDTO dto = buildTeacherDTO();
//private final String TEACHER_ID_OKAY_UUID = dto.getTeacherId();

    @Autowired
    private WebTestClient client;
    private final TeacherDetails dto = buildTeacherDetails();
    @MockBean
    TeacherServiceClient teacherServiceClient;

    private final String TEACHER_ID_OKAY_UUID = dto.getTeacherId();
    @Test
    public void getATeacherDetails() {

        when(teacherServiceClient.getTeacherByTeacherId(anyString())).thenReturn(Mono.just(dto));

        //use web client to send request (get request) --> to(teachers/teacherId)
        // act & assert
        client
                .get()
                .uri("/teachers/" + TEACHER_ID_OKAY_UUID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.firstName").isEqualTo(dto.getFirstName())
                .jsonPath("$.teacherId").isEqualTo(dto.getTeacherId())
                .jsonPath("$.lastName").isEqualTo(dto.getLastName())
                .jsonPath("$.email").isEqualTo(dto.getEmail());

        Mockito.verify(teacherServiceClient, times(1))
                .getTeacherByTeacherId(TEACHER_ID_OKAY_UUID);
    }

    private TeacherDetails buildTeacherDetails() {
        return TeacherDetails.builder()
                .teacherId("48b0f8c1-c341-4130-b945-463b43cfc9de")
                .firstName("Felix")
                .lastName("Labrie")
                .email("felix.labrie@crcmail.net")
                .build();
    }

}