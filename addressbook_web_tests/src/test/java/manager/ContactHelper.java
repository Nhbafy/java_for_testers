package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

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
        clickElementByLocator(By.xpath("//input[@name=\'submit\']"));
        clickElementByLocator(By.linkText("home page"));
    }

    public void createContact(ContactData contact, GroupData group) {
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
        selectGroup(group);
        clickElementByLocator(By.xpath("//input[@name=\'submit\']"));
        clickElementByLocator(By.linkText("home page"));

    }

    private void selectGroup(GroupData group) {
       new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void dropdownElementByLocator(By dropdownLocator, By valueLocator) {
        manager.driver.findElement(dropdownLocator).click();
        {
            WebElement dropdown = manager.driver.findElement(dropdownLocator);
            dropdown.findElement(valueLocator).click();
        }
    }

    public void canRemoveContact(ContactData contact) {
        openHomePage();
        clickElementByLocator(By.cssSelector(String.format("input[id='%s']", contact.id())));
        manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }

    public void openHomePage() {
            clickElementByLocator(By.linkText("home"));
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

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var spans = manager.driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr[*]"));
        if (spans.size() > 1) {
            for (int i = 1; i < spans.size(); i++) {
                String lastName = spans.get(i).findElement(By.xpath("./td[2]")).getText();
                String firstName = spans.get(i).findElement(By.xpath("./td[3]")).getText();
                String address = spans.get(i).findElement(By.xpath("./td[4]")).getText();
                var checkbox = spans.get(i).findElement(By.xpath("./td/input[@name=\"selected[]\"]"));
                var id = checkbox.getAttribute("id");
                contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address));
            }
        }
        return contacts;
    }

    public void modifyContact(ContactData testData,int index) {
        openHomePage();
        clickElementByLocator(By.xpath(String.format("//tr[%s]/td[8]",index+2)));
        sendKeys(By.name("firstname"), testData.firstName());
        clickElementByLocator(By.name("update"));
    }
}
