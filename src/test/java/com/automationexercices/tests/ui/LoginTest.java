package com.automationexercices.tests.ui;

import com.automationexercices.Pages.SignupLoginPage;
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
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
@Owner("Abdallah Mohammed")
@UITest

public class LoginTest extends BaseTest {
    String timestamp = TimeManager.GetCurrentTimeStamp();

    //tests
    @Test
    @Description("Verify user can login with valid credentials")
    public void validLoginTC(){
    new UserManagementAPI().createRegisterUserAccount
            (
                    testData.GetJsonData("name"),
                    testData.GetJsonData("email")+ timestamp +"@gmail.com",
                    testData.GetJsonData("password"),
                    testData.GetJsonData("firstName"),
                    testData.GetJsonData("lastName")
            )
            .verifyAccountCreatedSuccessfully();
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.GetJsonData("email")+ timestamp +"@gmail.com")
                .enterLoginPassword(testData.GetJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyUsernameVisible(testData.GetJsonData("name"));

        new UserManagementAPI().deleteUserAccount(
                testData.GetJsonData("email")+ timestamp +"@gmail.com",
                testData.GetJsonData("password"))
                .verifyAccountDeletedSuccessfully();
    }

    @Test
    @Description("Verify user cannot login with invalid Email ")
    public void inValidLoginUsingInValidEmailTC(){
        new UserManagementAPI().createRegisterUserAccount
                        (
                                testData.GetJsonData("name"),
                                testData.GetJsonData("email")+ timestamp +"@gmail.com",
                                testData.GetJsonData("password"),
                                testData.GetJsonData("firstName"),
                                testData.GetJsonData("lastName")
                        )
                .verifyAccountCreatedSuccessfully();
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.GetJsonData("email")+"@gmail.com")
                .enterLoginPassword(testData.GetJsonData("password"))
                .clickLoginButton()
                .verifyLoginErrorMsg(testData.GetJsonData("messages.error"));

        new UserManagementAPI().deleteUserAccount(
                        testData.GetJsonData("email")+ timestamp +"@gmail.com",
                        testData.GetJsonData("password"))
                .verifyAccountDeletedSuccessfully();
    }


    @Test
    @Description("VVerify user cannot login with invalid password")
    public void inValidLoginUsingInValidPasswordTC(){
        new UserManagementAPI().createRegisterUserAccount
                        (
                                testData.GetJsonData("name"),
                                testData.GetJsonData("email")+ timestamp +"@gmail.com",
                                testData.GetJsonData("password"),
                                testData.GetJsonData("firstName"),
                                testData.GetJsonData("lastName")
                        )
                .verifyAccountCreatedSuccessfully();

        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.GetJsonData("email")+ timestamp +"@gmail.com")
                .enterLoginPassword(testData.GetJsonData("password")+timestamp)
                .clickLoginButton()
                .verifyLoginErrorMsg(testData.GetJsonData("messages.error"));

        new UserManagementAPI().deleteUserAccount(
                        testData.GetJsonData("email")+ timestamp +"@gmail.com",
                        testData.GetJsonData("password"))
                .verifyAccountDeletedSuccessfully();
    }






    //configuration
    @BeforeClass
    protected void precondition(){
        testData = new JsonReader("login-data");
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
