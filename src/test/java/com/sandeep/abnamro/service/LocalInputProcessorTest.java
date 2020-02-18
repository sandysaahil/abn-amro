package com.sandeep.abnamro.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LocalInputProcessorTest {

    @Autowired
    private LocalInputProcessor localInputProcessor;

    @Value("classpath:failure.txt")
    private Resource resourceFail;

    @Value("classpath:Input.txt")
    private Resource resourceSuccess;

    @Test
    public void readInputSuccess() {

        String path = "some/dummy/path"; //path is not used. File is read from classpath. This is for future compatibility
        localInputProcessor.setResource(resourceSuccess);
        List<String> transactionsList = localInputProcessor.readInput(path);
        Assert.assertNotNull(transactionsList);
        Assert.assertEquals(717, transactionsList.size());
    }

    @Test
    public void readInputFailure() throws IOException {

        String path = "some/dummy/path"; //path is not used. File is read from classpath. This is for future compatibility

        localInputProcessor.setResource(resourceFail);
        List<String> transactionsList = localInputProcessor.readInput(path);
        Assert.assertNotNull(transactionsList);
        Assert.assertEquals(0, transactionsList.size());
    }
}
