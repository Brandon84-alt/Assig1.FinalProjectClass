package com.labrielevis.teacherservice.dataaccesslayer;


import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

@DataMongoTest
class TeacherRepoTest {

    //@Autowired
    //private WebTestClient webTestClient;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void shouldSaveSingleTeacher(){
        // arrange & act
        Publisher<Teacher> setup = teacherRepository.deleteAll().thenMany(teacherRepository.save(buildTeacher()));
        // assert
        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();
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
