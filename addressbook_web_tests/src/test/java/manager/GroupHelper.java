package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase {
    private final ApplicationManager manager;

    public GroupHelper(ApplicationManager manager)
    {
        super(manager);
        this.manager=manager;
    }

    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            clickElementByLinkText("groups");
        }
    }

    public void removeGroup() {
        openGroupPage();
        clickElementByName("selected[]");
        clickElementByName("delete");
        clickElementByLinkText("group page");
    }

    public boolean isGroupPresent() {
        openGroupPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createGroup(GroupData group) {
        openGroupPage();
        clickElementByName("new");
        clickElementByName("group_name");
        sendKeys("group_name", group.name());
        clickElementByName("group_header");
        sendKeys("group_header", group.header());
        clickElementByName("group_footer");
        sendKeys("group_footer", group.footer());
        clickElementByName("submit");
        clickElementByLinkText("group page");
    }

}
