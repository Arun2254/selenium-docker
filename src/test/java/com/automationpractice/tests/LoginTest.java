package com.automationpractice.tests;

import com.automationpractice.pages.AccountPage;
import com.automationpractice.pages.HomePage;
import com.automationpractice.pages.LoginPage;
import com.support.BaseForTests;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
    @Parameters({"email", "password"})
    public void testForLoginWithValidCredentials(@Optional("arunpanday777@gmail.com") String email, @Optional("Test@123") String password) {
        this.email = email;
        this.password = password;

        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isInitialized());

        loginPage = homePage.onClickLogin();
        Assert.assertTrue(loginPage.isInitialized());

        accountPage = loginPage.onClickSignIn(this.email, this.password);
        Assert.assertTrue(accountPage.isInitialized());
        Assert.assertEquals(accountPage.getUserName(),"Panday tester","UserName is not matching");

        homePage = accountPage.clickOnLogout();
        Assert.assertTrue(homePage.isInitialized());
        System.out.println("Test Completed");
    }
}
