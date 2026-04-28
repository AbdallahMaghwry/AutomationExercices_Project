package com.automationexercices.tests.api;

import com.automationexercices.Pages.component.NavigationBarComponent;
import com.automationexercices.apis.UserManagementAPI;
import com.automationexercices.drivers.APITest;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.dataReader.JsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@APITest
public class RegisterTestAPI extends BaseTest {
    String timestamp = TimeManager.GetCurrentTimeStamp();

    @Test
    public void CreateAccount(){
        new UserManagementAPI().createRegisterUserAccount
                        (
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
                                testData.GetJsonData("mobileNumber")
                        )
                .verifyAccountCreatedSuccessfully();
    }

    //configuration
    @BeforeClass
    protected void precondition(){
        testData = new JsonReader("register-data");
    }

}

