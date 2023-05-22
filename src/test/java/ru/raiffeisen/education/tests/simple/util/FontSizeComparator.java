package ru.raiffeisen.education.tests.simple.util;

import java.util.Comparator;

public class FontSizeComparator implements Comparator<String> {
    @Override
    public int compare(String fontSize1, String fontSize2) {
        float fontSizeValue1 = Float.parseFloat(fontSize1.replace("px", ""));
        float fontSizeValue2 = Float.parseFloat(fontSize2.replace("px", ""));
        if (fontSizeValue1 > fontSizeValue2)
            return -1;
        else if (fontSizeValue1 < fontSizeValue2) {
            return 1;
        }
        return 0;
    }
}
