package com.kadydmi.alpha_challenge5.runtime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
public class PromoEndpoint implements DecisionEndpoint {

    @Override
    public Mono<ServerResponse> processReq(ServerRequest serverRequest) {
        log.debug("promo");
        return ServerResponse.ok().body(BodyInserters.fromValue(""));
    }
}
