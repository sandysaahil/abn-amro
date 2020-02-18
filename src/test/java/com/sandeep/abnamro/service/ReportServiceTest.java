package com.sandeep.abnamro.service;

import com.sandeep.abnamro.domain.ClientInfo;
import com.sandeep.abnamro.domain.DailyReportOutput;
import com.sandeep.abnamro.domain.ProductInfo;
import com.sandeep.abnamro.domain.TransactionEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @MockBean
    private LocalInputProcessor localInputProcessor;

    private List<String> transactionList;
    List<DailyReportOutput> dailyReportOutputList;

    @Before
    public void setup() throws Exception {
        transactionList = new ArrayList<>();
        transactionList.add("315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O");
        transactionList.add("315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012400     688058000092500000000             O");
        transactionList.add("315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012450     688098000092500000000             O");
        transactionList.add("315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012530     688158000092450000000             O");

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

        DailyReportOutput dailyReportOutput = DailyReportOutput.builder()
                .clientInfo(clientInfo)
                .productInfo(productInfo)
                .totalTransactionAmount(4).build();

        dailyReportOutputList = new ArrayList<>();
        dailyReportOutputList.add(dailyReportOutput);

    }

    @Test
    public void processInputSuccess() {

        String filePath = ""; // File is read from classpath. This attribute is for future enhancements
        when(localInputProcessor.readInput(Mockito.any(String.class))).thenReturn(transactionList);

        try {
            List<DailyReportOutput> dailyReportOutputs = reportService.processInput(filePath);
            Assert.assertNotNull(dailyReportOutputs);
            Assert.assertEquals(dailyReportOutputList.toString(), dailyReportOutputs.toString());

        } catch (Exception e) {
            fail("No exception expected");
        }

    }

    @Test (expected = RuntimeException.class)
    public void processInputFailure() throws Exception {

        String filePath = ""; // File is read from classpath. This attribute is for future enhancements
        when(localInputProcessor.readInput(Mockito.any(String.class))).thenReturn(null);
        List<DailyReportOutput> dailyReportOutputs = reportService.processInput(filePath);
        Assert.fail("Control should not have reached here");
    }
}
