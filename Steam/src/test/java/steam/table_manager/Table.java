package steam.table_manager;

import elements.BaseWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class Table extends BaseWebElement {

    private static final String TABLE_ROWS = "//a[@class='tab_item   app_impression_tracked']";
    public static final int NUMBER_OF_ROWS_TO_BE = 3;

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
     * Scrolls the page into view and gets the rows of the table
     *
     * @return list of rows
     */
    public List<WebElement> getRows() {
        scrollToElement(driver.findElement(By.xpath("//div[@class='tab_content' and contains(.,'Top')]")));
        waitForCondition(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(TABLE_ROWS), NUMBER_OF_ROWS_TO_BE));

        return driver.findElements(By.xpath(TABLE_ROWS));
    }

    /**
     * Gets list with selected number of rows
     *
     * @param numberOfRows required number of rows
     * @return list with required number of rows
     * @see #getRows()
     */
    public List<WebElement> getSelectedRows(int numberOfRows) {
        List<WebElement> rows = getRows();
        return rows.stream()
                .limit(numberOfRows)
                .collect(Collectors.toList());
    }

}
