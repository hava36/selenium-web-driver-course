package ru.raiffeisen.education;


import com.google.common.collect.Comparators;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Collectors;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.cssSelector;

public class Task8Test {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(3L));
    }

    @Test
    void checkAlphabeticalOrderOfCountries() {

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");

        login();

        var countryNames = driver.findElements(cssSelector("table.dataTable tr.row td:nth-child(5)")).stream().map(WebElement::getText).collect(Collectors.toList());

        assertTrue(Comparators.isInOrder(countryNames, String::compareTo));

    }

    @Test
    void checkZoneIdsInCountriesWithFilledZones() {

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");

        login();

        var countryLinks = driver.findElements(cssSelector("table.dataTable tr.row")).stream().filter(webElement -> {
            var columnValues = webElement.getAttribute("innerText").split("\t");
            return !columnValues[5].equals("0");
        }).map(webElement -> webElement.findElement(cssSelector("td:nth-child(5) a")).getAttribute("href"))
                .collect(Collectors.toList());

        countryLinks.forEach(countryLink -> {
            driver.navigate().to(countryLink);
            wait.until(webDriver -> {
                var zoneNames = webDriver.findElements(cssSelector("table#table-zones tr td:nth-child(3)"))
                        .stream()
                        .map(WebElement::getText)
                        .filter(s -> !s.isBlank())
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
