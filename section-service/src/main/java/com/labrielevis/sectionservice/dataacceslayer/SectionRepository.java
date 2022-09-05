package com.labrielevis.sectionservice.dataacceslayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends ReactiveMongoRepository<Section, String> {



}
