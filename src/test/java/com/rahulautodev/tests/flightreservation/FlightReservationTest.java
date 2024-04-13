package com.rahulautodev.tests.flightreservation;

import com.rahulautodev.pages.flightreservation.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest {

    private WebDriver driver;
    private String expectedPrice;
    private String noOfPassengers;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setDriver(String noOfPassengers, String expectedPrice)
    {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    @Test
    public void userRegistrationTest()
    {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html#");
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails("selenium", "Docker");
        registrationPage.enterUserCredentials("selenium@docker.com", "docker");
        registrationPage.enterAddress("123 non main", "atlanta", "30021");
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest()
    {
        RegisterationConfirmationPage registerationConfirmationPage = new RegisterationConfirmationPage(driver);
        Assert.assertTrue(registerationConfirmationPage.isAt());
        registerationConfirmationPage.goToFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest()
    {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(noOfPassengers);
        flightSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest()
    {
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlight();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTest()
    {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), expectedPrice);
    }

    @AfterTest
    public void quit()
    {
        this.driver.quit();
    }









}
