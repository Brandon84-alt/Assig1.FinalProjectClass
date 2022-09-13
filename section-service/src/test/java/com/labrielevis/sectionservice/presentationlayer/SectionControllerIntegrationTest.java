package com.labrielevis.sectionservice.presentationlayer;

import com.labrielevis.sectionservice.dataacceslayer.Section;
import com.labrielevis.sectionservice.dataacceslayer.SectionRepository;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {"spring.data.mongodb.port: 27017"})
@AutoConfigureWebTestClient
class SectionControllerIntegrationTest {
    private final Section section = buildSection();
    private final String Section_ID_OKAY_UUID = section.getSectionId();
    @Autowired
    private WebTestClient client;
    @Autowired
    private SectionRepository sectionRepository;
    @Test
    void whenProductUUIDIsValid_returnDetailsOfAProduct() {
//arrange
        Publisher<Section> setup = sectionRepository.deleteAll().thenMany(sectionRepository.save(section));
        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();
//act and assert
        client
                .get()
                .uri("/sections/" + Section_ID_OKAY_UUID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)

                .expectBody()
                .jsonPath("$.courseNumber").isEqualTo(section.getCourseNumber())
                .jsonPath("$.roomNumber").isEqualTo(section.getRoomNumber());
    }
    private Section buildSection() {
        return Section.builder()
                .sectionId("48b0f8c1-c341-4130-b945-463b43cfc9de")
                .courseNumber(101)
                .roomNumber(201)
                .build();
    }
}