package com.automationpractice.pages;

import com.support.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by ayellapu on 7/2/20.
 */
public class AccountPage extends BasePage {

    @FindBy(linkText = "My account")
    private WebElement lnkMyAccount;

    @FindBy(xpath = "//*[@class='account']/span")
    private WebElement userNameLocator;

    @FindBy(xpath = "//*[@class='logout']")
    private WebElement signoutLocator;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        wait.until(ExpectedConditions.visibilityOf(this.lnkMyAccount));
        return lnkMyAccount.isDisplayed();
    }

    public String getUserName() {
        return userNameLocator.getText();
    }

    public HomePage clickOnLogout() {
        signoutLocator.click();
        return new HomePage(driver);
    }


}
