package ru.raiffeisen.education;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Collectors;

import static java.time.Duration.ofSeconds;
import static java.util.regex.Pattern.compile;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Task6Test {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(3L));
    }

    @Test
    void test() {

        driver.navigate().to("http://localhost/litecart/admin");
        var username = driver.findElement(cssSelector("input[name='username']"));
        var password = driver.findElement(cssSelector("input[name='password']"));
        var loginButton = driver.findElement(cssSelector("button[name='login']"));
        username.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();

        for (int menuCounter = 1; menuCounter <= 17; menuCounter++) {

            var menuItemElement = driver
                    .findElement(cssSelector(String.format("ul#box-apps-menu li#app-:nth-child(%s) > a", menuCounter)));

            menuItemElement.click();
            var subMenuCount = driver.findElements(cssSelector("ul.docs li[id^=doc]")).size();
            for (var subMenuCounter = 1; subMenuCounter <= subMenuCount; subMenuCounter++) {
                var subItemElement = driver.findElement(cssSelector(String.format("ul.docs li[id^=doc]:nth-child(%s) > a", subMenuCounter)));
                subItemElement.click();
                wait.until(presenceOfElementLocated(cssSelector("td#content h1")));
            }
        }

    }

    @AfterEach
    void stop() {
        this.driver.quit();
        this.driver = null;
    }

}
