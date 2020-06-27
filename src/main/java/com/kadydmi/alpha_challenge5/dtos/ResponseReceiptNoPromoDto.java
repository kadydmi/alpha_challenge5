package com.kadydmi.alpha_challenge5.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ResponseReceiptNoPromoDto {

    @JsonProperty("total")
    private final BigDecimal total;

    @JsonProperty("discount")
    private final BigDecimal discount;

    @JsonProperty("positions")
    private final List<ResponseReceiptPositionDto> positions;

}
