package steam.pages;

import base_entity.BaseEntity;
import elements.BaseWebElement;

public class PageBase extends BaseEntity {

    public PageBase() {
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
