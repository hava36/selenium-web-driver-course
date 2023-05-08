package ru.raiffeisen.education.util;

import lombok.Builder;

@Builder
public class ProductProperty {

    private String name;
    private ProductPriceProperty regularPriceProperty;
    private ProductPriceProperty campaignPriceProperty;

    public String getName() {
        return name;
    }

    public ProductPriceProperty getRegularPriceProperty() {
        return regularPriceProperty;
    }

    public ProductPriceProperty getCampaignPriceProperty() {
        return campaignPriceProperty;
    }

    public boolean campaignPriceIsLargerThanRegularPrice() {
        return campaignPriceProperty.getFontSize().compareTo(regularPriceProperty.getFontSize()) > 0;
    }
}
