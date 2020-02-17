package com.sandeep.abnamro.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
public class ClientInfo {

    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subaccountNumber;

    @Builder
    public ClientInfo(String clientType, String clientNumber, String accountNumber, String subaccountNumber) {
        this.clientType = clientType;
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
        this.subaccountNumber = subaccountNumber;
    }

    @Override
    public String toString() {
        return "Client_Information{" +
                clientType.trim() + "_" + clientNumber + "_" + accountNumber + "_" + subaccountNumber +
                '}';
    }
}
