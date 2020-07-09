package com.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by ayellapu on 7/2/20.
 */
public class Helper {

    public void scrollDown(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,480)","");
    }
}
