package ru.raiffeisen.education;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class LitecartLoginSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(10L));
    }

    @Test
    void litecartLogin() {

        driver.navigate().to("http://localhost/litecart/en/");
        var email = driver.findElement(cssSelector("input[name='email']"));
        var password = driver.findElement(cssSelector("input[name='password']"));
        var loginButton = driver.findElement(cssSelector("button[name='login']"));
        email.sendKeys("vhavabox@gmail.com");
        password.sendKeys("1");
        loginButton.click();

        var jsDriver = (JavascriptExecutor) driver;
        jsDriver.executeScript("document.getElementById('notices-wrapper').style.display='block';");

        wait.until(textMatches(xpath("//*[@class='notice success']"),
                Pattern.compile("You are now logged in as [.]*")));
    }

    @AfterEach
    void stop() {
        this.driver.quit();
        this.driver = null;
    }

}
