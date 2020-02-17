package com.sandeep.abnamro.controller;

import com.sandeep.abnamro.domain.DailyReportOutput;
import com.sandeep.abnamro.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Endpoint API for getting daily transaction report
 */

@RestController
@RequestMapping("/v1")
public class TransactionController {

    private ReportService reportService;

    @Autowired
    public TransactionController(final ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Returns daily summary report with client information, product information and
     * total transaction amount
     *
     * @param filePath Optional input path. Not used. For future enhancement.
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<List<DailyReportOutput>> getDailySummaryReport(@RequestParam(value = "filePath", required = false) String filePath)  throws Exception {

        try {

            List<DailyReportOutput> dailyReportOutputs = reportService.processInput(filePath);

          //  log.info("The daily transaction report is : ", dailyReportOutputs);
            return new ResponseEntity<> (dailyReportOutputs, HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
