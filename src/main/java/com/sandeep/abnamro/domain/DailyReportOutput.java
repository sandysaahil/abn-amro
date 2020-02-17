package com.sandeep.abnamro.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Output data for User
 */
@Data
public class DailyReportOutput {

    private ClientInfo clientInfo;
    private ProductInfo productInfo;
    private Integer totalTransactionAmount;

    @Builder
    public DailyReportOutput(ClientInfo clientInfo, ProductInfo productInfo, Integer totalTransactionAmount) {
        this.clientInfo = clientInfo;
        this.productInfo = productInfo;
        this.totalTransactionAmount = totalTransactionAmount;
    }

    @Override
    public String toString() {
        return "DailyReportOutput{" +
                clientInfo.toString() +
                productInfo.toString() +
                ", Total Transaction Amount=" + totalTransactionAmount +
                '}';
    }
}
