package com.support;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.utils.URIBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by ayellapu on 7/2/20.
 */
public class InitializeDriver {

    public static WebDriver driver;
    public WebDriverWait wait;
    private DesiredCapabilities desiredCapabilities;
    public static FileInputStream fileInputStream;
    public static Properties prop = new Properties();
    public static Logger log = Logger.getLogger("Logger");
    public static ExtentReports report = ExtentReporting.getExtentInstance();
    public static ExtentTest test;

    public void setupDriver(String browser) throws URISyntaxException, MalformedURLException {


        if(browser.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-fullscreen");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver");
            desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setPlatform(Platform.ANY);
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/geckodriver");
            desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setPlatform(Platform.ANY);
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else {
            log.warning("Incorrect Mode Of Execution , Running Scripts in Local with Firefox Driver");
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/geckodriver");
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

    public static String getProperty(String propertyName) throws IOException {
//        fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
//        prop.load(fileInputStream);
//        return (String)prop.get(propertyName);
        System.out.println(new File(".").getCanonicalPath());
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream is = loader.getResourceAsStream("config.properties");
        if (is != null){
            prop.load(is);
        }else{
            throw new FileNotFoundException("Property file couldn't be found");
        }
        return prop.getProperty(propertyName);
    }

    public String getScreenshot(String screenShotName, WebDriver driver) throws IOException {
        //create a string variable which will be unique always
        String df = new SimpleDateFormat("yyyyMMddhhss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        String path = "./reports/Screenshots/" + screenShotName + df + ".png";
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(path);
        FileUtils.copyFile(source, destination);
        return "."+path;
    }

    public String FetchPropertyValues(String PropertyKey) throws IOException{
        System.out.println(new File(".").getCanonicalPath());
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream is = loader.getResourceAsStream("config.properties");
        if (is != null){
            prop.load(is);
        }else{
            throw new FileNotFoundException("Property file couldn't be found");
        }
        return prop.getProperty(PropertyKey);
    }

}
