package ru.raiffeisen.education.util;

import org.openqa.selenium.WebElement;

public interface ProductPropertyExtractor {

    ProductProperty extract(WebElement webElement);

}
