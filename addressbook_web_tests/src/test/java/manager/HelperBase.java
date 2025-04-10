package manager;

import org.openqa.selenium.By;

public class HelperBase {
    protected ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void clickElementByName(String name) {
        manager.driver.findElement(By.name(name)).click();
    }

    protected void sendKeys(String elementName, String keys) {
        manager.driver.findElement(By.name(elementName)).sendKeys(keys);
    }

    protected void clickElementByLinkText(String linkText) {
        manager.driver.findElement(By.linkText(linkText)).click();
    }

    protected void clickElementByXpath(String xpathExpression) {
        manager.driver.findElement(By.xpath(xpathExpression)).click();
    }
}
