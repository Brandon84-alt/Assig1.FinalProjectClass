package com.labrielevis.sectionservice.servicelayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner {

    @Autowired
    private SectionService sectionService;
    @Override
    public void run(String... args) throws Exception{
        SectionDTO s1 = new SectionDTO(100,201,1);
        SectionDTO s2 = new SectionDTO(101,202,2);
        SectionDTO s3 = new SectionDTO(102,203,3);
        SectionDTO s4 = new SectionDTO(103,204,4);
        SectionDTO s5 = new SectionDTO(104,205,5);

        Flux.just(s1, s2, s3, s4, s5)
                .flatMap(p -> sectionService.insertSection(Mono.just(p))
                        .log(p.toString()))
                .subscribe();

    }
}
