package com.labrielevis.apigateway.businesslayer;

import com.labrielevis.apigateway.mappinglayer.SectionDetails;
import com.labrielevis.apigateway.mappinglayer.SectionSummaryModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SectionService{
    Flux<SectionDetails> getAll();

    Mono<SectionSummaryModel> getSectionBySectionId(String sectionId);

    Mono<SectionSummaryModel> updateSection(String sectionId, Mono<SectionDetails> sectionDetailsMono);

    Mono<SectionSummaryModel> insertSection(Mono<SectionDetails> SectionDetails);

    Mono<Void> deleteSection(String sectionId);
}
