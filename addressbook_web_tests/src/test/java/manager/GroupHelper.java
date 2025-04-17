package manager;

import model.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

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

    public void removeGroup(GroupData group) {
        openGroupPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupPage();
    }

    public void modifyGroup(GroupData group,GroupData modifiedGroup) {
        openGroupPage();
        selectGroup(group);
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

    private void selectGroup(GroupData group) {
        clickElementByLocator(By.cssSelector(String.format("input[value='%s']",group.id())));
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

    public List<GroupData> getList() {
        openGroupPage();
        var groups = new ArrayList<GroupData>();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        for(var span:spans){
            var name = span.getText();
            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
