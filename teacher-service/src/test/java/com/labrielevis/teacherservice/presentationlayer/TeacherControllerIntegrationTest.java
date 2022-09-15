package com.labrielevis.teacherservice.presentationlayer;

import com.labrielevis.teacherservice.dataaccesslayer.Teacher;
import com.labrielevis.teacherservice.dataaccesslayer.TeacherRepository;
import com.labrielevis.teacherservice.servicelayer.TeacherDTO;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = ("spring.data.mongodb.port: 0"))
@AutoConfigureWebTestClient
public class TeacherControllerIntegrationTest {

    private final Teacher teacher = buildTeacher();
    private final String TEACHER_ID_IS_OKAY = teacher.getTeacherId();

    @Autowired
    private WebTestClient client;

    @Autowired
    private TeacherRepository repo;

    @Test
    void whenTeacherIdIsValid_returnDetailsOfTeacher() {
        // arrange
        Publisher<Teacher> setup =  repo.deleteAll().thenMany(repo.save(teacher));

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();

        // act & assert
        client
                .get()
                .uri("/teachers/" + TEACHER_ID_IS_OKAY)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(TeacherDTO.class)
                .value((dto -> {
                    assertThat(dto.getTeacherId()).isEqualTo(teacher.getTeacherId());
                    assertThat(dto.getFirstName()).isEqualTo(teacher.getFirstName());
                    assertThat(dto.getLastName()).isEqualTo(teacher.getLastName());
                    assertThat(dto.getEmail()).isEqualTo(teacher.getEmail());
                }));

    }

    private Teacher buildTeacher() {

        return Teacher.builder()
                .teacherId("48b0f8c1-c341-4130-b945-463b43cfc9de")
                .firstName("Felix")
                .lastName("Labrie")
                .email("felix.labrie@crcmail.net")
                .build();

    }

}
