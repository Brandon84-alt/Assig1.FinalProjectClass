package com.labrielevis.sectionservice.presentationlayer;

import com.labrielevis.sectionservice.servicelayer.SectionDTO;
import com.labrielevis.sectionservice.servicelayer.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("section")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping()
    public Flux<SectionDTO> getAllSections(){
        return sectionService.getAll();
    }

    @PostMapping()
    public Mono<SectionDTO> insertSection(@RequestBody Mono<SectionDTO> sectionDTOMono){
        return sectionService.insertSection(sectionDTOMono);
    }

    @PutMapping("{SectionIdString}")
    public Mono<ResponseEntity<SectionDTO>> updateSectionBySectionIdString(@PathVariable String sectionIdString,
                                                                             @RequestBody Mono<SectionDTO> sectionDTOMono){
        return sectionService.updateSection(sectionIdString, sectionDTOMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
