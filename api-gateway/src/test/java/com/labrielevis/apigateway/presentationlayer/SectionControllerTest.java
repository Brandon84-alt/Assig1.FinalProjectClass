package com.labrielevis.apigateway.presentationlayer;

import com.labrielevis.apigateway.domainclientlayer.SectionServiceClient;
import com.labrielevis.apigateway.mappinglayer.SectionDetails;
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

@SpringBootTest
@AutoConfigureWebTestClient
//brandon
class SectionControllerTest {

    @Autowired
    private WebTestClient client;
    private final SectionDetails dto = buildSectionDetails();
    @MockBean
    SectionServiceClient sectionServiceClient;

    private final String Section_ID_OKAY_UUID = dto.getSectionId();
    @Test
    public void getASectionDetails() {

        when(sectionServiceClient.getSectionBySectionId(anyString())).thenReturn(Mono.just(dto));

        //use web client to send request (get request) --> to(teachers/teacherId)
        // act & assert
        client
                .get()
                .uri("/teachers/" + Section_ID_OKAY_UUID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.sectionId").isEqualTo(dto.getSectionId())
                .jsonPath("$.courseNumber").isEqualTo(dto.getCourseNumber())
                .jsonPath("$.roomNumber").isEqualTo(dto.getRoomNumber());

        Mockito.verify(sectionServiceClient, times(1))
                .getSectionBySectionId(Section_ID_OKAY_UUID);
    }

    private SectionDetails buildSectionDetails() {
        return SectionDetails.builder()
                .sectionId("48b0f8c1-c341-4130-b945-463b43cfc9de")
                .courseNumber(101)
                .roomNumber(201)
                .build();
    }

}