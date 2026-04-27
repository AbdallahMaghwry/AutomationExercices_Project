package com.automationexercices.utils;

import com.automationexercices.utils.dataReader.PropertyReader;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class WaitManager {

    private WebDriver driver;

    public WaitManager(WebDriver driver) {
        this.driver = driver;
    }


    public FluentWait<WebDriver> FluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(java.time.Duration.ofSeconds(Long.parseLong(PropertyReader.GetProperty("DEFAULT_WAIT"))))
                .pollingEvery(java.time.Duration.ofMillis(100))
                .ignoreAll(ignoredExceptions());
    }


    private ArrayList<Class<? extends Exception>> ignoredExceptions()
    {
        //عملت اراي اوف ليست عشان ابقي ابعتها لل ignoreAll انا هنا بقول ان الكلاس دا جي من حته معينه و الحته المعينه دي بتاكسنيند الاكسيبشين
        ArrayList<Class<? extends Exception>> exceptions = new ArrayList<>();//  (?) دي معناها template
        exceptions.add(NoSuchElementException.class);//استثناء عدم وجود العنصر
        exceptions.add(StaleElementReferenceException.class);//استثناء مرجع العنصر القديم
        exceptions.add(ElementNotInteractableException.class);//أستثناء عدم امكانيه التفاعل مع العنصر
        exceptions.add(ElementClickInterceptedException.class); //استثناء تم اعتراض النقر علي العنصر
        return exceptions;
    }




}

