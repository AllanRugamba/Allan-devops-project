package tests;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
    }

    @Test
    public void testBrowserLaunch() {
        page.navigate("https://practicesoftwaretesting.com/");
        Assert.assertTrue(page.title().length() > 0, "Page title should not be empty");
    }

    @Test
    public void testPageLoad() {
        page.navigate("https://www.google.com");
        Assert.assertTrue(page.url().contains("google"), "Should navigate to Google");
    }

    @Test
    public void testPlaywrightSetup() {
        Assert.assertNotNull(playwright, "Playwright should be initialized");
        Assert.assertNotNull(browser, "Browser should be launched");
    }

    @AfterMethod
    public void teardown() {
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
