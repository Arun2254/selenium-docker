package com.automationpractice.pages;

import com.support.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by ayellapu on 7/2/20.
 */
public class ShippingPage extends BasePage {

    @FindBy(className = "page-heading")
    private WebElement pageHeadingLocator;

    @FindBy(name = "processCarrier")
    private WebElement proceedToPaymentBtn;

    @FindBy(name = "cgv")
    private WebElement checkBoxLocator;


    public ShippingPage(WebDriver driver) {
        super(driver);
    }

    public boolean isShippingPageDisplayed() {
        return pageHeadingLocator.getText().equalsIgnoreCase("Shipping");
    }

    public void clickOnCheckBox() {
        checkBoxLocator.click();
    }

    public PaymentPage clickOnProceedToPayment() {
        proceedToPaymentBtn.click();
        return new PaymentPage(driver);
    }

}
