package com.automationpractice.pages;

import com.support.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by ayellapu on 7/2/20.
 */
public class OrderConfirmationPage extends BasePage {

    @FindBy(className = "page-heading")
    private WebElement pageHeadingLocator;

    @FindBy(xpath = "//div[@class='box']/span[@class='price']")
    private WebElement priceLocator;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOrderConfirmationPageDisplayed() {
        return pageHeadingLocator.getText().equalsIgnoreCase("Order Confirmation");
    }

    public String getPriceOnOrderConfirmation() {
        System.out.println("Price on Order Confirmation: " + priceLocator.getText());
       return priceLocator.getText();
    }

}
