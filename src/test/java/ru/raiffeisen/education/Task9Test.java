package ru.raiffeisen.education;


import com.google.common.collect.Comparators;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Collectors;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.cssSelector;

public class Task9Test {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(3L));
    }

    @Test
    void test() {

        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones ");

        login();

        var zoneLinks = driver.findElements(cssSelector("table.dataTable tr.row td:nth-child(3) a"))
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());

        zoneLinks.forEach(zoneLink -> {
            driver.navigate().to(zoneLink);
            wait.until(webDriver -> {
                var zoneNames = webDriver.findElements(cssSelector("table.dataTable select[name*=zone_code] option[selected=selected]"))
                        .stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
                return Comparators.isInOrder(zoneNames, String::compareTo);
            });
        });

    }


    @AfterEach
    void stop() {
        this.driver.quit();
        this.driver = null;
    }

    private void login() {
        var username = driver.findElement(cssSelector("input[name='username']"));
        var password = driver.findElement(cssSelector("input[name='password']"));
        var loginButton = driver.findElement(cssSelector("button[name='login']"));
        username.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();
    }

}
