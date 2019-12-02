package steam.pages;

import elements.BaseWebElement;
import utils.Log;

public class PageBase {
    static final Log log = Log.getInstance();

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
