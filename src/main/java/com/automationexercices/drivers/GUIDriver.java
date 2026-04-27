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

    private final  String browser = PropertyReader.GetProperty("browserType"); // هنا انا بجيب البراوز الي هاجري عليه من البروبرتيز عشان اقدر استخدمه في ال سويتش الي جاي بعد كدا

    private  ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>(); // دا الي بعرفو عشان استخدمو مكان كلمه driver

    public   GUIDriver () {
        Browsers BrowserType = Browsers.valueOf(browser.toUpperCase());//هنا انا بستخدم ال valueOf عشان اخد ال string الي جاي من البروبرتيز و احولها ل enum عشان اقدر استخدمها في السويتش
        LogsManager.Info("Browser Type: " + BrowserType);
        AbstractDriver abstractDriver = BrowserType.getDriverFactory();
        WebDriver driver = ThreadGuard.protect(abstractDriver.CreateDriver());//createDriver  هو هنا بناء علي البراوز الي بعتو وقت الرن تايم هو هاينده سواء كروم او ايدج وليه اتعرف بعد الدوت عشان فوق في السويتش انا نادي علي الي الابستركت درايفر
        driverThreadLocal.set(driver);

    }

    public  WebDriver Get()
     {
        return driverThreadLocal.get();//و عشان اشغل الدرايفر هاعملو return بالثريد لوكل عشان انا مستخدم ثريد ديلوقتي
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


