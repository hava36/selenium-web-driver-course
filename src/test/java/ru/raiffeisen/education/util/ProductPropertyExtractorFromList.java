package ru.raiffeisen.education.util;

import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.cssSelector;

public class ProductPropertyExtractorFromList implements ProductPropertyExtractor {
    @Override
    public ProductProperty extract(WebElement webElement) {

        var productElementInListName = webElement.findElement(cssSelector("div.name")).getText();

        var productElementInListRegularPriceElement = webElement.findElement(cssSelector("div.price-wrapper s.regular-price"));
        var productElementInListRegularPriceElementValue = productElementInListRegularPriceElement.getText();
        var productElementInListRegularPriceElementColor = productElementInListRegularPriceElement.getCssValue("color");
        var productElementInListRegularPriceElementFontSize = productElementInListRegularPriceElement.getCssValue("font-size");

        var productElementInListCampaignPriceElement = webElement.findElement(cssSelector("div.price-wrapper strong.campaign-price"));
        var productElementInListCampaignPriceElementValue = productElementInListCampaignPriceElement.getText();
        var productElementInListCampaignPriceElementColor = productElementInListCampaignPriceElement.getCssValue("color");
        var productElementInListCampaignPriceElementFontSize = productElementInListCampaignPriceElement.getCssValue("font-size");


        return ProductProperty.builder()
                .name(productElementInListName)
                .regularPriceProperty(ProductPriceProperty.builder()
                        .price(productElementInListRegularPriceElementValue)
                        .color(productElementInListRegularPriceElementColor)
                        .fontSize(productElementInListRegularPriceElementFontSize)
                        .build())
                .campaignPriceProperty(ProductPriceProperty.builder()
                        .price(productElementInListCampaignPriceElementValue)
                        .color(productElementInListCampaignPriceElementColor)
                        .fontSize(productElementInListCampaignPriceElementFontSize)
                        .build())
                .build();
    }
}
