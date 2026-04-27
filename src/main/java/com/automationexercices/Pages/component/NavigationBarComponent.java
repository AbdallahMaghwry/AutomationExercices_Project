package com.automationexercices.Pages.component;

import com.automationexercices.Pages.*;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.Logs.LogsManager;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarComponent {
    private final GUIDriver driver;

    public NavigationBarComponent(GUIDriver driver) {
        this.driver = driver;
    }


    //Locators
    private final By homeButton = By.xpath("//a[.=' Home']");
    private final By productsButton = By.cssSelector("[href='/products']");
    private final By cartButton = By.cssSelector("a[href='/view_cart']>i[class='fa fa-shopping-cart']");
    private final By signupLoginButton = By.cssSelector("a[href=\"/login\"]");
    private final By testCasesButton = By.cssSelector("a[href=\"/test_cases\"]>i");
    private final By apiTestingButton = By.cssSelector("a[href=\"/api_list\"]>i");
    private final By contactUsButton = By.cssSelector("a[href=\"/contact_us\"]>i");
    private final By videoTutorialsButton = By.xpath("//a[.=' Video Tutorials']");
    private final By deleteAccountButton = By.cssSelector("a[href='/delete_account']");
    private final By logoutButton = By.cssSelector("a[href='/logout']");
    private final By userLabel = By.tagName("b");

    //Actions
    @Step("Navigate to Base Url")
    public NavigationBarComponent Navigate(){
        driver.browser().navigateToURL(PropertyReader.GetProperty("baseUrlWeb"));
        return this;
    }


    @Step("Home Button is clicking")
    public NavigationBarComponent homeButtonClick() {
        driver.element().click(homeButton);
        return this;
    }
    @Step(" Products Button is clicking")
    public ProductPage productsButtonClick() {
        driver.element().click(productsButton);
        return new ProductPage(driver);
    }
    @Step(" Cart Button is clicking")
    public CartPage cartButtonClick() {
        driver.element().click(cartButton);
        return new CartPage(driver);
    }
    @Step(" Signup/Login Button is clicking")
    public SignupLoginPage signupLoginButtonClick() {
        driver.element().click(signupLoginButton);
        return new SignupLoginPage(driver);
    }
    @Step(" Test Cases Button is clicking")
    public TestCasesPage testCasesButtonClick() {
        driver.element().click(testCasesButton);
        return new TestCasesPage(driver);
    }
    @Step(" API Testing Button is clicking")
    public APITestingPage apiTestingButtonClick() {
        driver.element().click(apiTestingButton);
        return new APITestingPage(driver);
    }
    @Step(" Contact Us Button is clicking")
    public ContactUsPage contactUsButtonClick() {
        driver.element().click(contactUsButton);
        return new ContactUsPage(driver);
    }
    @Step(" Video Tutorials Button is clicking")
    public VideoTutorialsPage videoTutorialsButtonClick() {
        driver.element().click(videoTutorialsButton);
        return new VideoTutorialsPage(driver);
    }
        @Step(" Delete Account Button is clicking")
    public DeleteAccountPage deleteAccountButtonClick() {
        driver.element().click(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }
    @Step(" Logout Button is clicking")
    public LogoutPage logoutButtonClick() {
        driver.element().click(logoutButton);
        return new LogoutPage(driver);
    }



    //Validations
    @Step("verify that username label is matched")
    public NavigationBarComponent   verifyUsernameVisible(String ExpectedName) {
        String actualUsername = driver.element().gettextFromElement(userLabel);
        LogsManager.Info("verifying User Label:  " + actualUsername);
        driver.verification().isEquals(actualUsername,ExpectedName,"Username is not matched Actual: " + actualUsername + " Expected: " + ExpectedName);
        return this;
    }



}
