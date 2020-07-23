package com.automationpractice.pages;

import com.support.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

/**
 * Created by ayellapu on 7/2/20.
 */
public class HomePage extends BasePage {

    @FindBy(linkText = "Contact us")
    private WebElement lnkContactUs;

    @FindBy(className = "login")
    private WebElement lnkSignIn;

    public HomePage(WebDriver driver) {
        super(driver);
        try{
            openURL(getProperty("automationPracticeURL"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean isInitialized() {
        return lnkSignIn.isDisplayed();
    }

    public LoginPage onClickLogin() {
        this.lnkSignIn.click();
        return new LoginPage(driver);
    }

}
