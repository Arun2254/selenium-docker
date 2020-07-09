package com.devexpress.pages;

import com.support.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by ayellapu on 7/3/20.
 */
public class FeedbackPage extends BasePage {

    @FindBy(id = "feedback")
    private WebElement feedbackLink;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "subject")
    private WebElement subjectField;

    @FindBy(id = "comment")
    private WebElement commentField;

    @FindBy(name = "submit")
    private WebElement submitBtn;

    @FindBy(name = "clear")
    private WebElement clearBtn;

    @FindBy(css = ".offset3")
    private WebElement successFeedback;


    public FeedbackPage(WebDriver driver) {
        super(driver);
        openURL("http://zero.webappsecurity.com/");
    }

    public void clickOnFeedbackLink() {
        feedbackLink.click();
    }

    public boolean fillFeedbackForm(String name, String emailId, String subject, String comments) {
        nameField.sendKeys(name);
        emailField.sendKeys(emailId);
        subjectField.sendKeys(subject);
        commentField.sendKeys(comments);
        submitBtn.click();
        System.out.println("Text is :" + successFeedback.getText());
        return (successFeedback.getText().contains("Feedback"));
    }


}
