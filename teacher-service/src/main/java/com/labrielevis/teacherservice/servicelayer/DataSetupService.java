package com.labrielevis.teacherservice.servicelayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner {

    @Autowired
    private TeacherService teacherService;

    @Override
    public void run(String... args) throws Exception {
        TeacherDTO t1 = new TeacherDTO("Christine", "Gerard", "christine.gerard@crcmail.net", "55645");
        TeacherDTO t2 = new TeacherDTO("Istiaque", "Shahriar", "istiaque.shahriar@crcmail.net", "55646");
        TeacherDTO t3 = new TeacherDTO("Reza", "Mirsalari", "reza.mirsalari@crcmail.net", "55647");
        TeacherDTO t4 = new TeacherDTO("Jennifer", "Liutec", "jennifer.liutec@crcmail.net", "55648");
        TeacherDTO t5 = new TeacherDTO("John", "Doe", "john.doe@crcmail.net", "55649");

        Flux.just(t1, t2, t3, t4, t5)
                .flatMap(p -> teacherService.insertTeacher(Mono.just(p))
                        .log(p.toString()))
                .subscribe();
    }

}
