package ru.raiffeisen.education;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static ru.raiffeisen.education.util.ExpectedConditions.anyWindowOtherThan;

public class Task14Test {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(10L));
    }

    @Test
    void test() {

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");

        var username = driver.findElement(cssSelector("input[name='username']"));
        var password = driver.findElement(cssSelector("input[name='password']"));
        var loginButton = driver.findElement(cssSelector("button[name='login']"));
        username.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();

        var mainWindow = driver.getWindowHandle();
        var oldWindows = driver.getWindowHandles();

        var addNewCountryWebElement = driver.findElement(cssSelector("td#content a.button"));
        addNewCountryWebElement.click();

        var externalWebElements = driver.findElements(cssSelector("table a[target='_blank'"));

        externalWebElements.forEach(externalWebElement -> {
            externalWebElement.click();
            String newWindow = wait.until(anyWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        });

    }


    @AfterEach
    void stop() {
        this.driver.quit();
        this.driver = null;
    }

}
