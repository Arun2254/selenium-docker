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
public class SearchPage extends BasePage {

    Helper helper;

    @FindBy(linkText = "Proceed to checkout")
    private WebElement proceedToCheckoutLink;

    @FindBy(linkText = "Faded Short Sleeve T-shirts")
    private WebElement productLink;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage clickOnProduct() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,480)","");
        productLink.click();
        return new ProductPage(driver);
    }

}
