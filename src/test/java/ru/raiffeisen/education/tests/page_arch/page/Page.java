package ru.raiffeisen.education.tests.page_arch.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Page {

    protected final WebDriver webDriver;
    protected final WebDriverWait webDriverWait;


    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }
}
