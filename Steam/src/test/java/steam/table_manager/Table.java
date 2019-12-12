package steam.table_manager;

import elements.BaseWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class Table extends BaseWebElement {

    private static final String TABLE_ROWS = "//a[@class='tab_item   app_impression_tracked']";

    public Table(By locator, String name) {
        super(locator, name);
    }

    public List<WebElement> getRows() {
        scrollToElement(driver.findElement(By.xpath("//div[@class='tab_content' and contains(.,'Top')]")));
        waitForConditions(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(TABLE_ROWS), 3));

        return driver.findElements(By.xpath(TABLE_ROWS));
    }

    public List<WebElement> getSelectedRows(int numberOfRows) {
        List<WebElement> rows = getRows();
        return rows.stream()
                .limit(numberOfRows)
                .collect(Collectors.toList());
    }

}
