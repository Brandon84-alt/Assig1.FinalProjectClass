package com.labrielevis.teacherservice.presentationlayer;


import com.labrielevis.teacherservice.servicelayer.TeacherDTO;
import com.labrielevis.teacherservice.servicelayer.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping()
    public Flux<TeacherDTO> getAllTeachers() {

    }

}
