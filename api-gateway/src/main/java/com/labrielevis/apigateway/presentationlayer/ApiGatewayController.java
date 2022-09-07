package com.labrielevis.apigateway.presentationlayer;

import com.labrielevis.apigateway.domainclientlayer.SectionServiceClient;
import com.labrielevis.apigateway.domainclientlayer.TeacherServiceClient;
import com.labrielevis.apigateway.dtos.SectionDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/gateway")
public class ApiGatewayController {

    private final TeacherServiceClient teacherServiceClient;

    private final SectionServiceClient sectionServiceClient;


    @GetMapping(value = "section/{sectionId}")
    public Mono<SectionDetails> getSectionInfo(final @PathVariable int sectionId) {
        return sectionServiceClient.getSection(sectionId);
    }

    @GetMapping(value = "sections")
    public Flux<SectionDetails> getSections() {
        return sectionServiceClient.getSections();
    }



}
