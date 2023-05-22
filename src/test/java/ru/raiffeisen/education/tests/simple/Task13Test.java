package ru.raiffeisen.education.tests.simple;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Task13Test {

    private WebDriver driver;
    private FluentWait<WebDriver> wait;

    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(10L)).ignoring(StaleElementReferenceException.class).pollingEvery(ofSeconds(1));
    }

    @Test
    void test() {

        openMainPage();
        addRandomProductToCart();
        openMainPage();
        addRandomProductToCart();
        openMainPage();
        addRandomProductToCart();
        openMainPage();
        removeProductsFromCart();

    }

    private void openMainPage() {
        driver.navigate().to("http://localhost/litecart");
    }

    private void addRandomProductToCart() {

        var productWebLink = driver.findElement(cssSelector("li.product a.link")).getAttribute("href");
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
    }

    private void removeProductsFromCart() {

        driver.findElement(cssSelector("div#cart a.link")).click();

        var productCountInCart = getProductCountInCart(cssSelector("ul.items li.item"));

        for (int productIndex = 1; productIndex <= productCountInCart; productIndex++) {
            if (productIndex == productCountInCart) {
                removeProduct();
            } else {
                clickToProductCard();
                removeProduct();
            }
        }

        assertEquals(0, getProductCountInCart(cssSelector("table.dataTable tr")));

    }

    private int getProductCountInCart(By viewItemsLocator) {
        return driver.findElements(viewItemsLocator).size();
    }

    private void clickToProductCard() {
        var shortProductLocator = cssSelector("ul.shortcuts li.shortcut a[class *= 'act']");
        wait.until(elementToBeClickable(shortProductLocator));
        driver.findElement(shortProductLocator).click();
    }

    private void removeProduct() {
        var removeButtonLocator = cssSelector("button[name=remove_cart_item]");
        wait.until(elementToBeClickable(removeButtonLocator));
        var removeButton = driver.findElement(removeButtonLocator);
        removeButton.sendKeys(Keys.ENTER);
        wait.until(stalenessOf(removeButton));
    }


    @AfterEach
    void stop() {
        this.driver.quit();
        this.driver = null;
    }


}
