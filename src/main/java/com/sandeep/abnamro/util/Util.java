package com.sandeep.abnamro.util;

import com.sandeep.abnamro.domain.ClientInfo;
import com.sandeep.abnamro.domain.ProductInfo;
import com.sandeep.abnamro.domain.TransactionEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Retrieves the elements from the input based on their index
 */
@Component
public class Util {

    public TransactionEntry mapTransactionEntry(String s) {

        String clientType = s.substring(Integer.valueOf(this.clientType.get("index")), Integer.valueOf(this.clientType.get("finalLength")));
        String clientNumber = s.substring(Integer.valueOf(this.clientNumber.get("index")), Integer.valueOf(this.clientNumber.get("finalLength")));
        String accountNumber = s.substring(Integer.valueOf(this.accountNumber.get("index")), Integer.valueOf(this.accountNumber.get("finalLength")));
        String subaccountNumber = s.substring(Integer.valueOf(this.subaccountNumber.get("index")), Integer.valueOf(this.subaccountNumber.get("finalLength")));
        String productGroupCode = s.substring(Integer.valueOf(this.productGroupCode.get("index")), Integer.valueOf(this.productGroupCode.get("finalLength")));
        String exchangeCode = s.substring(Integer.valueOf(this.exchangeCode.get("index")), Integer.valueOf(this.exchangeCode.get("finalLength")));
        String symbol = s.substring(Integer.valueOf(this.symbol.get("index")), Integer.valueOf(this.symbol.get("finalLength")));
        String expirationDate = s.substring(Integer.valueOf(this.expirationDate.get("index")), Integer.valueOf(this.expirationDate.get("finalLength")));
        String quantityLong = s.substring(Integer.valueOf(this.quantityLong.get("index")), Integer.valueOf(this.quantityLong.get("finalLength")));
        String quantityShort = s.substring(Integer.valueOf(this.quantityShort.get("index")), Integer.valueOf(this.quantityShort.get("finalLength")));


        ProductInfo productInfo = ProductInfo.builder()
                .exchangeCode(exchangeCode)
                .productGroup(productGroupCode)
                .symbol(symbol)
                .expirationDate(expirationDate).build();

        ClientInfo clientInfo = ClientInfo.builder()
                .clientType(clientType)
                .clientNumber(clientNumber)
                .accountNumber(accountNumber)
                .subaccountNumber(subaccountNumber).build();

        return TransactionEntry.builder()
                .clientInfo(clientInfo)
                .productInfo(productInfo)
                .quantityLong(Integer.valueOf(quantityLong))
                .quantityShort(Integer.valueOf(quantityShort)).build();
    }

    @Value("#{${recordCode}}")
    private Map<String, String> recordCode;

    @Value("#{${clientType}}")
    private Map<String, String> clientType;

    @Value("#{${clientNumber}}")
    private Map<String, String> clientNumber;

    @Value("#{${accountNumber}}")
    private Map<String, String> accountNumber;

    @Value("#{${subaccountNumber}}")
    private Map<String, String> subaccountNumber;

    @Value("#{${oppositePartyCode}}")
    private Map<String, String> oppositePartyCode;

    @Value("#{${productGroupCode}}")
    private Map<String, String> productGroupCode;

    @Value("#{${exchangeCode}}")
    private Map<String, String> exchangeCode;

    @Value("#{${symbol}}")
    private Map<String, String> symbol;

    @Value("#{${expirationDate}}")
    private Map<String, String> expirationDate;

    @Value("#{${currencyCode}}")
    private Map<String, String> currencyCode;

    @Value("#{${movementCode}}")
    private Map<String, String> movementCode;

    @Value("#{${buySellCode}}")
    private Map<String, String> buySellCode;

    @Value("#{${quantityLongSign}}")
    private Map<String, String> quantityLongSign;

    @Value("#{${quantityLong}}")
    private Map<String, String> quantityLong;

    @Value("#{${quantityShortSign}}")
    private Map<String, String> quantityShortSign;

    @Value("#{${quantityShort}}")
    private Map<String, String> quantityShort;

    @Value("#{${exchFeeDec}}")
    private Map<String, String> exchFeeDec;

    @Value("#{${exchFeeDc}}")
    private Map<String, String> exchFeeDc;

    @Value("#{${exchCurrCode}}")
    private Map<String, String> exchCurrCode;

    @Value("#{${clearingFeeDec}}")
    private Map<String, String> clearingFeeDec;

    @Value("#{${clearingFeeDc}}")
    private Map<String, String> clearingFeeDc;

    @Value("#{${clearingFeeCurrCode}}")
    private Map<String, String> clearingFeeCurrCode;

    @Value("#{${commision}}")
    private Map<String, String> commision;

    @Value("#{${commisionDc}}")
    private Map<String, String> commisionDc;

    @Value("#{${commisionCurrCode}}")
    private Map<String, String> commisionCurrCode;

    @Value("#{${trasactionDate}}")
    private Map<String, String> trasactionDate;

    @Value("#{${futureReference}}")
    private Map<String, String> futureReference;

    @Value("#{${ticketNumber}}")
    private Map<String, String> ticketNumber;

    @Value("#{${externalNumber}}")
    private Map<String, String> externalNumber;

    @Value("#{${transactionPriceDec}}")
    private Map<String, String> transactionPriceDec;

    @Value("#{${traderInitials}}")
    private Map<String, String> traderInitials;

    @Value("#{${oppositeTraderId}}")
    private Map<String, String> oppositeTraderId;

    @Value("#{${openCloseCode}}")
    private Map<String, String> openCloseCode;
}
