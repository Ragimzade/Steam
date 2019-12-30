package elements;

import org.openqa.selenium.By;

public class Link extends BaseWebElement {
    /**
     * Base constructor
     *
     * @param locator Element locator
     * @param name    Element name
     */
    public Link(By locator, String name) {
        super(locator, name);
    }
}
