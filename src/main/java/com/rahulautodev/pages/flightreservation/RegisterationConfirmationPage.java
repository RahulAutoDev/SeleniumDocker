package com.rahulautodev.pages.flightreservation;

import com.rahulautodev.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterationConfirmationPage extends AbstractPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightsSearchButton;

    public RegisterationConfirmationPage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightsSearchButton));
        return this.goToFlightsSearchButton.isDisplayed();
    }

    public void goToFlightsSearch()
    {
        this.goToFlightsSearchButton.click();
    }
}



