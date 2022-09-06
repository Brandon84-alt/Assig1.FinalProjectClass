package com.labrielevis.sectionservice.servicelayer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SectionService {
    Flux<SectionDTO> getAll();

    Mono<SectionDTO> insertSection(Mono<SectionDTO> sectionDTOMono);

    Mono<SectionDTO> updateSection(String sectionUUIDString, Mono<SectionDTO> SectionDTOMono);

    Mono<SectionDTO> getSectionBySectionId(String sectionUUIDString);

    Mono<Void> deleteSection(String sectionUUIDString);

}
