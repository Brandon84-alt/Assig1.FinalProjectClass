package com.labrielevis.sectionservice.servicelayer;

import com.labrielevis.sectionservice.dataacceslayer.Section;
import com.labrielevis.sectionservice.dataacceslayer.SectionRepository;
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
//brandon
class SectionServiceImplTest {

    //@Autowired
    //private WebTestClient webTestClient;
    @Autowired
    private SectionService sectionService;

    @MockBean
    private SectionRepository sectionRepository;

    @Test
    void getSectionBySectionId(){
        //arrange
        Section section = buildSection();
        when(sectionRepository.findSectionBySectionId(anyString())).thenReturn(Mono.just(section));
        String SectionId = section.getSectionId();
        //act
        Mono<SectionDTO> sectionDTOMono = sectionService.getSectionBySectionId(SectionId);

        //assert
        StepVerifier
            .create(sectionDTOMono)
                    .consumeNextWith(foundProduct -> {
                        assertEquals(section.getSectionId(), foundProduct.getSectionId());
                        assertEquals(section.getCourseNumber(), foundProduct.getCourseNumber());
                        assertEquals(section.getRoomNumber(), foundProduct.getRoomNumber());
                    })
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