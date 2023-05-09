package ru.raiffeisen.education.util;

import lombok.Builder;

@Builder
public class ProductPriceProperty {

    private String price;
    private Short[] color;
    private String fontSize;
    private String textDecoration;
    private String fontWeight;

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

    public String getFontWeight() {
        return fontWeight;
    }
}
