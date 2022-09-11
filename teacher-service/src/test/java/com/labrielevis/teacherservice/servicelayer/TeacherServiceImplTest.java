package com.labrielevis.teacherservice.servicelayer;

import com.labrielevis.teacherservice.dataaccesslayer.Teacher;
import com.labrielevis.teacherservice.dataaccesslayer.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWebTestClient
class TeacherServiceImplTest {

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @Test
    void getTeacherByTeacherId(){
        Teacher teacher = buildTeacher();
        when(teacherRepository.findTeacherByTeacherId(anyString())).thenReturn(Mono.just(teacher));
        String TeacherId = teacher.getTeacherId();

        Mono<TeacherDTO> teacherDTOMono = teacherService.getTeacherByTeacherIdString(TeacherId);

        StepVerifier
                .create(teacherDTOMono)
                .consumeNextWith(foundProduct -> {
                    assertEquals(teacher.getTeacherId(), foundProduct.getTeacherId());
                    assertEquals(teacher.getFirstName(), foundProduct.getFirstName());
                    assertEquals(teacher.getLastName(), foundProduct.getLastName());
                    assertEquals(teacher.getEmail(), foundProduct.getEmail());
                })
                .verifyComplete();
    }

    private Teacher buildTeacher() {
        return Teacher.builder()
                .teacherId("")
                .firstName("Felix")
                .lastName("Labrie")
                .email("felix.labrie@crcmail.net")
                .build();
    }

}
