package com.kadydmi.alpha_challenge5.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RequestReceiptNoPromoDTO {

    @JsonProperty("shopId")
    private int shopId;

    @JsonProperty("loyaltyCard")
    private boolean isLoyaltyCard;

    @JsonProperty("positions")
    private List<RequestReceiptPositionDto> positions;

}
