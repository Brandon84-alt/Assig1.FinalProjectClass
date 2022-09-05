package com.labrielevis.schoolsystem.servicelayer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SectionService {
    Flux<SectionDTO> getAll();

    Mono<SectionDTO> insertSection(Mono<SectionDTO> sectionDTOMono);
}
