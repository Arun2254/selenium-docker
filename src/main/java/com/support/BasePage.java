package com.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by ayellapu on 7/2/20.
 */
public class BasePage extends InitializeDriver {

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,30);
        PageFactory.initElements(driver, this);
    }

    public void openURL(String baseURL) {
        driver.get(baseURL);
    }
}
