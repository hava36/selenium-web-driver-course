package ru.raiffeisen.education;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
