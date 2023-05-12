package ru.raiffeisen.education;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.raiffeisen.education.util.ProductPropertyExtractor;
import ru.raiffeisen.education.util.ProductPropertyExtractorFromCard;
import ru.raiffeisen.education.util.ProductPropertyExtractorFromList;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.openqa.selenium.By.cssSelector;

public class Task10Test {

    private WebDriver driver;
    private WebDriverWait wait;
    private ProductPropertyExtractor productPropertyExtractorFromList;
    private ProductPropertyExtractor productPropertyExtractorFromCard;


    @BeforeEach
    void init() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, ofSeconds(3L));
        this.productPropertyExtractorFromList = new ProductPropertyExtractorFromList();
        this.productPropertyExtractorFromCard = new ProductPropertyExtractorFromCard();
    }

    @Test
    void yellowDuckProductItemsInCardAndInListAreEquals() {

        driver.navigate().to("http://localhost/litecart");

        var productElementInList = driver.findElement(cssSelector("div#box-campaigns li.product"));
        var productElementInListLink = productElementInList.findElement(cssSelector("a.link")).getAttribute("href");

        var productFromList = productPropertyExtractorFromList.extract(driver.findElement(cssSelector("div#box-campaigns li.product")));

        driver.navigate().to(productElementInListLink);

        var productCard = productPropertyExtractorFromCard.extract(driver.findElement(cssSelector("div#box-product")));

        assertAll(
                () -> assertThat(productCard.getName()).isEqualTo(productFromList.getName()),
                () -> assertThat(productCard.getRegularPriceProperty().getPrice()).isEqualTo(productFromList.getRegularPriceProperty().getPrice()),
                () -> assertThat(productCard.getCampaignPriceProperty().getPrice()).isEqualTo(productFromList.getCampaignPriceProperty().getPrice()),
                () -> {
                    var rgbColor = productFromList.getRegularPriceProperty().getColor();
                    assertThat(rgbColor).hasSize(3);
                    assertThat(rgbColor[0]).isEqualTo(rgbColor[1]).isEqualTo(rgbColor[2]);
                },
                () -> {
                    var rgbColor = productFromList.getCampaignPriceProperty().getColor();
                    assertThat(rgbColor).hasSize(3);
                    assertThat(rgbColor[1]).isEqualTo(rgbColor[2]).isZero();
                },
                () -> {
                    var rgbColor = productCard.getRegularPriceProperty().getColor();
                    assertThat(rgbColor).hasSize(3);
                    assertThat(rgbColor[0]).isEqualTo(rgbColor[1]).isEqualTo(rgbColor[2]);
                },
                () -> {
                    var rgbColor = productCard.getCampaignPriceProperty().getColor();
                    assertThat(rgbColor).hasSize(3);
                    assertThat(rgbColor[1]).isEqualTo(rgbColor[2]).isZero();
               },
                () -> assertThat(productFromList.getRegularPriceProperty().getTextDecoration()).startsWith("line-through"),
                () -> assertThat(productCard.getRegularPriceProperty().getTextDecoration()).startsWith("line-through"),
                () -> assertThat(productFromList.getCampaignPriceProperty().getFontWeight()).isGreaterThanOrEqualTo(700),
                () -> assertThat(productCard.getCampaignPriceProperty().getFontWeight()).isGreaterThanOrEqualTo(700),
                () -> assertThat(productCard.campaignPriceIsLargerThanRegularPrice()).isTrue(),
                () -> assertThat(productFromList.campaignPriceIsLargerThanRegularPrice()).isTrue()
        );

    }


    @AfterEach
    void stop() {
        this.driver.quit();
        this.driver = null;
    }


}
