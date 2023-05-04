package ru.raiffeisen.education;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task7Test {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(3L));
    }

    @Test
    void test() {

        driver.navigate().to("http://localhost/litecart/");

        var elementsCount = driver.findElements(cssSelector("nav.content ul.list-vertical li a")).size();

        for (var counter = 1; counter <= elementsCount; counter++) {
            var categoryLinkElement = driver.findElement(cssSelector(String.format("nav.content ul.list-vertical li:nth-child(%s) a", counter)));
            categoryLinkElement.click();
            driver.findElements(cssSelector("ul[class='listing-wrapper products'] div.image-wrapper")).forEach(divImageWrapperElement -> {
                var countOfStickers = divImageWrapperElement.findElements(cssSelector("[class^='sticker']")).size();
                assertEquals(1, countOfStickers);
            });
        }
    }

    @AfterEach
    void stop() {
        this.driver.quit();
        this.driver = null;
    }

}
