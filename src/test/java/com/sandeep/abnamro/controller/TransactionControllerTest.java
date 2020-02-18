package com.sandeep.abnamro.controller;

import com.sandeep.abnamro.domain.ClientInfo;
import com.sandeep.abnamro.domain.DailyReportOutput;
import com.sandeep.abnamro.domain.ProductInfo;
import com.sandeep.abnamro.service.LocalInputProcessor;
import com.sandeep.abnamro.service.ReportService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = TransactionController.class)
public class TransactionControllerTest {

    @MockBean
    private ReportService reportService;;

    @MockBean
    private LocalInputProcessor localInputProcessor;

    @Autowired
    private MockMvc mockMvc;

    private List<DailyReportOutput> dailyReportList;
    private List<String> transactionString;

    @Before
    public void setup() throws Exception {
        dailyReportList = new ArrayList<>();
        transactionString = new ArrayList<>();
        DailyReportOutput dailyReportOutput = DailyReportOutput.builder()
                .clientInfo(ClientInfo.builder()
                        .clientType("CL")
                        .accountNumber("1234")
                        .clientNumber("0003")
                        .subaccountNumber("0001")
                        .build())
                .productInfo(ProductInfo.builder()
                        .productGroup("FU")
                        .expirationDate("20100910")
                        .symbol("N1")
                        .exchangeCode("CME")
                        .build())
                .totalTransactionAmount(285).build();
        dailyReportList.add(dailyReportOutput);
        transactionString.add("315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O");

    }

    @Test
    public void getReportSuccess() throws Exception {
        Mockito.when(reportService.processInput(Mockito.any())).thenReturn(dailyReportList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/report");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"clientInfo\":{\"clientType\":\"CL\",\"clientNumber\":\"0003\",\"accountNumber\":\"1234\",\"subaccountNumber\":\"0001\"},\"productInfo\":{\"exchangeCode\":\"CME\",\"productGroup\":\"FU\",\"symbol\":\"N1\",\"expirationDate\":\"20100910\"},\"totalTransactionAmount\":285}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getReportException() throws Exception {
        Mockito.when(reportService.processInput(Mockito.any())).thenThrow(new Exception("Some exception"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/report");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println("____"+result.getResolvedException().getMessage());


        Assert.assertEquals("500 INTERNAL_SERVER_ERROR \"Some exception\"; nested exception is java.lang.Exception: Some exception", result.getResolvedException().getMessage());
    }
}
