package com.labrielevis.apigateway.domainclientlayer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class TeacherServiceClient {

    private final String teacherServiceUrl;
    private final WebClient.Builder webClientBuilder;


    public TeacherServiceClient(WebClient.Builder webClientBuilder,
                                @Value("teacher") String teacherServiceHost,
                                @Value("1454") String teacherServicePort
    ) {
        this.webClientBuilder = webClientBuilder;
        teacherServiceUrl = "http://" + teacherServiceHost + ":" + teacherServicePort + "/teachers";
    }
}
