package steam.table_manager;

import elements.BaseWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class Table extends BaseWebElement {
    private static final String TABLE_ROWS = "//div[@class='tab_item_content']";

    /**
     * Base constructor
     *
     * @param locator Table locator
     * @param name    Table name
     */
    public Table(By locator, String name) {
        super(locator, name);
    }

    /**
     * Scrolls the page into view and gets all rows of the table
     *
     * @return list of rows
     */
    public List<WebElement> getAllRows() {
        scrollToElement(driver.findElement(By.xpath("//div[@class='tab_content' and contains(.,'Top')]")));
        return driver.findElements(By.xpath(TABLE_ROWS));
    }

    /**
     * Gets list with selected number of rows
     *
     * @param numberOfRows required number of rows
     * @return list with required number of rows
     * @see #getAllRows()
     */
    public List<WebElement> getSelectedRows(int numberOfRows) {
        List<WebElement> rows = getAllRows();

        return rows.stream()
                .limit(numberOfRows)
                .collect(Collectors.toList());
    }
}
