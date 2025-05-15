package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class HelperBase {
    protected ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void clickElementByLocator(By locator) {
        manager.driver().findElement(locator).click();
    }

    protected void sendKeys(By locator, String keys) {
        clickElementByLocator(locator);
        manager.driver().findElement(locator).clear();
        manager.driver().findElement(locator).sendKeys(keys);
    }

    protected boolean isElementPresent(By locator){
       return manager.driver().findElements(locator).size()>0;
    }

}
