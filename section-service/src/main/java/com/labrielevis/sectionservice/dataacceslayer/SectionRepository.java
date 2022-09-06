package com.labrielevis.sectionservice.dataacceslayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SectionRepository extends ReactiveMongoRepository<Section, String> {

    Mono<Section> findSectionBySectionId(String SectionIdString);
   // Mono<Void> deleteSectionBySectionId(String SectionIdString);

}
