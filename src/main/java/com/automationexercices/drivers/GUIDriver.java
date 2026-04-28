package com.automationexercices.drivers;

import com.automationexercices.utils.Actions.AlertActions;
import com.automationexercices.utils.Actions.BrowserActions;
import com.automationexercices.utils.Actions.ElementActions;
import com.automationexercices.utils.Actions.FrameActions;
import com.automationexercices.utils.Logs.LogsManager;
import com.automationexercices.validations.Validation;
import com.automationexercices.validations.Verification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import com.automationexercices.utils.dataReader.PropertyReader;

public class GUIDriver {

    private final  String browser = PropertyReader.GetProperty("browserType");

    private  ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public   GUIDriver () {
        Browsers BrowserType = Browsers.valueOf(browser.toUpperCase());
        LogsManager.Info("Browser Type: " + BrowserType);
        AbstractDriver abstractDriver = BrowserType.getDriverFactory();
        WebDriver driver = ThreadGuard.protect(abstractDriver.CreateDriver());
        driverThreadLocal.set(driver);

    }

    public  WebDriver Get()
     {
        return driverThreadLocal.get();
    }
    public ElementActions element(){
        return new ElementActions(Get());
    }
    public BrowserActions browser(){
        return new BrowserActions(Get());
    }
    public AlertActions alert(){
        return new AlertActions(Get());
    }
    public FrameActions frame(){
        return new FrameActions(Get());
    }
    public Validation validation(){
        return new Validation(Get());
    }
    public Verification verification(){
        return new Verification(Get());
    }

    public void quitDriver()
    {
        driverThreadLocal.get().quit();
    }
}


