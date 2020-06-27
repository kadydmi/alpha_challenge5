package com.kadydmi.alpha_challenge5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AutoConfigureWebTestClient
@SpringBootTest(classes = AlphaChallenge5Application.class)
class AlphaChallenge5ApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

	@Test
	public void testInit() {
		WebTestClient.ResponseSpec responseSpec = webTestClient.post().uri("/promo")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.bodyValue("")
				.exchange()
				.expectStatus().isEqualTo(200);
	}

	@Test
	public void testInitReceipt() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Integer>> positions = new ArrayList<>();
		positions.add(Map.of("itemId", 3432166, "quantity", 1));
		ObjectNode requestBody = mapper.createObjectNode();
		requestBody.set("shopId", mapper.valueToTree(1));
		requestBody.set("loyaltyCard", mapper.valueToTree(false));
		requestBody.set("positions", mapper.valueToTree(positions));

		WebTestClient.ResponseSpec responseSpec = webTestClient.post().uri("/receipt")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.bodyValue(requestBody)
				.exchange()
				.expectStatus().isEqualTo(200);
		EntityExchangeResult<String> responseContext = responseSpec.expectBody(String.class).returnResult();
		JsonNode result = mapper.reader().readTree(responseContext.getResponseBody());
		Assertions.assertEquals(result.get("total").asDouble(), 141.99);
		Assertions.assertEquals(result.get("discount").asDouble(), 0.00);
		Assertions.assertNotNull(responseContext.getResponseBody());
	}

}
