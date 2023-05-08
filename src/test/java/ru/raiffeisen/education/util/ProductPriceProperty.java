package ru.raiffeisen.education.util;

import lombok.Builder;

@Builder
public class ProductPriceProperty {

    private String price;
    private String[] color;
    private String fontSize;

    public String getPrice() {
        return price;
    }

    public String[] getColor() {
        return color;
    }

    public String getFontSize() {
        return fontSize;
    }
}
