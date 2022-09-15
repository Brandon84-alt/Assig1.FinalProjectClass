package com.labrielevis.apigateway.presentationlayer;

import com.labrielevis.apigateway.domainclientlayer.TeacherServiceClient;
import com.labrielevis.apigateway.mappinglayer.TeacherDetails;
import com.labrielevis.apigateway.mappinglayer.TeacherSummaryModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
class TeacherControllerIntegrationTest {

    private final TeacherDetails teacherDetails = buildTeacher();
    private final String TEACHER_ID_IS_OK = teacherDetails.getTeacherId();
    //private final String TEACHER_FIRST_NAME_IS_OK = teacherDetails.getFirstName();
    //private final String TEACHER_LAST_NAME_IS_OK = teacherDetails.getLastName();

    @Autowired
    private WebTestClient client;

    @MockBean
    private TeacherServiceClient teacherServiceClient;

    @Test
    void getTeacherByTeacherId() {

        when(teacherServiceClient.getTeacherByTeacherId(anyString())).thenReturn(Mono.just(teacherDetails));

        client
                .get()
                .uri("/teachers/" + TEACHER_ID_IS_OK)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(TeacherSummaryModel.class)
                .value((dto) -> {
                    assertThat(dto.getFirstName()).isEqualTo(teacherDetails.getFirstName());
                    assertThat(dto.getLastName()).isEqualTo(teacherDetails.getLastName());
                    assertThat(dto.getEmail()).isEqualTo(teacherDetails.getEmail());
                    assertThat(dto.getSectionId()).isEqualTo(teacherDetails.getSectionId());
                });

    }

    @Test
    void deleteTeacherByTeacherId() {

        when(teacherServiceClient.deleteTeacher(anyString())).thenReturn(Mono.empty());

        client.delete()
                .uri("/teachers" + TEACHER_ID_IS_OK)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is4xxClientError();

    }

    /*@Test
    void insertTeacher() {
        when(teacherServiceClient.insertTeacher(anyString())).thenReturn(Mono.just(teacherDetails));

        client
                .post()
                .uri("/teachers" + "{\"firstName\": \"Felix\",\"lastName\": \"Mirsalari\",\"email\": \"reza.mirsalari@crcmail.net\",\"sectionId\": \"55647\"}")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(TeacherSummaryModel.class)
                .value((dto) -> {
                    assertThat(dto.getFirstName()).isEqualTo("Felix");
                });

    } */

    private TeacherDetails buildTeacher() {

        return TeacherDetails.builder()
                .teacherId("48b0f8c1-c341-4130-b945-463b43cfc9de")
                .firstName("Felix")
                .lastName("Labrie")
                .email("felix.labrie@crcmail.net")
                .sectionId("1234")
                .build();

    }

}
