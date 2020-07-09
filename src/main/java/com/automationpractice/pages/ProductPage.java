package com.automationpractice.pages;

import com.support.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by ayellapu on 7/2/20.
 */
public class ProductPage extends BasePage {

    @FindBy(name = "Submit")
    private WebElement addToCartButton ;

    @FindBy(linkText = "Proceed to checkout")
    private WebElement proceedToCheckoutLink;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart() {
        addToCartButton.click();
    }

    public CartPage clickOnProceedToCheckout() {
        proceedToCheckoutLink.click();
        return new CartPage(driver);
    }
}
