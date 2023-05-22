package ru.raiffeisen.education.tests.page_arch.page;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.cssSelector;

public class HomePage extends Page {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public void open() {
        webDriver.navigate().to("http://localhost/litecart");
    }

    public String getFirstProductLink() {
        return webDriver.findElement(cssSelector("li.product a.link")).getAttribute("href");
    }
}
