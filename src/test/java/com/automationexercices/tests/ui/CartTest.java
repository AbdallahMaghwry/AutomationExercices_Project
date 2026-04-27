package com.automationexercices.tests.ui;

import com.automationexercices.Pages.CartPage;
import com.automationexercices.Pages.ProductPage;
import com.automationexercices.Pages.component.NavigationBarComponent;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.drivers.UITest;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Automation Exercise")
@Feature("UI Cart Management")
@Story("Cart Management")
@Severity(SeverityLevel.NORMAL)
@Owner("Abdallah Mohammed")
@UITest
public class CartTest extends BaseTest {

@Description("Verify Product Details On Cart Is Matched Without Login")
@Test
public void verifyProductDetailsOnCartWithoutLogin() {
    // Test implementation here
    new ProductPage(driver).navigate().clickOnAddToCart(testData.GetJsonData("product1.name"))
            .validateItemAddedLabel(testData.GetJsonData("messages.cartAdded"))
            .clickOnContinueShopping();
    new CartPage(driver).navigateToCartPage().ValidateProductDetails(
            testData.GetJsonData("product1.name"),
            testData.GetJsonData("product1.price"),
            testData.GetJsonData("product1.quantity"),
            testData.GetJsonData("product1.total")
    );

}










    //configuration
    @BeforeClass
    protected void precondition(){
        testData = new JsonReader("cart-data");
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
