package com.sandeep.abnamro.config;

import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "input.spec")
public class ApplicationPropertiesConfig {

    private int clientType;
    private int clientNumber;
    private int accountNumber;
    private int subaccountNumber;
    private int oppositePartyCode;
    private int productGroupCode;
    private int exchangeCode;
    private int symbol;
    private int expirationDate;
    private int currencyCode;
    private int movementCode;
    private int buySellCode;
    private int quantityLongSign;
    private int quantityLong;
    private int quantityShortSign;
    private int quantityShort;
    private int exchFeeDec;
    private int exchFeeDc;
    private int exchCurrCode;
    private int clearingFeeDec;
    private int clearingFeeDc;
    private int clearingFeeCurrCode;
    private int commision;
    private int commisionDc;
    private int commisionCurrCode;
    private int trasactionDate;
    private int futureReference;
    private int ticketNumber;
    private int externalNumber;
    private int transactionPriceDec;
    private int traderInitials;
    private int oppositeTraderId;
    private int openCloseCode;
    private int filler;

}
