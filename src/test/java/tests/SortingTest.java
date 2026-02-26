package tests;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SortingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for sorting functionality on https://practicesoftwaretesting.com/
 * Tests validate:
 * - Sort by Name A-Z
 * - Sort by Name Z-A
 * - Sort by Price Low to High
 */
public class SortingTest {
    Playwright playwright;
    Browser browser;
    Page page;
    SortingPage sortingPage;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://practicesoftwaretesting.com/");
        sortingPage = new SortingPage(page);
    }

    @Test
    public void testSortByNameAZ() {
        sortingPage.sortByNameAZ();
        List<String> productNames = sortingPage.getProductNames();
        List<String> sortedNames = new ArrayList<>(productNames);
        sortedNames.sort(String::compareTo);
        Assert.assertEquals(productNames, sortedNames, "Products are not sorted A-Z");
    }

    @Test
    public void testSortByNameZA() {
        sortingPage.sortByNameZA();
        List<String> productNames = sortingPage.getProductNames();
        List<String> sortedNames = new ArrayList<>(productNames);
        sortedNames.sort((a, b) -> b.compareTo(a));
        Assert.assertEquals(productNames, sortedNames, "Products are not sorted Z-A");
    }

    @Test
    public void testSortByPriceLowToHigh() {
        sortingPage.sortByPriceLowToHigh();
        List<Double> priceValues = sortingPage.getProductPrices();
        List<Double> sortedPrices = new ArrayList<>(priceValues);
        sortedPrices.sort(Double::compareTo);
        Assert.assertEquals(priceValues, sortedPrices, "Prices are not sorted low to high");
    }

    @AfterMethod
    public void teardown() {
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
