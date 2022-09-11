package com.labrielevis.apigateway.presentationlayer;

import com.labrielevis.apigateway.businesslayer.TeacherService;
import com.labrielevis.apigateway.mappinglayer.TeacherDetails;
import com.labrielevis.apigateway.mappinglayer.TeacherSummaryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping()
    public Flux<TeacherDetails> getAll() {
        return teacherService.getAll();
    }


    @GetMapping("{teacherId}")
    public Mono<ResponseEntity<TeacherSummaryModel>> getTeacherByTeacherId(@PathVariable String teacherId) {
        return teacherService.getTeacherByTeacherId(teacherId)
                .map(ResponseEntity::ok)
                . defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("{teacherId}")
    public Mono<ResponseEntity<TeacherSummaryModel>> updateTeacherByTeacherId(@PathVariable String teacherId,
                                                                              @RequestBody TeacherDetails teacherDTO) {
        return teacherService.updateTeacher(teacherId, Mono.just(teacherDTO))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Mono<TeacherSummaryModel> insertTeacher(@RequestBody TeacherDetails teacherDTO) {
        return teacherService.insertTeacher(Mono.just(teacherDTO));
    }

    @DeleteMapping("{teacherId}")
    public Mono<Void> deleteTeacher(@PathVariable String teacherId) {
        return teacherService.deleteTeacher(teacherId);
    }
}
