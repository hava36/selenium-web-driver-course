package ru.raiffeisen.education.tests.simple;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.raiffeisen.education.tests.simple.util.attach.FileAttachManager;
import ru.raiffeisen.education.tests.simple.util.attach.FileAttachManagerImpl;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.UUID;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.linkText;

public class Task12Test {

    private WebDriver driver;
    private WebDriverWait wait;
    private FileAttachManager fileAttachManager;

    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(3L));
        this.fileAttachManager = new FileAttachManagerImpl();
    }

    @Test
    void test() throws URISyntaxException {

        navigateToCatalogPage();

        login();

        var productId = "test_name_".concat(UUID.randomUUID().toString());

        addNewProduct(productId);

        navigateToCatalogPage();

        checkExistenceOfNewProduct(productId);

    }


    @AfterEach
    void stop() {
        this.driver.quit();
        this.driver = null;
    }

    private void login() {
        var username = driver.findElement(cssSelector("input[name='username']"));
        var password = driver.findElement(cssSelector("input[name='password']"));
        var loginButton = driver.findElement(cssSelector("button[name='login']"));
        username.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();
    }

    private void navigateToCatalogPage() {
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog");
    }

    private void addNewProduct(String productTestName) throws URISyntaxException {

        fillGeneralTab(productTestName);
        fillInformationTab();
        fillPricesTab();
        saveProduct();

    }

    private void fillGeneralTab(String productTestName) throws URISyntaxException {

        var addNewProductButton = driver.findElement(cssSelector("a.button[href*='edit_product']"));
        addNewProductButton.click();

        var enabledStatusRadioButton = driver.findElement(cssSelector("input[name='status'][value='1']"));
        enabledStatusRadioButton.click();

        var inputNameEditText = driver.findElement(cssSelector("input[name='name[en]']"));
        inputNameEditText.sendKeys(productTestName);

        var inputCodeEditText = driver.findElement(cssSelector("input[name='code']"));
        inputCodeEditText.sendKeys("test code");

        var femaleGenderCheckbox = driver.findElement(cssSelector("input[type='checkbox'][name='product_groups[]'][value='1-2']"));
        femaleGenderCheckbox.click();

        var inputQuantityEditText = driver.findElement(cssSelector("input[name='quantity']"));
        inputQuantityEditText.sendKeys("1");

        var inputDateValidFrom = driver.findElement(cssSelector("input[name='date_valid_from']"));
        inputDateValidFrom.click();
        inputDateValidFrom.sendKeys("01012021");

        var inputDateValidTo = driver.findElement(cssSelector("input[name='date_valid_to']"));
        inputDateValidTo.click();
        inputDateValidTo.sendKeys("01012024");

        var res = getClass().getClassLoader().getResource("images\\selenium_logo.png");
        var file = Paths.get(res.toURI()).toFile();
        var absolutePath = file.getAbsolutePath();
        fileAttachManager.attach(driver, cssSelector("input[name = 'new_images[]']"), absolutePath);

    }

    private void fillInformationTab() {

        var tabInfromationWebElement = driver.findElement(cssSelector("a[href = '#tab-information']"));
        tabInfromationWebElement.click();

        var manufacturerWebElement = driver.findElement(cssSelector("select[name = 'manufacturer_id']"));
        manufacturerWebElement.click();
        manufacturerWebElement.sendKeys(Keys.PAGE_DOWN);
        manufacturerWebElement.sendKeys(Keys.ENTER);

        var supplierWebElement = driver.findElement(cssSelector("select[name = 'supplier_id']"));
        supplierWebElement.click();
        supplierWebElement.sendKeys(Keys.PAGE_DOWN);
        supplierWebElement.sendKeys(Keys.ENTER);

        var keywordsWebElement = driver.findElement(cssSelector("input[name = 'keywords']"));
        keywordsWebElement.sendKeys("test keywords");

        var shortDescriptionWebElement = driver.findElement(cssSelector("input[name *= 'short_description']"));
        shortDescriptionWebElement.sendKeys("test short description");

        var descriptionWebElement = driver.findElement(cssSelector("div.trumbowyg-editor"));
        descriptionWebElement.sendKeys("test description");

        var headTitleWebElement = driver.findElement(cssSelector("input[name *= 'head_title']"));
        headTitleWebElement.sendKeys("head title");

        var metaDescriptionWebElement = driver.findElement(cssSelector("input[name *= 'meta_description']"));
        metaDescriptionWebElement.sendKeys("meta description");

    }

    private void fillPricesTab() {

        var tabPricesWebElement = driver.findElement(cssSelector("a[href = '#tab-prices']"));
        tabPricesWebElement.click();

        var purchasePriceWebElement = driver.findElement(cssSelector("input[name = 'purchase_price']"));
        purchasePriceWebElement.sendKeys("10");

        var purchasePriceCurrencyCodeWebElement = driver.findElement(cssSelector("select[name = 'purchase_price_currency_code']"));
        purchasePriceCurrencyCodeWebElement.click();
        purchasePriceCurrencyCodeWebElement.sendKeys(Keys.PAGE_DOWN);
        purchasePriceCurrencyCodeWebElement.sendKeys(Keys.ENTER);

        var purchasePriceUsdWebElement = driver.findElement(cssSelector("input[name = 'prices[USD]']"));
        purchasePriceUsdWebElement.sendKeys("1");

        var purchasePriceEuroWebElement = driver.findElement(cssSelector("input[name = 'prices[EUR]']"));
        purchasePriceEuroWebElement.sendKeys("1");

    }

    private void saveProduct() {

        var saveButtonWebElement = driver.findElement(cssSelector("button[name = 'save']"));
        saveButtonWebElement.click();

    }

    private void checkExistenceOfNewProduct(String productTestName) {

        var productWebElements = driver.findElements(linkText(productTestName));

        assertThat(productWebElements).hasSize(1);

    }


}
