package ru.raiffeisen.education.util;

import java.util.Arrays;

public class CssColorExtractorImpl implements CssColorExtractor {
    @Override
    public Short[] extractorColor(String color) {
        return Arrays.stream(color.replace("rgb(", "").replace(")", "")
                        .replace("rgba(", "").replace(")", "")
                        .replace(" ", "")
                        .split(","))
                .map(Short::parseShort)
                .limit(3).toArray(Short[]::new);
    }
}
