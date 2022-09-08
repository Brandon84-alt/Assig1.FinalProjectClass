package com.labrielevis.apigateway.presentationlayer;

import com.labrielevis.apigateway.businesslayer.SectionService;
import com.labrielevis.apigateway.mappinglayer.SectionDetails;
import com.labrielevis.apigateway.mappinglayer.SectionSummaryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("sections")
public class SectionController {
    @Autowired
    SectionService sectionService;

    @GetMapping()
    public Flux<SectionDetails> getAll() {
        return sectionService.getAll();
    }

    @GetMapping("/{sectionId}")
    public Mono<ResponseEntity<SectionSummaryModel>> getSectionBySectionId(@PathVariable String sectionId) {
        return sectionService.getSectionBySectionId(sectionId)
                .map(ResponseEntity::ok)
                . defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("{sectionId}")
    public Mono<ResponseEntity<SectionSummaryModel>> updateSectionBySectionId(@PathVariable String sectionId,
                                                                              @RequestBody SectionDetails sectionDetails) {
        return sectionService.updateSection(sectionId, Mono.just(sectionDetails))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Mono<SectionSummaryModel> insertSection(@RequestBody SectionDetails sectionDetails) {
        return sectionService.insertSection(Mono.just(sectionDetails));
    }

    @DeleteMapping("{sectionId}")
    public Mono<Void> deleteSection(@PathVariable String sectionId) {
        return sectionService.deleteSection(sectionId);
    }

}
