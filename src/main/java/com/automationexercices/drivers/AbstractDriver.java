package com.automationexercices.drivers;

import com.automationexercices.utils.dataReader.PropertyReader;
import org.openqa.selenium.WebDriver;

import java.io.File;

public abstract class AbstractDriver {
    protected final String remoteHost = PropertyReader.GetProperty("remoteHost");
    protected final String remotePort = PropertyReader.GetProperty("remotePort");
    //الي هاextend ال ابستركت درايفر  هو الي هايقول الميثود اي تحت دي شغاله ازاي
    protected File  haramBlurExtension= new File("src/main/resources/extensions/HaramBlur.crx");
    public abstract WebDriver CreateDriver();//طلما دي ابستركت يبقي ملهاش  implementation
}
