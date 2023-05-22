package ru.raiffeisen.education.tests.simple.util;

import lombok.Builder;

@Builder
public class ProductPriceProperty {

    private String price;
    private Short[] color;
    private String fontSize;
    private String textDecoration;
    private int fontWeight;

    public String getPrice() {
        return price;
    }

    public Short[] getColor() {
        return color;
    }

    public String getFontSize() {
        return fontSize;
    }

    public String getTextDecoration() {
        return textDecoration;
    }

    public int getFontWeight() {
        return fontWeight;
    }
}
