package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        clickElementByLocator(By.linkText("add new"));
        sendKeys(By.name("firstname"), contact.firstName());
        sendKeys(By.name("middlename"), "middlename");
        sendKeys(By.name("lastname"), contact.lastName());
        sendKeys(By.name("nickname"), "nickname");
        sendKeys(By.name("title"), "title");
        sendKeys(By.name("company"), "company");
        sendKeys(By.name("address"), contact.address());
        sendKeys(By.name("home"), "home_telephone");
        sendKeys(By.name("mobile"), "mobile_telephone");
        sendKeys(By.name("work"), "work_telephone");
        sendKeys(By.name("fax"), "fax_telephone");
        sendKeys(By.name("email"), "email1@gg.com");
        sendKeys(By.name("email2"), "email2@gg.com");
        sendKeys(By.name("email3"), "email3@gg.com");
        sendKeys(By.name("homepage"), "homepage");
        dropdownElementByLocator(By.name("bday"), By.xpath("//option[. = '18']"));
        dropdownElementByLocator(By.name("bmonth"), By.xpath("//option[. = 'August']"));
        sendKeys(By.name("byear"), "1988");
        dropdownElementByLocator(By.name("aday"), By.xpath("//option[. = '21']"));
        dropdownElementByLocator(By.name("amonth"), By.xpath("//option[. = 'July']"));
        sendKeys(By.name("ayear"), "2005");
/*        manager.driver.findElement(By.name("new_group")).click();
        {
            WebElement dropdown = manager.driver.findElement(By.name("new_group"));
            dropdown.findElement(By.xpath("//option[. = 'name']")).click();
}*/
        clickElementByLocator(By.xpath("//input[@name=\'submit\']"));
        clickElementByLocator(By.linkText("home page"));
    }

    private void dropdownElementByLocator(By dropdownLocator, By valueLocator) {
        manager.driver.findElement(dropdownLocator).click();
        {
            WebElement dropdown = manager.driver.findElement(dropdownLocator);
            dropdown.findElement(valueLocator).click();
        }
    }

    public void canRemoveContact() {
        openHomePage();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.id("maintable"))) {
            clickElementByLocator(By.linkText("home"));
        }
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void removeAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
        clickElementByLocator(By.xpath("//input[@value=\'Delete\']"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }
}
