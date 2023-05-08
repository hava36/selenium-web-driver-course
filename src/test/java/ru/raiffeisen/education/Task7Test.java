package ru.raiffeisen.education;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;

public class Task7Test {

    private WebDriver driver;

    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
    }

    @Test
    void test() {

        driver.navigate().to("http://localhost/litecart/");

        var productElements = driver.findElements(cssSelector("li.product"));

        for (WebElement productElement : productElements) {
            var stickerElementsForProduct = productElement.findElements(By.cssSelector(".sticker"));
            assertEquals(1, stickerElementsForProduct.size());
        }

    }

    @AfterEach
    void stop() {
        this.driver.quit();
        this.driver = null;
    }

}
