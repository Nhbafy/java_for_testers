package manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    void login(String user, String password) {
        sendKeys(By.name("user"), user);
        sendKeys(By.name("pass"), password);
        clickElementByLocator(By.xpath("//input[@value=\'Login\']"));
    }

}
