package kaspersky.pages;

import base_entity.BaseEntity;
import elements.BaseWebElement;

public class BasePage extends BaseEntity {

    public BasePage() {
    }

    protected void assertPageIsOpened(BaseWebElement element) {

        if (element.isElementPresent()) {
            log.info(String.format("Page '%s' is opened", getClass().getName()));
        } else {
            log.error(String.format("Page '%s' is not opened", getClass().getName()));
            throw new AssertionError(element + " not found");
        }
    }
}
