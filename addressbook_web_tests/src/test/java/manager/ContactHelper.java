package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
        super.manager = manager;
    }

    public void createContact(ContactData contact) {
        clickElementByLinkText("add new");
        sendKeys("firstname", contact.firstName());
        sendKeys("middlename", "middlename");
        sendKeys("lastname", contact.lastName());
        sendKeys("nickname", "nickname");
        sendKeys("title", "title");
        sendKeys("company", "company");
        sendKeys("address", contact.address());
        sendKeys("home", "home_telephone");
        sendKeys("mobile", "mobile_telephone");
        sendKeys("work", "work_telephone");
        sendKeys("fax", "fax_telephone");
        sendKeys("email", "email1@gg.com");
        sendKeys("email2", "email2@gg.com");
        sendKeys("email3", "email3@gg.com");
        sendKeys("homepage", "homepage");
        dropdownElementByName("bday", "//option[. = '18']");
        dropdownElementByName("bmonth", "//option[. = 'August']");
        sendKeys("byear", "1988");
        dropdownElementByName("aday", "//option[. = '21']");
        dropdownElementByName("amonth", "//option[. = 'July']");
        sendKeys("ayear", "2005");
/*        manager.driver.findElement(By.name("new_group")).click();
        {
            WebElement dropdown = manager.driver.findElement(By.name("new_group"));
            dropdown.findElement(By.xpath("//option[. = 'name']")).click();
}*/
        manager.driver.findElement(By.xpath("//input[@name=\'submit\']")).click();
        manager.driver.findElement(By.linkText("home page")).click();
    }

    private void dropdownElementByName(String name, String xpathExpression) {
        manager.driver.findElement(By.name(name)).click();
        {
            WebElement dropdown = manager.driver.findElement(By.name(name));
            dropdown.findElement(By.xpath(xpathExpression)).click();
        }
    }

    public void canRemoveContact() {
        openHomePage();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.id("maintable"))) {
            clickElementByLinkText("home");
        }
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }
}
