package com.automationpractice.tests;

import com.automationpractice.pages.*;
import com.support.BaseForTests;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by ayellapu on 7/2/20.
 */
public class EndToEndTestCases extends BaseForTests {

    private LoginPage loginPage;
    private HomePage homePage;
    private SearchPage searchPage;
    private AccountPage accountPage;
    private ContainerSection containerSection;
    private ProductPage productPage;
    private CartPage cartPage;
    private AddressPage addressPage;
    private ShippingPage shippingPage;
    private PaymentPage paymentPage;
    private OrderConfirmationPage orderConfirmationPage;

    private String productName = "Faded Short Sleeve T-shirts";
    private String email ;
    private String password ;
    private String expectedPrice ;

    @Test
    @Parameters({"email", "password"})
    public void LoginWithValidCredentials(String email, String password) {

        this.email = email;
        this.password = password;

        homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isInitialized());

        loginPage = homePage.onClickLogin();
        Assert.assertTrue(loginPage.isInitialized());

        accountPage = loginPage.onClickSignIn(this.email, this.password);
        Assert.assertTrue(accountPage.isInitialized());
        Assert.assertEquals(accountPage.getUserName(),"Panday tester","UserName is not matching");

    }

    @Test(dependsOnMethods = "LoginWithValidCredentials")
    public void testAddProductToCart() {
        containerSection = new ContainerSection(driver);
        containerSection.searchProduct(productName);
        searchPage = containerSection.onClickSearchButton();

        productPage = searchPage.clickOnProduct();
        productPage.addProductToCart();
        cartPage = productPage.clickOnProceedToCheckout();
        Assert.assertTrue(cartPage.checkIfValidProductAddedToCart(productName));
    }

    @Test(dependsOnMethods = "testAddProductToCart")
    public void testForAddress() {
        addressPage = cartPage.clickOnProceedToAddressPage();
        Assert.assertTrue(addressPage.isAddressPageDisplayed());
    }

    @Test(dependsOnMethods = "testForAddress")
    public void testForShipping() {
        shippingPage = addressPage.clickOnProceedToShippingPage();
        Assert.assertTrue(shippingPage.isShippingPageDisplayed());
        shippingPage.clickOnCheckBox();
    }

    @Test(dependsOnMethods = "testForShipping")
    public void testForPayment() {
        paymentPage = shippingPage.clickOnProceedToPayment();
//        Assert.assertTrue(paymentPage.isPaymentPageDisplayed());

        paymentPage.clickOnPayByBankWire();
    }

    @Test(dependsOnMethods = "testForPayment")
    @Parameters({"expectedPrice"})
    public void testForOrderConfirmation(String expectedPrice) {
        this.expectedPrice = expectedPrice;
        orderConfirmationPage = paymentPage.clickOnConfirmOrder();
        Assert.assertTrue(orderConfirmationPage.isOrderConfirmationPageDisplayed());
        Assert.assertEquals(orderConfirmationPage.getPriceOnOrderConfirmation(),this.expectedPrice,"Price is not matching");

        homePage = accountPage.clickOnLogout();
        Assert.assertTrue(homePage.isInitialized());
    }
}
