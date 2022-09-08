package com.labrielevis.apigateway.businesslayer;

import com.labrielevis.apigateway.domainclientlayer.SectionServiceClient;
import com.labrielevis.apigateway.mappinglayer.SectionDetails;
import com.labrielevis.apigateway.mappinglayer.SectionSummaryModel;
import com.labrielevis.apigateway.util.EntityDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    SectionServiceClient sectionServiceClient;

    @Override
    public Flux<SectionDetails> getAll() {
        return sectionServiceClient.getAllSections();
    }

    @Override
    public Mono<SectionSummaryModel> getSectionBySectionId(String sectionIdString) {
        return sectionServiceClient.getSectionBySectionId(sectionIdString)
                .map(EntityDTOUtil::toSectionSummaryModel);
    }

    @Override
    public Mono<SectionSummaryModel> updateSection(String sectionId, Mono<SectionDetails> sectionDetailsMono) {
        return sectionServiceClient.updateSection(sectionId, sectionDetailsMono)
                .map(EntityDTOUtil::toSectionSummaryModel);
    }

    @Override
    public Mono<SectionSummaryModel> insertSection(Mono<SectionDetails> sectionDetailsMono) {
        return sectionServiceClient.insertSection(sectionDetailsMono)
                .map(EntityDTOUtil::toSectionSummaryModel);
    }

    @Override
    public Mono<Void> deleteSection(String sectionId) {
        return sectionServiceClient.deleteSection(sectionId);
    }
}
