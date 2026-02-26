package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SortingTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://practicesoftwaretesting.com/");
    }

    @Test
    public void testSortByNameAZ() {
        driver.findElement(By.id("sort")).click();
        driver.findElement(By.cssSelector("option[value='name,asc']")).click();
        
        List<WebElement> products = driver.findElements(By.cssSelector(".card-title"));
        List<String> productNames = new ArrayList<>();
        for (WebElement product : products) {
            productNames.add(product.getText());
        }
        
        List<String> sortedNames = new ArrayList<>(productNames);
        sortedNames.sort(String::compareTo);
        
        Assert.assertEquals(productNames, sortedNames, "Products are not sorted A-Z");
    }

    @Test
    public void testSortByNameZA() {
        driver.findElement(By.id("sort")).click();
        driver.findElement(By.cssSelector("option[value='name,desc']")).click();
        
        List<WebElement> products = driver.findElements(By.cssSelector(".card-title"));
        List<String> productNames = new ArrayList<>();
        for (WebElement product : products) {
            productNames.add(product.getText());
        }
        
        List<String> sortedNames = new ArrayList<>(productNames);
        sortedNames.sort((a, b) -> b.compareTo(a));
        
        Assert.assertEquals(productNames, sortedNames, "Products are not sorted Z-A");
    }

    @Test
    public void testSortByPriceLowToHigh() {
        driver.findElement(By.id("sort")).click();
        driver.findElement(By.cssSelector("option[value='price,asc']")).click();
        
        List<WebElement> prices = driver.findElements(By.cssSelector(".card-text"));
        List<Double> priceValues = new ArrayList<>();
        for (WebElement price : prices) {
            String priceText = price.getText().replace("$", "");
            priceValues.add(Double.parseDouble(priceText));
        }
        
        List<Double> sortedPrices = new ArrayList<>(priceValues);
        sortedPrices.sort(Double::compareTo);
        
        Assert.assertEquals(priceValues, sortedPrices, "Prices are not sorted low to high");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
