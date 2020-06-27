package com.kadydmi.alpha_challenge5.runtime;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface DecisionEndpoint {

    Mono<ServerResponse> processReq(ServerRequest serverRequest);
}
