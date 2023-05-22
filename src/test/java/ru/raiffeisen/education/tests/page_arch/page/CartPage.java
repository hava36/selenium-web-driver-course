package ru.raiffeisen.education.tests.page_arch.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartPage extends Page {
    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void open() {
        webDriver.findElement(cssSelector("div#cart a.link")).click();
    }

    public void removeFirstPositionFromCart() {

        var productCountInCart = getProductCount();

        for (int productIndex = 1; productIndex <= productCountInCart; productIndex++) {
            if (productIndex == productCountInCart) {
                removeProduct();
            } else {
                clickToProductCard();
                removeProduct();
            }
        }

    }

    public int getProductCount() {
        return webDriver.findElements(cssSelector("ul.items li.item")).size();
    }

    private void clickToProductCard() {
        var shortProductLocator = cssSelector("ul.shortcuts li.shortcut a[class *= 'act']");
        webDriverWait.until(elementToBeClickable(shortProductLocator));
        webDriver.findElement(shortProductLocator).click();
    }

    private void removeProduct() {
        var removeButtonLocator = cssSelector("button[name=remove_cart_item]");
        webDriverWait.until(elementToBeClickable(removeButtonLocator));
        var removeButton = webDriver.findElement(removeButtonLocator);
        removeButton.sendKeys(Keys.ENTER);
        webDriverWait.until(stalenessOf(removeButton));
    }

}
