package ru.raiffeisen.education.util.attach;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public interface FileAttachManager {

    void attach(WebDriver driver, By locator, String file);

}
