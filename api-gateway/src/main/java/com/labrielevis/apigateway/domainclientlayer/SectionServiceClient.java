package com.labrielevis.apigateway.domainclientlayer;

import com.labrielevis.apigateway.dtos.SectionDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SectionServiceClient {

    private final WebClient.Builder webClientBuilder;
    private String sectionServiceUrl;

    public SectionServiceClient(WebClient.Builder webClientBuilder,
                                @Value("localhost") String sectionServiceHost,
                                @Value("1453") String sectionServicePort
    ) {
        this.webClientBuilder = webClientBuilder;
        sectionServiceUrl = "http://" + sectionServiceHost + ":" + sectionServicePort + "/sections";
    }

    public SectionServiceClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<SectionDetails> getSection(final int sectionId) {

        return webClientBuilder.build().get()
                .uri(sectionServiceUrl + "/{sectionId}", sectionId)
                .retrieve()
                .bodyToMono(SectionDetails.class);
    }

    public Flux<SectionDetails> getSections() {
        return webClientBuilder.build().get()
                .uri(sectionServiceUrl)
                .retrieve()
                .bodyToFlux(SectionDetails.class);
    }
}
