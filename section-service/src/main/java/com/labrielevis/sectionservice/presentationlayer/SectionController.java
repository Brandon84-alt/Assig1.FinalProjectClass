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
    public Mono<ResponseEntity<SectionDTO>> updateSectionBySectionIdString(@PathVariable String SectionIdString,
                                                                             @RequestBody Mono<SectionDTO> sectionDTOMono){
        return sectionService.updateSection(SectionIdString, sectionDTOMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("{SectionIdString}")
    public Mono<ResponseEntity<SectionDTO>> getSectionBySectionId(@PathVariable String SectionIdString){
        return sectionService.getSectionBySectionId(SectionIdString)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{SectionIdString}")
    public Mono<Void> deleteProduct(@PathVariable String SectionIdString){
        return sectionService.deleteSection(SectionIdString);
    }
}
