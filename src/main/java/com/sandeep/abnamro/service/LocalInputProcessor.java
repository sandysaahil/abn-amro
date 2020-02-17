package com.sandeep.abnamro.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads the file from the local file implementation which is the resources folder
 */
@Service
@Slf4j
public class LocalInputProcessor implements InputProcessor {

    @Value("classpath:Input.txt")
    private Resource resource;

    /**
     * Reads the file content at the given filepath. Currently usess classpath
     *
     * @param filePath
     * @return
     */
    @Override
    public List<String> readInput(String filePath) {

        List<String> transaction = new ArrayList<>();
        String thisLine = null;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"))){

            while ((thisLine = reader.readLine()) != null) {
                transaction.add(thisLine);
            }
        } catch (Exception e) {
            log.error("File [Input.txt] not found in the classpath");
            e.printStackTrace();
        }

        return transaction;
    }
}
