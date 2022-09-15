package com.labrielevis.apigateway.presentationlayer;

import com.labrielevis.apigateway.domainclientlayer.SectionServiceClient;
import com.labrielevis.apigateway.domainclientlayer.TeacherServiceClient;
import com.labrielevis.apigateway.mappinglayer.SectionDetails;
import com.labrielevis.apigateway.mappinglayer.SectionSummaryModel;
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
class SectionControllerIntegrationTest {

    private final SectionDetails sectionDetails = buildSection();
    private final String SECTION_ID_IS_OK = sectionDetails.getSectionId();

    @Autowired
    private WebTestClient client;

    @MockBean
    private SectionServiceClient sectionServiceClient;

    @Test
    void getSectionBySectionId() {

        when(sectionServiceClient.getSectionBySectionId(anyString())).thenReturn(Mono.just(sectionDetails));

        client
                .get()
                .uri("/sections/" + SECTION_ID_IS_OK)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(SectionSummaryModel.class)
                .value((dto) -> {
                    assertThat(dto.getCourseNumber()).isEqualTo(sectionDetails.getCourseNumber());
                        });

    }

    @Test
    void deleteSectionBySectionId() {

        when(sectionServiceClient.deleteSection(anyString())).thenReturn(Mono.empty());

        client
                .delete()
                .uri("/sections" + SECTION_ID_IS_OK)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is4xxClientError();

    }

    private SectionDetails buildSection() {
        return SectionDetails.builder()
                .sectionId("48b0f8c1-c341-4130-b945-463b43cfc9de")
                .courseNumber(101)
                .roomNumber(201)
                .teacherId(1111)
                .build();
    }

}
