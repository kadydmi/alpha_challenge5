package com.kadydmi.alpha_challenge5.runtime;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class PromoEndpoint implements DecisionEndpoint {

    @Override
    public Mono<ServerResponse> processReq(ServerRequest serverRequest) {
        System.out.println("promo");
        return ServerResponse.ok().body(BodyInserters.fromValue(""));
    }
}
