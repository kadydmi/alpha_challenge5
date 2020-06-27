package com.kadydmi.alpha_challenge5.cvso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@ToString
public class Item {
    private String name;
    private String groupId;
    private BigDecimal price;
}
