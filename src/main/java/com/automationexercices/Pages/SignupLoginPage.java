package com.automationexercices.Pages;

import com.automationexercices.Pages.component.NavigationBarComponent;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupLoginPage {
    public NavigationBarComponent navigationBar;
    private  GUIDriver driver;
    private final String signupLoginEndpoint = "/login";

    public SignupLoginPage(GUIDriver driver) {
        this.driver = driver;
        this.navigationBar = new NavigationBarComponent(driver);
    }

    //Locators
    By signupName = By.cssSelector("[name='name']");
    By signupEmail = By.cssSelector("[data-qa=\"signup-email\"]");
    By signupButton = By.cssSelector("[data-qa=\"signup-button\"]");
    By loginEmail = By.cssSelector("[data-qa=\"login-email\"]");
    By loginPassword = By.cssSelector("[data-qa=\"login-password\"]");
    By loginButton = By.cssSelector("[data-qa=\"login-button\"]");
    By loginToYourAccountLabel = By.cssSelector(".login-form>h2");
    By loginError = By.cssSelector("[action='/login'] > p");
    By signupError = By.cssSelector("[action='/signup'] > p");


    //Actions
    @Step("Navigate to SignUp/Login Page")
    public SignupLoginPage navigate() {
            driver.browser().navigateToURL("https://automationexercise.com/login");
            return this;
        }
    @Step("Enter name {name} in signup name field")
    public  SignupLoginPage enterSignupName(String name) {
        driver.element().type(signupName, name);
        return this;
    }
    @Step("Enter email {email} in signup field")
    public SignupLoginPage enterSignupEmail(String email) {
        driver.element().type(signupEmail, email);
        return this;
    }
    @Step("Click signup button")
    public SignupLoginPage clickSignupButton() {
        driver.element().click(signupButton);
        return new SignupLoginPage(driver);
    }
    @Step("Enter name {email} in login field")
    public SignupLoginPage enterLoginEmail(String email) {
        driver.element().type(loginEmail, email);
        return this;
    }
    @Step("Enter password {password} in login field")
    public SignupLoginPage enterLoginPassword(String password) {
        driver.element().type(loginPassword, password);
        return this;
    }
    @Step("Click login button")
    public SignupLoginPage clickLoginButton() {
        driver.element().click(loginButton);
        return this;
    }



    //Validations
    @Step("Verify Login Error Message {expectedErrorMsg} is displayed")
    public SignupLoginPage verifyLoginErrorMsg(String expectedErrorMsg) {
        String actualErrorMsg = driver.element().gettextFromElement(loginError);
        driver.verification().isEquals(actualErrorMsg, expectedErrorMsg, "Login error message mismatch!");
        return this;
    }
    @Step("Verify SignUp Error Massage {expectedErrorMsg} is displayed")
    public SignupLoginPage verifySignUpErrorMsg(String expectedErrorMsg) {
        String actualErrorMsg = driver.element().gettextFromElement(signupError);
        driver.verification().isEquals(actualErrorMsg, expectedErrorMsg, "Signup error message mismatch!");
         return this;
    }
    @Step("Verify Login to your account Label is displayed")
    public  SignupLoginPage verifyLoginLabelVisible() {
        driver.verification().isElementVisible(loginToYourAccountLabel);
        return this;
    }

}
