package com.automationexercices.Pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PaymentPage {
private final GUIDriver driver;
public PaymentPage(GUIDriver driver) {
        this.driver = driver;
    }

    //vars
    String endpoint = "/payment";
    //locatorsBy
    private  final By CARD_NAME_FIELD = By.cssSelector("[name='name_on_card']");
    private  final By CARD_NUMBER_FIELD = By.cssSelector("[name='card_number']");
    private  final By EXPIRATION_YEAR_FIELD =By.cssSelector("[name='expiry_year']") ;
    private  final By EXPIRATION_MONTH_FIELD = By.cssSelector("[name='expiry_month']")   ;
    private  final By CVV_FIELD = By.cssSelector("[name='cvc']");
    private  final By PAY_NOW_BUTTON =By.id("submit");
    private final By PAYMENT_SUCCESS_MESSAGE = By.cssSelector("h2>b");
    private final By downloadInvoiceButton = By.xpath("//a[.='Download Invoice']");

    //Actions
    @Step("Navigate to Payment Page")
    public PaymentPage navigate() {
        driver.browser().navigateToURL(PropertyReader.GetProperty("baseUrlWeb")+endpoint);
        return this;
    }

    @Step("Fill Payment Details")
        public PaymentPage fillPaymentDetails(String cardName, String cardNumber, String expirationYear, String expirationMonth, String cvv) {
        driver.element().type(CARD_NAME_FIELD, cardName).
        type(CARD_NUMBER_FIELD, cardNumber).
        type(EXPIRATION_YEAR_FIELD, expirationYear).
        type(EXPIRATION_MONTH_FIELD, expirationMonth).
        type(CVV_FIELD, cvv).
        click(PAY_NOW_BUTTON);
        return this;
    }
    @Step("Click on download invoice button")
    public PaymentPage clickOnDownloadInvoiceButton() {
        driver.element().click(downloadInvoiceButton);
        return this;
    }

    //Validation
    @Step("Validate Payment Success Message")
    public PaymentPage validatePaymentSuccessMessage(String expectedMessage) {
        String actualMessage = driver.element().gettextFromElement(PAYMENT_SUCCESS_MESSAGE);
        driver.verification().isEquals(actualMessage, expectedMessage, "Payment success message is not visable");
        return this;
    }


    public PaymentPage validateInvoiceDownloaded(String invoicename) {
        driver.verification().assertFileExists(invoicename,"File is not existed");
        return this;
    }
}
