package com.labrielevis.sectionservice.servicelayer;

import com.labrielevis.sectionservice.dataacceslayer.SectionRepository;
import com.labrielevis.sectionservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    SectionRepository repository;


    @Override
    public Flux<SectionDTO> getAll(){
        return repository.findAll()
                .map(EntityDtoUtil::toDTO);
    }

    @Override
    public Mono<SectionDTO> insertSection(Mono<SectionDTO> sectionDTOMono){
        return sectionDTOMono
                .map(EntityDtoUtil::toEntity)
                .doOnNext(e -> e.setSectionId(EntityDtoUtil.generateSecIdString()))
                .flatMap(repository::insert)
                .map(EntityDtoUtil::toDTO);
    }


    @Override
    public Mono<SectionDTO> updateSection(String sectionIdString, Mono<SectionDTO> sectionDTOMono) {

        return repository.findSectionBySectionId(sectionIdString)
                .flatMap(p -> sectionDTOMono
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(e -> e.setSectionId(p.getSectionId()))
                        .doOnNext(e -> e.setId(p.getId()))
                )
                .flatMap((repository::save))
                .map(EntityDtoUtil::toDTO);
    }
   @Override
    public Mono<SectionDTO> getSectionBySectionId(String sectionIdString) {
        return repository.findSectionBySectionId(sectionIdString)
                .map(EntityDtoUtil::toDTO);
    }


    @Override
    public Flux<SectionDTO> getSectionByCourseNumber(Integer courseNumber) {
        return repository.findSectionsByCourseNumber(courseNumber)
                .map(EntityDtoUtil::toDTO);
    }


    @Override
    public Mono<Void> deleteSection(String sectionIdString) {
        return repository.deleteSectionBySectionId(sectionIdString);
    }


}
