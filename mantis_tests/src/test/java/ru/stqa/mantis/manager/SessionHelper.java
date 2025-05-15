package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {
    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        sendKeys(By.name("username"), user);
        clickElementByLocator(By.cssSelector("input[type='submit']"));
        sendKeys(By.name("password"), password);
        clickElementByLocator(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }
}
