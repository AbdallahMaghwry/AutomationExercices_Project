package com.automationexercices.drivers;

import com.automationexercices.utils.Logs.LogsManager;
import com.automationexercices.utils.dataReader.PropertyReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;


public class ChromeFactory extends AbstractDriver{

    private ChromeOptions GetOptions()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--Start-Maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (PropertyReader.GetProperty("extensions").equalsIgnoreCase("enabled"))
            options.addExtensions(haramBlurExtension);
        switch (PropertyReader.GetProperty("executionType"))
        {
            case "LocalHeadless"->options.addArguments("--headless=new");
            case "Remote"->
            {
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-extensions");
                options.addArguments("--headless=new");

            }
        }
        return options;
    }

//هنا اول ما عملت اكستيند للابستركشن درايفر جابلي هنا اني استخدم الفانكشن الي اسمها كريت درايفر الي هي اصلا موجوده هناك في الابستركشن و منها عملت return اني افتح براوز بالاوبشينز الي انا حاططتها و كمان عامل ريتتن كدا عشانpublic WebDriver
    @Override
    public WebDriver CreateDriver() {

        if (PropertyReader.GetProperty("executionType").equalsIgnoreCase("LocalHeadless") ||
                PropertyReader.GetProperty("executionType").equalsIgnoreCase("Local"))
        {
            return new ChromeDriver(GetOptions());
        }
        else if (PropertyReader.GetProperty("executionType").equalsIgnoreCase("remote"))
        {

            try {
                return new RemoteWebDriver(new URI("http://"+ remoteHost + ":" + remotePort + "/wd/hub").toURL(), GetOptions());
            }
            catch (Exception e)
            {
                LogsManager.Error("Failed to create remote WebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to create remote WebDriver: " + e.getMessage());
            }


        }
        else {
            LogsManager.Error("Invalid execution type: " + PropertyReader.GetProperty("executionType"));
            throw new IllegalArgumentException("Invalid execution type: " + PropertyReader.GetProperty("executionType"));
        }


    }
}
