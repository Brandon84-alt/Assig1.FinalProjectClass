package com.labrielevis.schoolsystem.servicelayer;

import com.labrielevis.schoolsystem.dataacceslayer.SectionRepository;
import com.labrielevis.schoolsystem.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.text.html.parser.Entity;

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
