package com.automationexercices.tests.ui;

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
@Feature("UI Products Management")
@Story("Products Management")
@Severity(SeverityLevel.NORMAL)
@Owner("Abdallah Mohammed")
@UITest

public class ProductTest extends BaseTest {

//Tests
    @Test
    @Description("Search for a product and validate its details")
    public void searchForProductWithoutLogin() {
        new ProductPage(driver).navigate()
                .searchProduct(testData.GetJsonData("searchedProduct.name"))
                .validateProductDetails
                (
                        testData.GetJsonData("searchedProduct.name"),
                        testData.GetJsonData("searchedProduct.price")

                );
    }

    @Test
    @Description("Add a product to the cart without logging in")
    public void addProductToCartWithoutLogin(){
        new ProductPage(driver).navigate()
                .clickOnAddToCart(testData.GetJsonData("product1.name"))
                .validateItemAddedLabel(
                        testData.GetJsonData("messages.cartAdded")
                )
        .clickOnContinueShopping();
    }





    //configuration
    @BeforeClass
    protected void precondition(){
        testData = new JsonReader("products-data");
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
