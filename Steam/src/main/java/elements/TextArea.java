package elements;

import org.openqa.selenium.By;

/**
 * Class describes webElement text-area
 */
public class TextArea extends BaseWebElement {
    /**
     * Constructor
     *
     * @param locator TextAre locator
     * @param name    TextAre name
     */
    public TextArea(By locator, String name) {
        super(locator, name);
    }

}
