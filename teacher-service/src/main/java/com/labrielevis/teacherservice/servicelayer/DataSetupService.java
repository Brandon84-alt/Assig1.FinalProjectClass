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
        TeacherDTO t1 = new TeacherDTO("Christine", "Gerard", "christine.gerard@crcmail.net");
        TeacherDTO t2 = new TeacherDTO("Istiaque", "Shahriar", "istiaque.shahriar@crcmail.net");
        TeacherDTO t3 = new TeacherDTO("Reza", "Mirsalari", "reza.mirsalari@crcmail.net");
        TeacherDTO t4 = new TeacherDTO("Jennifer", "Liutec", "jennifer.liutec@crcmail.net");
        TeacherDTO t5 = new TeacherDTO("John", "Doe", "john.doe@crcmail.net");

        Flux.just(t1, t2, t3, t4, t5)
                .flatMap(p -> teacherService.insertTeacher(Mono.just(p))
                        .log(p.toString()))
                .subscribe();
    }

}
