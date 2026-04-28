package com.automationexercices.Pages;

import com.automationexercices.Pages.component.NavigationBarComponent;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.Logs.LogsManager;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductPage {
    private final GUIDriver driver;
    public NavigationBarComponent navigationBar;
    //variables
    private String productPage = "/products";

    public ProductPage(GUIDriver driver) {
        this.driver = driver;
        this.navigationBar = new NavigationBarComponent(driver);
    }
    //locators
    private final By searchField = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By itemAddedLabel = By.cssSelector(".modal-body > p");
    private final By viewCartButton = By.cssSelector("p > [href=\"/view_cart\"]");
    private final By continueShoppingButton = By.cssSelector(".modal-footer >button");


    //dynamic locators
    private By productName(String productName) {
        return By.xpath("//div[@class='features_items']//div[@class='overlay-content'] /p[.='" + productName + "']");
    }

    private By productPrice(String productName) {
        return By.xpath("//div[@class='features_items']//div[@class='overlay-content'] /p[.='" + productName + "'] //preceding-sibling::h2");
    }

    private By hoverOnProduct(String productName) {
        return By.xpath("//div[@class='features_items']//div[@class='productinfo text-center'] /p[.='" + productName + "']");
    }

    private By addToCartButton(String productName) {
        return By.xpath("//div[@class='features_items']//div[@class='overlay-content'] /p[.='" + productName + "'] //following-sibling::a");
    }

    private By viewProduct(String productName) {
        return By.xpath("//p[.='" + productName + "'] //following::div[@class='choose'][1]");
    }

    //Actions
    @Step("Navigate to Products Page")
    public ProductPage navigate(){
        driver.browser().navigateToURL(PropertyReader.GetProperty("baseUrlWeb")+productPage);
        return this;
    }
    @Step("Search for product: {NameOfProduct}")
    public ProductPage searchProduct(String NameOfProduct){
        driver.element().type(searchField,NameOfProduct).click(searchButton);
        return this ;
    }
    @Step("Click on Add to Cart for product: {NameOfProduct}")
    public ProductPage clickOnAddToCart(String NameOfProduct){
       driver.element().hoverElement(hoverOnProduct(NameOfProduct)).click(addToCartButton(NameOfProduct));
       return this;
    }
    @Step("Click on View Product for product: {productName}")
    public ProductDetailsPage clickOnViewProduct(String productName) {
        driver.element().click(viewProduct(productName));
        return new ProductDetailsPage(driver);
    }
    @Step("Click on View Cart")
    public CartPage clickOnViewCart() {
        driver.element().click(viewCartButton);
        return new CartPage(driver);
    }

    @Step("Click on Continue Shopping")
    public ProductPage clickOnContinueShopping() {
        driver.element().click(continueShoppingButton);
        return this;
    }

    //Validation
    @Step("Validate product details for {productName} with price {productPrice}")
    public ProductPage validateProductDetails(String productName, String productPrice)//verify after search
    {
        String actualProductName = driver.element().hoverElement(productName(productName)).gettextFromElement(productName(productName));
        String actualProductPrice = driver.element().hoverElement(productName(productName)).gettextFromElement(this.productPrice(productName));
        LogsManager.Info("Validating product details for: " + actualProductName, " with price: " + actualProductPrice);
        driver.validation().isEquals(actualProductName, productName, "Product name does not match");
        driver.validation().isEquals(actualProductPrice, productPrice, "Product price does not match");
        return this;
    }
    @Step("Validate item added label contains: {expectedText}")
    public ProductPage validateItemAddedLabel(String expectedText) {
        String actualTxt = driver.element().gettextFromElement(itemAddedLabel);
        LogsManager.Info("Validating item added label: " + actualTxt);
        driver.verification().isEquals(actualTxt, expectedText, "Item added label does not match expected text");
        return this;
    }


}
