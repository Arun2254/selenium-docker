package com.automationpractice.pages;

import com.support.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by ayellapu on 7/2/20.
 */
public class LoginPage extends BasePage {

    @FindBy(id = "email")
    private WebElement txtEmailAddress;

    @FindBy(id = "passwd")
    private WebElement txtPassword;

    @FindBy(id = "SubmitLogin")
    private WebElement btnSignin;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        wait.until(ExpectedConditions.visibilityOf(this.btnSignin));
        return btnSignin.isDisplayed();
    }

    public AccountPage onClickSignIn(String email, String pwd) {
        this.txtEmailAddress.sendKeys(email);
        this.txtPassword.sendKeys(pwd);
        this.btnSignin.click();
        return new AccountPage(driver);
    }

}
