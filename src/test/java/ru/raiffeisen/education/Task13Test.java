package ru.raiffeisen.education;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class Task13Test {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(10L));
    }

    @Test
    void test() {

        driver.navigate().to("http://localhost/litecart");
        var productWebLinks = extractProductLinks();
        addProductToCart(productWebLinks);
        removeProductsFromCart();

    }

    private List<String> extractProductLinks() {
        return driver.findElements(cssSelector("li.product a.link"))
                .stream().limit(3)
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
    }

    private void addProductToCart(List<String> productWebLinks) {
        productWebLinks.forEach(productWebLink -> {
            driver.navigate().to(productWebLink);
            driver.findElements(cssSelector("select[name='options[Size]']")).forEach(webElement -> {
                if (webElement.isDisplayed()) {
                    var selectElement = new Select(webElement);
                    selectElement.selectByIndex(2);
                }
            });

            int currentQuantity = Integer.parseInt(driver.findElement(cssSelector("div#cart span.quantity")).getText());
            driver.findElement(cssSelector("button[name=add_cart_product]")).click();
            currentQuantity++;
            wait.until(textToBe(cssSelector("div#cart span.quantity"), String.valueOf(currentQuantity)));
        });
    }

    private void removeProductsFromCart() {
        driver.findElement(cssSelector("div#cart a.link")).click();

        var orderWebElements = driver.findElements(cssSelector("table.dataTable tr"));

        driver.findElements(cssSelector("ul.shortcuts li.shortcut a.inact")).forEach(webElement -> {
            webElement.click();
            driver.findElement(cssSelector("button[name=remove_cart_item]")).click();
            wait.until(stalenessOf(orderWebElements.remove(orderWebElements.size() - 1)));
        });
    }


    @AfterEach
    void stop() {
        this.driver.quit();
        this.driver = null;
    }

}
