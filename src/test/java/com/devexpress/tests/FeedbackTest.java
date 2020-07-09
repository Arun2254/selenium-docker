package com.devexpress.tests;

import com.devexpress.pages.FeedbackPage;
import com.support.BaseForTests;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by ayellapu on 7/3/20.
 */
public class FeedbackTest extends BaseForTests {

    private String name;
    private String emailId;
    private String subject;
    private String comments;

    FeedbackPage feedbackPage;

    @Test
    @Parameters({"name","emailId","subject","comments"})
    public void testForFeedback(String name, String emailId, String subject, String comments) {
        feedbackPage = new FeedbackPage(driver);
        this.name = name;
        this.emailId = emailId;
        this.subject = subject;
        this.comments = comments;

        feedbackPage.clickOnFeedbackLink();
        boolean  actualFeedback = feedbackPage.fillFeedbackForm(this.name, this.emailId, this.subject, this.comments);
        Assert.assertTrue(actualFeedback);
    }
}
