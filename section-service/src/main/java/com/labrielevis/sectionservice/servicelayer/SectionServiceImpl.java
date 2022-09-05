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
}
