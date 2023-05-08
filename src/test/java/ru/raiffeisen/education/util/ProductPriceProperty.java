package ru.raiffeisen.education.util;

import lombok.Builder;

import java.util.Objects;

@Builder
public class ProductPriceProperty {

    private String price;
    private String color;
    private String fontSize;

    public String getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getFontSize() {
        return fontSize;
    }
}
