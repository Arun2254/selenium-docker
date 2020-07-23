package com.automationpractice.tests;

import com.automationpractice.pages.AccountPage;
import com.automationpractice.pages.HomePage;
import com.automationpractice.pages.LoginPage;
import com.aventstack.extentreports.Status;
import com.support.BaseForTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by ayellapu on 7/2/20.
 */
public class LoginTest extends BaseForTests {

    private LoginPage loginPage;
    private HomePage homePage;
    private AccountPage accountPage;

    private String email;
    private String password ;

    @Test
    public void testForLoginWithValidCredentials() throws IOException {

        this.email = getProperty("email");
        this.password = getProperty("password");

        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());

        loginPage = homePage.onClickLogin();
        Assert.assertTrue(loginPage.isInitialized());
        accountPage = loginPage.onClickSignIn(this.email, this.password);
        Assert.assertTrue(accountPage.isInitialized());
        test.log(Status.INFO, "User Logged In Successfully");
        test.log(Status.INFO,"Actual User Name is " + accountPage.getUserName() );
        Assert.assertEquals(accountPage.getUserName(),"Panday tester","UserName is not matching");

        homePage = accountPage.clickOnLogout();
        Assert.assertTrue(homePage.isInitialized());
        test.log(Status.INFO, "User Logged Out Successfully");

    }
}
