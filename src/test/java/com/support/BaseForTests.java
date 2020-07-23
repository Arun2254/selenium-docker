package com.support;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by ayellapu on 7/2/20.
 */
public class BaseForTests extends InitializeDriver {

    @BeforeTest
    public void beforeSuite() throws IOException, URISyntaxException {
        if(getProperty("modeOfExecution").equalsIgnoreCase("Remote")) {
            setupRemoteDriver(System.getProperty("BROWSER"));
        } else if(getProperty("modeOfExecution").equalsIgnoreCase("Local")) {
            setupDriver(System.getProperty("BROWSER"));
//            setupDriver("firefox");
        }
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        closeDriver();
    }

}
