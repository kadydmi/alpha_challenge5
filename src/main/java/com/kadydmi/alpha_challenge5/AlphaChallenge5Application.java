package com.kadydmi.alpha_challenge5;

import com.kadydmi.alpha_challenge5.runtime.PromoEndpoint;
import com.kadydmi.alpha_challenge5.runtime.ReceiptEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class AlphaChallenge5Application {

	public static void main(String[] args) {
		SpringApplication.run(AlphaChallenge5Application.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> routerFunction(PromoEndpoint promoEndpoint, ReceiptEndpoint receiptEndpoint) {
		return RouterFunctions.route()
				.POST("/promo", RequestPredicates.accept(MediaType.APPLICATION_JSON), promoEndpoint::processReq)
				.POST("/receipt", RequestPredicates.accept(MediaType.APPLICATION_JSON), receiptEndpoint::processReq)
				.build();
	}

}
