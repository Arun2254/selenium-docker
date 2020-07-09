package com.automationpractice.pages;

import com.support.BasePage;
import com.utilities.Helper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by ayellapu on 7/2/20.
 */
public class PaymentPage extends BasePage {

    Helper helper;

    @FindBy(className = "page-heading")
    private WebElement pageHeadingLocator;

    @FindBy(className = "bankwire")
    private WebElement payByBankWireLocator;

    @FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
    private WebElement confirmOrderBtn;


    public PaymentPage(WebDriver driver) {
        super(driver);
    }


    public boolean isPaymentPageDisplayed() {
        return pageHeadingLocator.getText().contains("Payment");
    }

    public void clickOnPayByBankWire() {
        payByBankWireLocator.click();
    }

    public OrderConfirmationPage clickOnConfirmOrder() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,480)","");
        confirmOrderBtn.click();
        return new OrderConfirmationPage(driver);
    }




}
