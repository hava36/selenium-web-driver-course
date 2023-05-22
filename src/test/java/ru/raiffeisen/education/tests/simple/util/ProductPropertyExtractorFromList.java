package ru.raiffeisen.education.tests.simple.util;

import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.cssSelector;

public class ProductPropertyExtractorFromList implements ProductPropertyExtractor {

    private final CssColorExtractor cssColorExtractor = new CssColorExtractorImpl();

    @Override
    public ProductProperty extract(WebElement webElement) {

        var productElementInListName = webElement.findElement(cssSelector("div.name")).getText();

        var productElementInListRegularPriceElement = webElement.findElement(cssSelector("div.price-wrapper s.regular-price"));
        var productElementInListRegularPriceElementValue = productElementInListRegularPriceElement.getText();
        var productElementInListRegularPriceElementColor = cssColorExtractor.extractorColor(productElementInListRegularPriceElement.getCssValue("color"));
        var productElementInListRegularPriceElementTextDecoration = productElementInListRegularPriceElement.getCssValue("text-decoration");
        var productElementInListRegularPriceElementFontWeight = productElementInListRegularPriceElement.getCssValue("font-weight");
        var productElementInListRegularPriceElementFontSize = productElementInListRegularPriceElement.getCssValue("font-size");

        var productElementInListCampaignPriceElement = webElement.findElement(cssSelector("div.price-wrapper strong.campaign-price"));
        var productElementInListCampaignPriceElementValue = productElementInListCampaignPriceElement.getText();
        var productElementInListCampaignPriceElementColor = cssColorExtractor.extractorColor(productElementInListCampaignPriceElement.getCssValue("color"));
        var productElementInListCampaignPriceElementFontSize = productElementInListCampaignPriceElement.getCssValue("font-size");
        var productElementInListCampaignPriceElementTextDecoration = productElementInListCampaignPriceElement.getCssValue("text-decoration");
        var productElementInListCampaignPriceElementFontWeight = productElementInListCampaignPriceElement.getCssValue("font-weight");

        return ProductProperty.builder()
                .name(productElementInListName)
                .regularPriceProperty(ProductPriceProperty.builder()
                        .price(productElementInListRegularPriceElementValue)
                        .color(productElementInListRegularPriceElementColor)
                        .fontSize(productElementInListRegularPriceElementFontSize)
                        .textDecoration(productElementInListRegularPriceElementTextDecoration)
                        .fontWeight(Integer.parseInt(productElementInListRegularPriceElementFontWeight))
                        .build())
                .campaignPriceProperty(ProductPriceProperty.builder()
                        .price(productElementInListCampaignPriceElementValue)
                        .color(productElementInListCampaignPriceElementColor)
                        .fontSize(productElementInListCampaignPriceElementFontSize)
                        .textDecoration(productElementInListCampaignPriceElementTextDecoration)
                        .fontWeight(Integer.parseInt(productElementInListCampaignPriceElementFontWeight))
                        .build())
                .build();
    }
}
