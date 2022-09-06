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

    @PutMapping("{SectionUUIDString}")
    public Mono<ResponseEntity<SectionDTO>> updateSectionBySectionUUIDString(@PathVariable String SectionUUIDString,
                                                                             @RequestBody Mono<SectionDTO> sectionDTOMono){
        return sectionService.updateSection(SectionUUIDString, sectionDTOMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("{sectionUUIDString}")
    public Mono<ResponseEntity<SectionDTO>> getSectionBySectionUUID(@PathVariable String sectionUUIDString){
        return sectionService.getSectionBySectionId(sectionUUIDString)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{sectionUUIDString}")
    public Mono<Void> deleteProduct(@PathVariable String sectionUUIDString){
        return sectionService.deleteSection(sectionUUIDString);
    }
}
