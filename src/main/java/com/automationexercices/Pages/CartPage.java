package com.automationexercices.Pages;

import com.automationexercices.Pages.component.NavigationBarComponent;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    private GUIDriver driver;
    private NavigationBarComponent navigationBarComponent;
    public CartPage(GUIDriver driver) {
        this.driver = driver;
    }

    //vars
    private String cartEndpoint = "/view_cart";
    //Locator
    private final By ProceedToCheckoutButton = By.xpath("//*[.='Proceed To Checkout']");

    //dynamic locators
    private By productName(String productName) {
        return By.xpath("(//h4  /a[.='" + productName + "'])[1]");
    }

    private By productPrice(String productName) {
        return By.xpath("(//h4  /a[.='" + productName + "'] //following::td[@class='cart_price'] /p)[1]");
    }

    private By productQuantity(String productName) {
        return By.xpath("(//h4  /a[.='" + productName + "'] //following::td[@class='cart_quantity'] /button)[1]");
    }

    private By productTotal(String productName) {
        return By.xpath("(//h4  /a[.='"+productName+"'] //following::td[@class='cart_total'] /p)[1]");
    }

    private By removeProductDL(String productName) {
        return By.xpath("(//h4  /a[.='"+productName+"'] //following::td[@class='cart_delete'] /a)[1]");
    }


    //Actions
    @Step("Navigate to Cart Page")
    public CartPage navigateToCartPage() {
        driver.browser().navigateToURL(PropertyReader.GetProperty("baseUrlWeb") + cartEndpoint);
        return this;
    }
    @Step("Click Proceed to Checkout Button")
    public CheckOutPage ClickProceedToCheckoutButton() {
        driver.element().click(ProceedToCheckoutButton);
        return new CheckOutPage(driver);
    }

    @Step("Delete Product From Cart")
    public CartPage deleteProductFromCart(String productName) {
        driver.element().click(removeProductDL(productName));
        return this;
    }


    //Validations
    @Step("Validate Product Details")
    public CartPage ValidateProductDetails(String ExpectedProductName,String ExpectedProductPrice ,String ExpectedProductQuantity, String ExpectedProductTotal) {

        String ActualProductName = driver.element().gettextFromElement(productName(ExpectedProductName));
        String ActualProductPrice = driver.element().gettextFromElement(productPrice(ExpectedProductName));
        String ActualProductQuantity = driver.element().gettextFromElement(productQuantity(ExpectedProductName));
        String ActualProductTotal = driver.element().gettextFromElement(productTotal(ExpectedProductName));


        driver.validation().isEquals(ActualProductName,ExpectedProductName,"Product name does not match");
        driver.validation().isEquals(ActualProductPrice,ExpectedProductPrice,"Product price does not match");
        driver.validation().isEquals(ActualProductQuantity,ExpectedProductQuantity,"Product quantity does not match");
        driver.validation().isEquals(ActualProductTotal,ExpectedProductTotal,"Product total does not match");

        return this;
    }










}
