package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase {
    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createGroup(GroupData group) {
        openGroupPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void removeGroup() {
        openGroupPage();
        selectGroup();
        removeSelectedGroups();
        returnToGroupPage();
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitModification();
        returnToGroupPage();
    }

    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            clickElementByLocator(By.linkText("groups"));
        }
    }

    public boolean isGroupPresent() {
        openGroupPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        openGroupPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        openGroupPage();
        selectAllGroups();
        removeSelectedGroups();
    }

    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    private void fillGroupForm(GroupData group) {
        sendKeys(By.name("group_name"), group.name());
        sendKeys(By.name("group_header"), group.header());
        sendKeys(By.name("group_footer"), group.footer());
    }

    private void initGroupModification() {
        clickElementByLocator(By.name("edit"));
    }

    private void selectGroup() {
        clickElementByLocator(By.name("selected[]"));
    }

    private void removeSelectedGroups() {
        clickElementByLocator(By.name("delete"));
    }

    private void submitModification() {
        clickElementByLocator(By.name("update"));
    }

    private void returnToGroupPage() {
        clickElementByLocator(By.linkText("group page"));
    }

    private void submitGroupCreation() {
        clickElementByLocator(By.name("submit"));
    }

    private void initGroupCreation() {
        clickElementByLocator(By.name("new"));
    }
}
