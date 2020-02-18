package com.sandeep.abnamro.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class TransactionEntry {

    private ClientInfo clientInfo;
    private ProductInfo productInfo;
    private Integer quantityLong;
    private Integer quantityShort;

    @Builder
    public TransactionEntry(ClientInfo clientInfo, ProductInfo productInfo, Integer quantityLong, Integer quantityShort) {
        this.clientInfo = clientInfo;
        this.productInfo = productInfo;
        this.quantityLong = quantityLong;
        this.quantityShort = quantityShort;
    }

    @Override
    public String toString() {
        return "TransactionEntry{" +
                "clientInfo=" + clientInfo +
                ", productInfo=" + productInfo +
                ", quantityLong=" + quantityLong +
                ", quantityShort=" + quantityShort +
                '}';
    }
}
