package com.labrielevis.apigateway.presentationlayer;

import com.labrielevis.apigateway.businesslayer.CollegeAggregateService;
import com.labrielevis.apigateway.domainclientlayer.SectionServiceClient;
import com.labrielevis.apigateway.domainclientlayer.TeacherServiceClient;
import com.labrielevis.apigateway.mappinglayer.CollegeAggregate;
import com.labrielevis.apigateway.mappinglayer.SectionDetails;
import com.labrielevis.apigateway.mappinglayer.TeacherDetails;
import com.labrielevis.apigateway.mappinglayer.TeacherSummaryModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("colleges")
public class CollegeController {

    @Autowired
    CollegeAggregateService collegeAggregateService;

    @GetMapping()
    public Mono<ResponseEntity<CollegeAggregate>> getCollegeByCollegeId() {
        return collegeAggregateService.getAll()
                .map(ResponseEntity::ok)
                . defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/teachers")
    public Mono<ResponseEntity<TeacherSummaryModel>> createTeacher(@RequestBody TeacherDetails teacherDetails) {
        return collegeAggregateService.createTeacher(Mono.just(teacherDetails))
                .map(ResponseEntity::ok)
                . defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{teacherId}")
    public Mono<ResponseEntity<TeacherSummaryModel>> updateTeacher(@PathVariable String teacherId,
                                                                   @RequestBody TeacherDetails teacherDetails) {
        return collegeAggregateService.updateTeacher(teacherId, Mono.just(teacherDetails))
                .map(ResponseEntity::ok)
                . defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @DeleteMapping()
    public Mono<Void> deleteAllSections() {
        return collegeAggregateService.deleteAllSections();
    }
}
