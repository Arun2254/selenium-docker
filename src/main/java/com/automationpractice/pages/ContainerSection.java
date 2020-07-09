package com.automationpractice.pages;

import com.support.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by ayellapu on 7/2/20.
 */
public class ContainerSection extends BasePage {

    @FindBy(id = "search_query_top")
    private WebElement searchTxt;

    @FindBy(name = "submit_search")
    private WebElement submitSearch;

    public ContainerSection(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String product) {
        searchTxt.sendKeys(product);
    }

    public SearchPage onClickSearchButton() {
        submitSearch.click();
        return new SearchPage(driver);
    }


    //Faded Short Sleeve T-shirts

}
