package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.ArrayList;
import java.util.List;

/**
 * Page Object for https://practicesoftwaretesting.com/
 * Locators:
 * - Sort dropdown: [data-test="sort"]
 * - Product names: .card-title
 * - Product prices: [data-test="product-price"]
 */
public class SortingPage {
    Page page;

    public SortingPage(Page page) {
        this.page = page;
    }

    public void sortByNameAZ() {
        page.waitForSelector("[data-test='sort']", new Page.WaitForSelectorOptions().setTimeout(60000));
        page.selectOption("[data-test='sort']", "name,asc");
        page.waitForTimeout(2000);
    }

    public void sortByNameZA() {
        page.waitForSelector("[data-test='sort']", new Page.WaitForSelectorOptions().setTimeout(60000));
        page.selectOption("[data-test='sort']", "name,desc");
        page.waitForTimeout(2000);
    }

    public void sortByPriceLowToHigh() {
        page.waitForSelector("[data-test='sort']", new Page.WaitForSelectorOptions().setTimeout(60000));
        page.selectOption("[data-test='sort']", "price,asc");
        page.waitForTimeout(2000);
    }

    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        for (Locator element : page.locator(".card-title").all()) {
            names.add(element.textContent());
        }
        return names;
    }

    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (Locator element : page.locator("[data-test='product-price']").all()) {
            String priceText = element.textContent().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }
}
