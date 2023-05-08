package ru.raiffeisen.education.util;

import java.util.Arrays;

public class CssColorExtractorImpl implements CssColorExtractor {
    @Override
    public String[] extractorColor(String color) {
        return Arrays.stream(color.replace("rgb(", "").replace(")", "")
                .replace("rgba(", "").replace(")", "")
                .replace(" ", "")
                .split(",")).limit(3).toArray(String[]::new);
    }
}
