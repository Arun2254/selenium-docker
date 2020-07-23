package com.automationpractice.pages;

import com.aventstack.extentreports.Status;
import com.support.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by ayellapu on 7/2/20.
 */
public class CartPage extends BasePage {

    @FindBy(linkText = "Faded Short Sleeve T-shirts")
    private WebElement descriptionOfItemLocator;

    @FindBy(linkText = "Proceed to checkout")
    private WebElement proceedToCheckoutLink;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getItemAddedToCart() {
        return descriptionOfItemLocator.getText();
    }

    public AddressPage clickOnProceedToAddressPage() {
        proceedToCheckoutLink.click();
        return new AddressPage(driver);
    }

    public boolean checkIfValidProductAddedToCart(String product) {
        test.log(Status.INFO, "Product added to cart is :" + product);
        return descriptionOfItemLocator.getText().equalsIgnoreCase(product);
    }

}
