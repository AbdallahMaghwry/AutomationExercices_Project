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
@Story("Products Details Management")
@Severity(SeverityLevel.NORMAL)
@Owner("Abdallah Mohammed")
@UITest

public class ProductsDetailsTest extends BaseTest {

//Tests
 @Description("Verify the product details Matched without login")
 @Test
     public void verifyProductDetailsPageTCWithoutLogin() {
        // Test implementation for verifying product details page
        new ProductPage(driver).navigate().clickOnViewProduct(testData.GetJsonData("product.name"))
                .verifyProductNameAndPrice(
                        testData.GetJsonData("product.name"),
                        testData.GetJsonData("product.price")
                );

    }



    @Description("Verify that a user can write a review for a product")
    @Test
    public void verifyReviewMessageTCWithoutLogin() {
        // Test implementation for writing a review
        new ProductPage(driver).navigate().clickOnViewProduct(testData.GetJsonData("product.name"))
                .writeReview(
                testData.GetJsonData("review.name"),
                testData.GetJsonData("review.email"),
                testData.GetJsonData("review.review")
        ).verifyReviewMsg(testData.GetJsonData("messages.review"));
    }



    //configuration
    @BeforeClass
    protected void precondition(){
        testData = new JsonReader("product-details-data");
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
