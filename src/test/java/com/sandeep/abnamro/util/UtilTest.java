package com.sandeep.abnamro.util;

import com.sandeep.abnamro.domain.ClientInfo;
import com.sandeep.abnamro.domain.ProductInfo;
import com.sandeep.abnamro.domain.TransactionEntry;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class UtilTest {

    @Autowired
    private Util util;

    @Test
    public void mapTransactionEntryTest() {
        ProductInfo productInfo = ProductInfo.builder()
                .exchangeCode("SGX")
                .productGroup("FU")
                .symbol("NK")
                .expirationDate("20100910").build();

        ClientInfo clientInfo = ClientInfo.builder()
                .clientType("CL")
                .clientNumber("4321")
                .accountNumber("0002")
                .subaccountNumber("0001").build();

        TransactionEntry transactionEntryExpected = TransactionEntry.builder()
                .clientInfo(clientInfo)
                .productInfo(productInfo)
                .quantityLong(1)
                .quantityShort(0).build();



        String transactionString = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O";
        TransactionEntry transactionEntryActual = util.mapTransactionEntry(transactionString);
        log.info("---"+transactionEntryExpected+"---");
        log.info("---"+transactionEntryActual+"---");
        Assert.assertNotNull(transactionEntryActual);
        Assert.assertEquals(transactionEntryExpected.toString(), transactionEntryActual.toString());
    }
}
