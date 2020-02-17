package com.sandeep.abnamro.service;

import com.sandeep.abnamro.config.ApplicationPropertiesConfig;
import com.sandeep.abnamro.domain.ClientInfo;
import com.sandeep.abnamro.domain.DailyReportOutput;
import com.sandeep.abnamro.domain.ProductInfo;
import com.sandeep.abnamro.domain.TransactionEntry;
import com.sandeep.abnamro.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * Processes the input file and prepares the output for the front end
 *
 */
@Service
@Slf4j
public class ReportService {

    private InputProcessor inputProcessor;
    private ApplicationPropertiesConfig applicationPropertiesConfig;
    private Util util;

    @Autowired
    public ReportService(final InputProcessor inputProcessor, final ApplicationPropertiesConfig applicationPropertiesConfig, final Util util) {
        this.inputProcessor = inputProcessor;
        this.applicationPropertiesConfig = applicationPropertiesConfig;
        this.util = util;
    }

    /**
     * Processes the input file and deduces the final position for each client / product combination
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public List<DailyReportOutput> processInput(final String filePath) throws Exception {

        List<String> transactions = inputProcessor.readInput(filePath);
        if (CollectionUtils.isEmpty(transactions)) {
            throw new RuntimeException("Input file not found or blank");
        }

        List<DailyReportOutput> dailyReportOutputs = new ArrayList<>();
        DailyReportOutput.DailyReportOutputBuilder builder = DailyReportOutput.builder();

        Map<ClientInfo, Map<ProductInfo, List<TransactionEntry>>> clientSpecificMap = transactions.stream()
                .map(this::getTransactionEntry)
                .collect(groupingBy(TransactionEntry::getClientInfo,
                            groupingBy(TransactionEntry::getProductInfo)));

        clientSpecificMap.entrySet().stream()
                .forEach(x -> {
                    builder.clientInfo(x.getKey());

                    Map<ProductInfo, List<TransactionEntry>> value = x.getValue();
                    value.entrySet().stream().forEach(product -> {

                            builder.productInfo(product.getKey());
                            List<TransactionEntry> transactionEntryList = product.getValue();

                            Integer longSum = transactionEntryList.stream().map(t -> t.getQuantityLong()).reduce(0, Integer::sum);
                            Integer shortSum = transactionEntryList.stream().map(t -> t.getQuantityShort()).reduce(0, Integer::sum);

                            builder.totalTransactionAmount(longSum - shortSum);
                            dailyReportOutputs.add(builder.build());
                        }
                    );
                });

        return dailyReportOutputs;
    }

    private TransactionEntry getTransactionEntry(String s) {
        return util.mapTransactionEntry(s);
    }

}
