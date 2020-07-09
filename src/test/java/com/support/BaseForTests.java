package com.support;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * Created by ayellapu on 7/2/20.
 */
public class BaseForTests extends InitializeDriver {


    @BeforeTest
    @Parameters({"modeOfExecution"})
    public void beforeSuite(@Optional("Local") String modeOfExecution) throws MalformedURLException, URISyntaxException {
        if(modeOfExecution.equalsIgnoreCase("Remote")) {
            setupRemoteDriver(System.getProperty("BROWSER"));
        } else if(modeOfExecution.equalsIgnoreCase("Local")) {
            setupDriver(System.getProperty("BROWSER"));
        }
    }

    @AfterTest
    public void afterTest() {
        closeDriver();
    }

}
