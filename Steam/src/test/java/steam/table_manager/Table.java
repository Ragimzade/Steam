package steam.table_manager;

import elements.BaseWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class Table extends BaseWebElement {
    WebDriver driver;
    private static final String TABLE_ROWS = "//a[@class='tab_item   app_impression_tracked']";

    public Table(By locator, String name) {
        super(locator, name);
    }

    //
    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }//перенести в бейс элемент. выбор по имени не по индексу, поменять циклы, модфикаторры, загрузка файла, платформы

    public List<WebElement> getRows(int number) {
        scrollToElement(driver.findElement(By.id("tab_select_ComingSoon")));
        waitForConditions(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(TABLE_ROWS), number));

        List<WebElement> rows = driver.findElements(By.xpath(TABLE_ROWS));
        return rows;
    }

    public List<WebElement> getSelectedRows(int numberOfRows) {
        List<WebElement> rows = getRows(numberOfRows);
        List<WebElement> selectedRows = new ArrayList<>();

        //заменить
        for (int i = 0; i < numberOfRows; i++) {
            selectedRows.add(rows.get(i));
        }
        return selectedRows;
    }
}
