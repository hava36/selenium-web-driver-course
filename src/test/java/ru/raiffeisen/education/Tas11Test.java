package ru.raiffeisen.education;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

import static java.time.Duration.ofSeconds;
import static java.util.regex.Pattern.compile;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.textMatches;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class Tas11Test {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(3L));
    }

    @Test
    void test() {

        var email = "email_" + UUID.randomUUID() + "@t.ru";
        var password = "1";

        createAccount(email, password);
        logout();
        login(email, password);
        logout();

    }

    private void createAccount(String email, String password) {

        driver.navigate().to("http://localhost/litecart/en/create_account");

        var webElementFirstName = driver.findElement(cssSelector("input[name=firstname]"));
        webElementFirstName.sendKeys("John");

        var webElementLastName = driver.findElement(cssSelector("input[name=lastname]"));
        webElementLastName.sendKeys("Johnson");

        var webElementAddress1 = driver.findElement(cssSelector("input[name=address1]"));
        webElementAddress1.sendKeys("Address 1");

        var webElementPostCode = driver.findElement(cssSelector("input[name=postcode]"));
        webElementPostCode.sendKeys("12345");

        var webElementCity = driver.findElement(cssSelector("input[name=city]"));
        webElementCity.sendKeys("Atalanta");

        var countrySelect = new Select(driver.findElement(cssSelector("select[name=country_code]")));
        countrySelect.selectByValue("US");

        var webElementEmail = driver.findElement(cssSelector("input[name=email]"));
        webElementEmail.sendKeys(email);

        var webElementPhone = driver.findElement(cssSelector("input[name=phone]"));
        webElementPhone.sendKeys("+79200010101");

        var webElementDesiredPass = driver.findElement(cssSelector("input[name=password]"));
        webElementDesiredPass.sendKeys(password);

        var webElementConfirmedPass = driver.findElement(cssSelector("input[name=confirmed_password]"));
        webElementConfirmedPass.sendKeys(password);

        var createAccountButton = driver.findElement(cssSelector("button[name=create_account]"));
        createAccountButton.click();

        var jsDriver = (JavascriptExecutor) driver;
        jsDriver.executeScript("document.getElementById('notices-wrapper').style.display='block';");

        wait.until(textToBe(xpath("//*[@class='notice success']"),
                "Your customer account has been created."));

    }

    private void login(String email, String password) {

        driver.navigate().to("http://localhost/litecart/en/");

        var webElementEmail = driver.findElement(cssSelector("input[name=email]"));
        webElementEmail.sendKeys(email);

        var webElementPassword = driver.findElement(cssSelector("input[name=password]"));
        webElementPassword.sendKeys(password);

        var loginButton = driver.findElement(cssSelector("button[name=login]"));
        loginButton.click();

    }

    private void logout() {

        driver.navigate().to("http://localhost/litecart/en/logout");


    }

    @AfterEach
    void stop() {
        this.driver.quit();
        this.driver = null;
    }


}
