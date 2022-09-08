package com.labrielevis.apigateway.domainclientlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labrielevis.apigateway.exceptions.HttpErrorInfo;
import com.labrielevis.apigateway.exceptions.InvalidInputException;
import com.labrielevis.apigateway.exceptions.NotFoundException;
import com.labrielevis.apigateway.mappinglayer.TeacherDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;


@Slf4j
@Service
public class TeacherServiceClient {

    private final String teacherServiceUrl;
    private final ObjectMapper mapper;
    private final WebClient.Builder webClientBuilder;

    public TeacherServiceClient(ObjectMapper mapper,
                                @Value("${app.teacher-service.port}") String teacherDetailsServicePort,
                                @Value("${app.teacher-service.host}") String teacherDetailsServiceHost,
                                WebClient.Builder webClient){
        this.mapper = mapper;
        this.webClientBuilder = webClient;

        this.teacherServiceUrl = "http://" + teacherDetailsServiceHost + ":" + teacherDetailsServicePort;
    }
    public Flux<TeacherDetails> getAllTeachers () {
        String requestUrl = teacherServiceUrl + "/teachers";
        Flux<TeacherDetails> teachersResponseModels;
        try {
            teachersResponseModels = webClientBuilder
                    .build()
                    .get()
                    .uri(requestUrl)
                    .retrieve()
                    .bodyToFlux(TeacherDetails.class);
        }

        catch (HttpClientErrorException e){
            throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
        }
        System.out.println("Got Teachers");
        return teachersResponseModels;
    }

    public Mono<TeacherDetails> getTeacherByTeacherId(String teacherId) {
        //System.out.println("getTeacherByTeacherId, TeacherServiceClient in API Gateway");
        String requestUrl = teacherServiceUrl + "/teachers/" + teacherId;
        Mono<TeacherDetails> teacherResponseModel;
        try {
            teacherResponseModel = webClientBuilder
                    .build()
                    .get()
                    .uri(requestUrl)
                    .retrieve()
                    .bodyToMono(TeacherDetails.class);
        } catch (HttpClientErrorException e) {
            throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
        }
        System.out.println("Teacher retrieved");
        return teacherResponseModel;
    }


    public Mono<TeacherDetails> insertTeacher(Mono<TeacherDetails> teacherDTOMono) {
        String requestUrl = teacherServiceUrl + "/teachers";
        Mono<TeacherDetails> teacherResponseModel;
        try {
            teacherResponseModel = webClientBuilder
                    .build()
                    .post()
                    .uri(requestUrl)
                    .body(teacherDTOMono, TeacherDetails.class)
                    .retrieve()
                    .bodyToMono(TeacherDetails.class);
        }
        catch (HttpClientErrorException e){
            throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
        }
        System.out.println("Teacher created");
        return teacherResponseModel;
    }

    public Mono<TeacherDetails> updateTeacher(String teacherId, Mono<TeacherDetails> teacherDTO) {
        String requestUrl = teacherServiceUrl + "/teachers/" + teacherId;
        Mono<TeacherDetails> teacherResponseModel;
        try {
            teacherResponseModel = webClientBuilder
                    .build()
                    .put()
                    .uri(requestUrl)
                    .body(teacherDTO, TeacherDetails.class)
                    .retrieve()
                    .bodyToMono(TeacherDetails.class);
        }
        catch (HttpClientErrorException e){
            throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
        }
        System.out.println("Teacher updated");
        return teacherResponseModel;
    }

    public Mono<Void> deleteTeacher(String teacherId) {
        String requestUrl = teacherServiceUrl + "/teachers/" + teacherId;
        Mono<Void> response;
        try {
            response = webClientBuilder
                    .build()
                    .delete()
                    .uri(requestUrl)
                    .retrieve()
                    .bodyToMono(Void.class);
        }
        catch (HttpClientErrorException e){
            throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
        }
        System.out.println("Teacher deleted");
        return response;
    }


    //Duplicate Code -- To fix soon //SectionServiceClient
    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
        switch (ex.getStatusCode()) {
            case NOT_FOUND -> {
                System.out.println("Not Found Thrown");
                return new NotFoundException(getErrorMessage(ex));
            }
            case UNPROCESSABLE_ENTITY -> {
                System.out.println("Unprocessable");
                return new InvalidInputException(getErrorMessage(ex));
            }
            default -> {
                log.warn("Got an unexpected HTTP error: {}, will rethrow it",
                        ex.getStatusCode());
                log.warn("Error body: {}", ex.getResponseBodyAsString());
                return ex;
            }
        }
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (IOException index) {
            return index.getMessage();
        }
    }
}
