package com.labrielevis.sectionservice.presentationlayer;

import com.labrielevis.sectionservice.servicelayer.SectionDTO;
import com.labrielevis.sectionservice.servicelayer.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping()
    public Flux<SectionDTO> getAllSections(){
        return sectionService.getAll();
    }

    @GetMapping()
    public Mono<SectionDTO> insertSection(@RequestBody Mono<SectionDTO> sectionDTOMono){
        return sectionService.insertSection(sectionDTOMono);
    }

}
