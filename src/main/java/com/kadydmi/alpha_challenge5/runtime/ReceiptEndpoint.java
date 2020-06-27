package com.kadydmi.alpha_challenge5.runtime;

import com.kadydmi.alpha_challenge5.CsvReader;
import com.kadydmi.alpha_challenge5.cvso.Item;
import com.kadydmi.alpha_challenge5.dtos.RequestReceiptNoPromoDTO;
import com.kadydmi.alpha_challenge5.dtos.RequestReceiptPositionDto;
import com.kadydmi.alpha_challenge5.dtos.ResponseReceiptPositionDto;
import com.kadydmi.alpha_challenge5.dtos.ResponseReceiptNoPromoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ReceiptEndpoint implements DecisionEndpoint {

    private final CsvReader csvReader;

    @Override
    public Mono<ServerResponse> processReq(ServerRequest serverRequest) {
        log.debug("receipt");
        return serverRequest.bodyToMono(RequestReceiptNoPromoDTO.class)
                .flatMap(this::responseWithBody);
    }

    private Mono<ServerResponse> responseWithBody(RequestReceiptNoPromoDTO dto) {
        ResponseReceiptNoPromoDto receiptNoPromoDto = createResponseDtoNoPromo(dto);
        return ServerResponse.ok().bodyValue(receiptNoPromoDto);
    }

    private ResponseReceiptNoPromoDto createResponseDtoNoPromo(RequestReceiptNoPromoDTO dto) {
        List<ResponseReceiptPositionDto> responsePositions = new ArrayList<>();
        BigDecimal total = BigDecimal.valueOf(0);
        BigDecimal discount = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_EVEN);
        for(RequestReceiptPositionDto pos : dto.getPositions()) {
            Item item = csvReader.getItems().get(pos.getItemId());
            BigDecimal posTotalPrice = item.getPrice().multiply(new BigDecimal(pos.getQuantity()));
            total = total.add(posTotalPrice);
            responsePositions.add(createPosition(pos.getItemId(), item));
        }
        total = total.setScale(2, RoundingMode.HALF_EVEN);
        return new ResponseReceiptNoPromoDto(total, discount, responsePositions);
    }

    private ResponseReceiptPositionDto createPosition(String id, Item item) {
        String name = item.getName();
        BigDecimal price = item.getPrice();
        BigDecimal regularPrice = item.getPrice();
        return new ResponseReceiptPositionDto(id, name, price, regularPrice);
    }
}
