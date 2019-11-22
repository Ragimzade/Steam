package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class Table extends BaseWebElement {

    public Table(By locator, String name) {
        super(locator, name);
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> getRows() {
        scrollToElement(driver.findElement(By.id("tab_select_ComingSoon")));
        waitForConditions(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//a[@class='tab_item   app_impression_tracked']"), 3));

        List<WebElement> rows = driver.findElements(By.xpath("//a[@class='tab_item   app_impression_tracked']"));
        List<WebElement> first3rows = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            first3rows.add(rows.get(i));
        }
        return first3rows;
    }


}
