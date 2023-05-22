package ru.raiffeisen.education.tests.page_arch.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class ProductPage extends Page {
    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void openProductCard(String productLink) {
        webDriver.navigate().to(productLink);
    }

    public void addProductToCart() {
        webDriver.findElements(cssSelector("select[name='options[Size]']")).forEach(webElement -> {
            if (webElement.isDisplayed()) {
                var selectElement = new Select(webElement);
                selectElement.selectByIndex(2);
            }
        });
        int currentQuantity = Integer.parseInt(webDriver.findElement(cssSelector("div#cart span.quantity")).getText());
        webDriver.findElement(cssSelector("button[name=add_cart_product]")).click();
        currentQuantity++;
        webDriverWait.until(textToBe(cssSelector("div#cart span.quantity"), String.valueOf(currentQuantity)));
    }
}
