package com.automationpractice.pages;

import com.support.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by ayellapu on 7/2/20.
 */
public class AddressPage extends BasePage {

    @FindBy(className = "page-heading")
    private WebElement pageHeadingLocator;

    @FindBy(name = "processAddress")
    private WebElement proceedToCheckoutBtn;

    public AddressPage (WebDriver driver) {
        super(driver);
    }

    public ShippingPage clickOnProceedToShippingPage() {
        proceedToCheckoutBtn.click();
        return new ShippingPage(driver);
    }

    public boolean isAddressPageDisplayed() {
        return pageHeadingLocator.getText().equalsIgnoreCase("Addresses");
    }
}
