package ru.raiffeisen.education.tests.simple;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Collectors;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.cssSelector;

public class Task17Test {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(10L));
    }

    @Test
    void test() {

        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        var username = driver.findElement(cssSelector("input[name='username']"));
        var password = driver.findElement(cssSelector("input[name='password']"));
        var loginButton = driver.findElement(cssSelector("button[name='login']"));
        username.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();

        var productWebLinks = driver.findElements(cssSelector("tr.row a[href*='product']"))
                .stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());

        productWebLinks.forEach(link -> {
            driver.navigate().to(link);
            assertThat(driver.manage().logs().get("browser").getAll())
                    .isEmpty();
        });


    }


    @AfterEach
    void stop() {
        this.driver.quit();
        this.driver = null;
    }

}
