package com.automationexercices.tests.ui;

import com.automationexercices.Pages.SignupLoginPage;
import com.automationexercices.Pages.SignupPage;
import com.automationexercices.Pages.component.NavigationBarComponent;
import com.automationexercices.apis.UserManagementAPI;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.drivers.UITest;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@Epic("Automation Exercise")
@Feature("UI User Management")
@Story("User Registration")
@Severity(SeverityLevel.CRITICAL)
@Owner("Abdallah Mohammed")
@UITest

public class RegisterTest extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();
    @Description("Verify user can sign up with valid data")
    @Test
    public void validSignUpTC() {
        new SignupLoginPage(driver).navigate().enterSignupName(testData.GetJsonData("name"))
                .enterSignupEmail(testData.GetJsonData("email")+ timestamp +"@gmail.com")
                .clickSignupButton();

                new SignupPage(driver)
                .fillRegisterationForm(testData.GetJsonData("titleMale"),
                        testData.GetJsonData("password"),
                        testData.GetJsonData("day"),
                        testData.GetJsonData("month"),
                        testData.GetJsonData("year"),
                        testData.GetJsonData("firstName"),
                        testData.GetJsonData("lastName"),
                        testData.GetJsonData("companyName"),
                        testData.GetJsonData("address1"),
                        testData.GetJsonData("address2"),
                        testData.GetJsonData("country"),
                        testData.GetJsonData("state"),
                        testData.GetJsonData("city"),
                        testData.GetJsonData("zipcode"),
                        testData.GetJsonData("mobileNumber"
                ))
                .clickCreateAccountButton()
                .verifyAccountCreated()
                .clickContinueButton();

        new UserManagementAPI().deleteUserAccount(
                        testData.GetJsonData("email") + timestamp + "@gmail.com",
                        testData.GetJsonData("password"))
                .verifyAccountDeletedSuccessfully();

    }
@Test
public void invalidRegisterTC(){
new UserManagementAPI().createRegisterUserAccount(
        testData.GetJsonData("name"),
    testData.GetJsonData("email")+ timestamp +"@gmail.com",
        testData.GetJsonData("password"),
        testData.GetJsonData("titleMale"),
        testData.GetJsonData("day"),
        testData.GetJsonData("month"),
        testData.GetJsonData("year"),
        testData.GetJsonData("firstName"),
        testData.GetJsonData("lastName"),
        testData.GetJsonData("companyName"),
        testData.GetJsonData("address1"),
        testData.GetJsonData("address2"),
        testData.GetJsonData("country"),
        testData.GetJsonData("state"),
        testData.GetJsonData("city"),
        testData.GetJsonData("zipcode"),
        testData.GetJsonData("mobileNumber"
        ))
                .verifyAccountCreatedSuccessfully();
        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.GetJsonData("name"))
                .enterSignupEmail(testData.GetJsonData("email")+ timestamp +"@gmail.com")
                .clickSignupButton()
                .verifySignUpErrorMsg(testData.GetJsonData("messages.error"));

    new UserManagementAPI().deleteUserAccount(
                    testData.GetJsonData("email") + timestamp + "@gmail.com",
                    testData.GetJsonData("password"))
            .verifyAccountDeletedSuccessfully();
}



//configuration
@BeforeClass
protected void precondition(){
    testData = new JsonReader("register-data");
}

    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();
        new NavigationBarComponent(driver).Navigate();
        driver.browser().closeExtensionTab();

    }

    @AfterMethod
    public void tearDown() {
     driver.quitDriver();
    }



}
