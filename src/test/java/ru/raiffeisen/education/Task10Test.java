package ru.raiffeisen.education;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
                () -> assertThat(productFromList.getRegularPriceProperty().getColor()).isEqualTo(new String[] {"119", "119", "119"}),
                () -> assertThat(productFromList.getCampaignPriceProperty().getColor()).isEqualTo(new String[] {"204", "0", "0"}),
                () -> assertThat(productCard.getRegularPriceProperty().getColor()).isEqualTo(new String[] {"102", "102", "102"}),
                () -> assertThat(productCard.getCampaignPriceProperty().getColor()).isEqualTo(new String[] {"204", "0", "0"}),
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
