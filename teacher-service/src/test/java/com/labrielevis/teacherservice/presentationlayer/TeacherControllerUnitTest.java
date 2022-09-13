package com.labrielevis.teacherservice.presentationlayer;

import com.labrielevis.teacherservice.dataaccesslayer.Teacher;
import com.labrielevis.teacherservice.servicelayer.TeacherDTO;
import com.labrielevis.teacherservice.servicelayer.TeacherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = TeacherController.class)
class TeacherControllerUnitTest {

    private TeacherDTO dto = buildTeacherDTO();
    private final String TEACHER_ID_OKAY_UUID = dto.getTeacherId();

    @Autowired
    private WebTestClient client;

    @MockBean
    TeacherService teacherService;

    @Test
    void getTeacherByTeacherId() {
        // arrange
        when(teacherService.getTeacherByTeacherIdString(anyString()))
                .thenReturn(Mono.just(dto));

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

        Mockito.verify(teacherService, times(1))
                .getTeacherByTeacherIdString(TEACHER_ID_OKAY_UUID);

    }


    private TeacherDTO buildTeacherDTO() {
        return TeacherDTO.builder()
                .teacherId("48b0f8c1-c341-4130-b945-463b43cfc9de")
                .firstName("Felix")
                .lastName("Labrie")
                .email("felix.labrie@crcmail.net")
                .build();
    }

}
