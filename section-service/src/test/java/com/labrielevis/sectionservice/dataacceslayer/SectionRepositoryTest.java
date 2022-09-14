package com.labrielevis.sectionservice.dataacceslayer;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import reactor.test.StepVerifier;
//brandon
@DataMongoTest
class SectionRepositoryTest {
    @Autowired
    private SectionRepository repository;
    @Test
    public void shouldSaveSingleSection(){
        //arrange & act
        Publisher<Section> setup = repository.deleteAll().thenMany(repository.save(buildSection()));
        //assert
        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();
    }
    private Section buildSection() {
        return Section.builder()
                .sectionId("48b0f8c1-c341-4130-b945-463b43cfc9de")
                .courseNumber(101)
                .roomNumber(201)
                .build();
    }
}