package com.automationexercices.tests.ui;

import com.automationexercices.Pages.*;
import com.automationexercices.Pages.component.NavigationBarComponent;
import com.automationexercices.apis.UserManagementAPI;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.drivers.UITest;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@Epic("Automation Exercise")
@Feature("UI Payment Management")
@Story("Payment Management")
@Severity(SeverityLevel.NORMAL)
@Owner("Abdallah Mohammed")
@UITest

public class PaymentTest extends BaseTest {
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
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.GetJsonData("email")+ timestamp +"@gmail.com")
                .enterLoginPassword(testData.GetJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyUsernameVisible(testData.GetJsonData("name"));

    }

    @Test(dependsOnMethods = "CreateAccount")
    public void AddToCart() {
        // Assuming there is a method to add items to the cart
        new ProductPage(driver).navigate().clickOnAddToCart(testData.GetJsonData("product.name"))
                .validateItemAddedLabel(testData.GetJsonData("messages.cartAdded")).clickOnContinueShopping();
    }

    @Test(dependsOnMethods = {"CreateAccount", "AddToCart"})
    public void verifyProductDetailsOnCart() {
        // Assuming there is a method to navigate to the cart and proceed with checkout
        new CartPage(driver).navigateToCartPage().ValidateProductDetails(
                        testData.GetJsonData("product.name"),
                        testData.GetJsonData("product.price"),
                        testData.GetJsonData("product.quantity"),
                        testData.GetJsonData("product.total")
                )
                .ClickProceedToCheckoutButton();
    }

    @Test(dependsOnMethods = {"CreateAccount", "AddToCart", "verifyProductDetailsOnCart"})
    public void Checkout() {
        new CheckOutPage(driver).verifyDeliveryAddress(
                        testData.GetJsonData("titleMale"),
                        testData.GetJsonData("firstName"),
                        testData.GetJsonData("lastName"),
                        testData.GetJsonData("companyName"),
                        testData.GetJsonData("address1"),
                        testData.GetJsonData("address2"),
                        testData.GetJsonData("city"),
                        testData.GetJsonData("state"),
                        testData.GetJsonData("zipcode"),
                        testData.GetJsonData("country"),
                        testData.GetJsonData("mobileNumber")
                )
                .verifyBillingAddress(
                        testData.GetJsonData("titleMale"),
                        testData.GetJsonData("firstName"),
                        testData.GetJsonData("lastName"),
                        testData.GetJsonData("companyName"),
                        testData.GetJsonData("address1"),
                        testData.GetJsonData("address2"),
                        testData.GetJsonData("city"),
                        testData.GetJsonData("state"),
                        testData.GetJsonData("zipcode"),
                        testData.GetJsonData("country"),
                        testData.GetJsonData("mobileNumber")
                )
                .clickOnPlaceOrder();

    }

    @Test(dependsOnMethods = {"Checkout","CreateAccount", "AddToCart", "verifyProductDetailsOnCart"})
    public void Payment() {
        new PaymentPage(driver).navigate().fillPaymentDetails
                (
                        testData.GetJsonData("payment.cardName"),
                        testData.GetJsonData("payment.cardNumber"),
                        testData.GetJsonData("payment.cvc"),
                        testData.GetJsonData("payment.exMonth"),
                        testData.GetJsonData("payment.exYear")
                )
                .validatePaymentSuccessMessage(testData.GetJsonData("messages.paymentSuccess"));

    }
    @Test(dependsOnMethods = {"Payment","Checkout","verifyProductDetailsOnCart", "AddToCart","CreateAccount"})
    public void deleteAccount(){
        // Assuming there is a method to delete the account
        new UserManagementAPI().deleteUserAccount(
                        testData.GetJsonData("email")+ timestamp +"@gmail.com",
                        testData.GetJsonData("password"))
                .verifyAccountDeletedSuccessfully();
    }








    //configuration
    @BeforeClass
    protected void setUp(){
        testData = new JsonReader("payment-data");
        driver = new GUIDriver();
        new NavigationBarComponent(driver).Navigate();
        driver.browser().closeExtensionTab();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }

}
