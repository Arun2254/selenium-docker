package com.support;

import org.apache.http.client.utils.URIBuilder;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * Created by ayellapu on 7/2/20.
 */
public class InitializeDriver {

    public WebDriver driver;
    public WebDriverWait wait;
    private DesiredCapabilities desiredCapabilities;

    public void setupDriver(String browser) throws URISyntaxException, MalformedURLException {


        if(browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
            desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setPlatform(Platform.ANY);
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/geckodriver");
            desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setPlatform(Platform.ANY);
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else {
            System.out.println("Incorrect Mode Of Execution , Running Scripts in Local with Firefox Driver");
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/geckodriver");
            desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setPlatform(Platform.ANY);
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }

        driverOptions();
    }

    public void setupRemoteDriver(String browser) throws URISyntaxException, MalformedURLException {

        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost(System.getProperty("HUB_HOST"))
//                .setHost("localhost")
                .setPort(4444)
                .setPath("/wd/hub")
                .build();
        driver = new RemoteWebDriver(uri.toURL(),getCapabilities(browser));
        driverOptions();
    }

    public DesiredCapabilities getCapabilities(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox":
                System.out.println("Opening firefox driver");
                return desiredCapabilities.firefox();
            case "chrome":
                System.out.println("Opening chrome driver");
                return desiredCapabilities.chrome();
            case "IE":
                System.out.println("Opening IE driver");
                return desiredCapabilities.internetExplorer();
            default:
                System.out.println("browser : " + browser + " is invalid, Launching Firefox as browser of choice..");
                return desiredCapabilities.firefox();
        }

    }

    private void driverOptions(){
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
