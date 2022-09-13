package com.labrielevis.sectionservice.presentationlayer;

import com.labrielevis.sectionservice.servicelayer.SectionDTO;
import com.labrielevis.sectionservice.servicelayer.SectionService;
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
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {"spring.data.mongodb.port: 27017"})
@AutoConfigureWebTestClient

class SectionControllerUnitTest {

    private final SectionDTO section = buildSection();
    private final String SECTION_ID_OKAY_UUID = section.getSectionId();

    private final Integer SECTION_ROOMNUMBER_OKAY = section.getRoomNumber();
    @Autowired
    private WebTestClient client;

    @MockBean
    private SectionService sectionService;

    @Test
    void getSectionBySectionId()  {
        //arrange
        when(sectionService.getSectionBySectionId(anyString())
                .thenReturn(Mono.just(section)));
        // Publisher<Section> setup = repository.deleteAll().thenMany(repository.save(dto));
        // act & assert
        /* StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();*/

        client
                .get()
                .uri("/sections/" + SECTION_ID_OKAY_UUID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.sectionId").isEqualTo(section.getSectionId())
                .jsonPath("$.courseNumber").isEqualTo(section.getCourseNumber())
                .jsonPath("$.roomNumber").isEqualTo(section.getRoomNumber());

        Mockito.verify(sectionService, times(1))
                .getSectionBySectionId(SECTION_ID_OKAY_UUID);

    }
    private SectionDTO buildSection() {
        return SectionDTO.builder()
                .sectionId("48b0f8c1-c341-4130-b945-463b43cfc9de")
                .courseNumber(101)
                .roomNumber(201)
                .build();
    }
}