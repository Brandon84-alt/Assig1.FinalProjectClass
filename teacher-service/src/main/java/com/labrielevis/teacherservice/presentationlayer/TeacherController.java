package com.labrielevis.teacherservice.presentationlayer;


import com.labrielevis.teacherservice.servicelayer.TeacherDTO;
import com.labrielevis.teacherservice.servicelayer.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping()
    public Flux<TeacherDTO> getAllTeachers() {
        return teacherService.getAll();
    }

    @PostMapping()
    public Mono<TeacherDTO> insertTeacher(@RequestBody Mono<TeacherDTO> teacherDTOMono) {
        return teacherService.insertTeacher(teacherDTOMono);
    }

    @PutMapping("{TeacherIdString}")
    public Mono<ResponseEntity<TeacherDTO>> updateTeacherByTeacherId(@PathVariable String TeacherIdString,
                                                                     @RequestBody Mono<TeacherDTO> teacherDTOMono) {
        return teacherService.updateTeacher(TeacherIdString, teacherDTOMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{TeacherIdString}")
    public Mono<Void> deleteTeacher(@PathVariable String TeacherIdString) {
        return teacherService.deleteTeacherByTeacherIDString(TeacherIdString);
    }

    @GetMapping("{TeacherIdString}")
    public Mono<ResponseEntity<TeacherDTO>> getTeacherByTeacherId(@PathVariable String TeacherIdString) {
        return teacherService.getTeacherByTeacherIdString(TeacherIdString)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
