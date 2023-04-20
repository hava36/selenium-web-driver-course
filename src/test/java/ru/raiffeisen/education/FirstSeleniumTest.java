package ru.raiffeisen.education;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstSeleniumTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
  void init() {
    this.driver = new ChromeDriver();
    this.wait = new WebDriverWait(driver, ofSeconds(3L));
  }

  @Test
  void firstTest() {
    driver.navigate().to("http://www.google.com");
    driver.findElement(name("q")).sendKeys("webdriver");
    driver.findElement(name("btnK")).submit();
    wait.until(titleContains("webdriver"));
  }

  @AfterEach
  void stop() {
    this.driver.quit();
    this.driver = null;
  }

}
