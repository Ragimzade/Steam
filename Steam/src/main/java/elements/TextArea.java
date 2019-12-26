package elements;

import org.openqa.selenium.By;

/**
 * Class describes webElement text-area
 */
public class TextArea extends BaseWebElement {
    /**
     * Constructor
     *
     * @param locator TextAres locator
     * @param name    TextAres name
     */
    public TextArea(By locator, String name) {
        super(locator, name);
    }

}
