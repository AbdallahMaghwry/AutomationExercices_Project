package com.automationexercices.Pages;

import com.automationexercices.Pages.component.NavigationBarComponent;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.Logs.LogsManager;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductDetailsPage {
    private GUIDriver driver;
    public NavigationBarComponent navigationBar;

    public ProductDetailsPage(GUIDriver driver) {
        this.driver = driver;
        navigationBar = new NavigationBarComponent(driver);
    }
    //vars
    private String productDetailsEndpoint = "/product-details/2";
    //locators
    private final By addToCartButton = By.cssSelector("[type='button']");
    private final By productName = By.cssSelector(".product-information>h2");
    private final By productPrice = By.cssSelector(".product-information>span>span");
    private final By name = By.id("name");
    private final By email = By.id("email");
    private final By reviewTextArea = By.id("review");
    private final By reviewButton = By.id("button-review");
    private final By reviewMsg = By.cssSelector("#review-section span");



    //Actions
    public ProductDetailsPage navigate() {
        driver.browser().navigateToURL(PropertyReader.GetProperty("baseUrlWeb") + productDetailsEndpoint);
        return this;
    }
    public ProductDetailsPage clickAddToCartButton() {
        driver.element().findElement(addToCartButton).click();
        return this;
    }
    @Step("write review on product")
    public ProductDetailsPage writeReview(String name, String email, String review) {
        driver.element().findElement(this.name).sendKeys(name);
        driver.element().findElement(this.email).sendKeys(email);
        driver.element().findElement(reviewTextArea).sendKeys(review);
        driver.element().findElement(reviewButton).click();
        return this;
    }

    //Validations

    @Step("Verify product name and price")
    public ProductDetailsPage verifyProductNameAndPrice(String expectedProductName, String expectedProductPrice) {
        String actualProductName = driver.element().findElement(productName).getText();
        String actualProductPrice = driver.element().findElement(productPrice).getText();
        LogsManager.Info("actual product name:", actualProductName, "actual price:", actualProductPrice);
        driver.validation().isEquals(actualProductName, expectedProductName, "Product Name is DisMatched");
        driver.validation().isEquals(actualProductPrice, expectedProductPrice, "Product Price is DisMatched");
        return this;
    }
    @Step("Verify review message")
    public ProductDetailsPage verifyReviewMsg(String expectedReviewMsg) {
        String actualReviewMsg = driver.element().findElement(reviewMsg).getText();
        LogsManager.Info("actual msg:", actualReviewMsg);
        driver.validation().isEquals(actualReviewMsg, expectedReviewMsg, "Review Message is DisMatched");
        return this;
    }



}
