package ru.raiffeisen.education.tests.page_arch.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.raiffeisen.education.tests.page_arch.page.CartPage;
import ru.raiffeisen.education.tests.page_arch.page.HomePage;
import ru.raiffeisen.education.tests.page_arch.page.ProductPage;

public class Application {

    private final WebDriver webDriver;

    private final HomePage homePage;
    private final ProductPage productPage;
    private final CartPage cartPage;

    public Application() {
        this.webDriver = new ChromeDriver();
        this.homePage = new HomePage(webDriver);
        this.productPage = new ProductPage(webDriver);
        this.cartPage = new CartPage(webDriver);
    }

    public void quit() {
        this.webDriver.quit();
    }

    public void addProductsToCart(int productCount) {

        while (productCount > 0) {
            homePage.open();
            productPage.openProductCard(homePage.getFirstProductLink());
            productPage.addProductToCart();
            productCount--;
        }

    }


    public void removeAllProductsFromCart() {
        this.cartPage.open();
        this.cartPage.removeFirstPositionFromCart();
    }

    public int getProductCountInCart() {
        return cartPage.getProductCount();
    }
}
