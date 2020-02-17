package com.sandeep.abnamro.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductInfo {

    private String exchangeCode;
    private String productGroup;
    private String symbol;
    private String expirationDate;

    @Builder
    public ProductInfo(String exchangeCode, String productGroup, String symbol, String expirationDate) {
        this.exchangeCode = exchangeCode;
        this.productGroup = productGroup;
        this.symbol = symbol;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Product_Information{" + exchangeCode.trim() + "_" + productGroup.trim() + "_" + symbol.trim() + "_" + expirationDate +
                '}';
    }
}
