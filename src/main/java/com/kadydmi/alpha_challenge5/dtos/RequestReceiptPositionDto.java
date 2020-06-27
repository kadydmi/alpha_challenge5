package com.kadydmi.alpha_challenge5.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class RequestReceiptPositionDto {

    @JsonProperty("itemId")
    private String itemId;

    @JsonProperty("quantity")
    private int quantity;
}
