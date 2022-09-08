package com.labrielevis.apigateway.domainclientlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labrielevis.apigateway.exceptions.HttpErrorInfo;
import com.labrielevis.apigateway.exceptions.InvalidInputException;
import com.labrielevis.apigateway.exceptions.NotFoundException;
import com.labrielevis.apigateway.mappinglayer.SectionDetails;
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
public class SectionServiceClient {
    private final String sectionServiceUrl;

    private final ObjectMapper mapper;
    private final WebClient.Builder webClientBuilder;




    public SectionServiceClient(ObjectMapper mapper,
                                WebClient.Builder webClientBuilder,
                                @Value("8080") String sectionServicePort,
                                @Value("section-service") String sectionServiceHost){
        this.webClientBuilder = webClientBuilder;
        this.mapper = mapper;
        this.sectionServiceUrl = "https://" + sectionServiceHost + ":" + sectionServicePort;
    }


    public Flux<SectionDetails> getAllSections () {
        String requestUrl = sectionServiceUrl  + "/sections";
        Flux<SectionDetails> SectionResponseModels;
        try {
            SectionResponseModels = webClientBuilder
                    .build()
                    .get()
                    .uri(requestUrl)
                    .retrieve()
                    .bodyToFlux(SectionDetails.class);
        }
        catch (HttpClientErrorException e){
            throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
        }
        System.out.println("Got Sections");
        return SectionResponseModels;
    }


    public Mono<SectionDetails> getSectionBySectionId(String sectionId) {
        String requestUrl = sectionServiceUrl + "/sections/" + sectionId;
        Mono<SectionDetails> sectionResponseModel;
        try {
            sectionResponseModel = webClientBuilder
                    .build()
                    .get()
                    .uri(requestUrl)
                    .retrieve()
                    .bodyToMono(SectionDetails.class);
        }
        catch (HttpClientErrorException e){
            throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
        }
        System.out.println("Section retrieved");
        return sectionResponseModel;
    }

    public Mono<SectionDetails> insertSection(Mono<SectionDetails> sectionDetailsMono) {
        String requestUrl = sectionServiceUrl + "/sections";
        Mono<SectionDetails> sectionResponseModel;
        try {
            sectionResponseModel = webClientBuilder
                    .build()
                    .post()
                    .uri(requestUrl)
                    .body(sectionDetailsMono, SectionDetails.class)
                    .retrieve()
                    .bodyToMono(SectionDetails.class);
        }
        catch (HttpClientErrorException e){
            throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
        }
        System.out.println("Section created");
        return sectionResponseModel;
    }

    public Mono<SectionDetails> updateSection(String sectionId, Mono<SectionDetails> sectionDetailsMono) {
        String requestUrl = sectionServiceUrl + "/sections/" + sectionId;
        Mono<SectionDetails> sectionResponseModel;
        try {
            sectionResponseModel = webClientBuilder
                    .build()
                    .put()
                    .uri(requestUrl)
                    .body(sectionDetailsMono, SectionDetails.class)
                    .retrieve()
                    .bodyToMono(SectionDetails.class);
        }
        catch (HttpClientErrorException e){
            throw handleHttpClientException(new HttpClientErrorException(e.getStatusCode()));
        }
        System.out.println("Section updated");
        return sectionResponseModel;
    }

    public Mono<Void> deleteSection(String sectionId) {
        String requestUrl = sectionServiceUrl + "/section/" + sectionId;
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
        System.out.println("Section deleted");
        return response;
    }

    public Mono<Void> deleteAllSections() {
        String requestUrl = sectionServiceUrl  + "/sections";
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
        System.out.println("Sections deleted");
        return response;
    }

    //Duplicate Code -- To fix soon //TeacherServiceClient
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
