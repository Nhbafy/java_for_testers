package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class GroupDeletedTest extends TestBase {
    @Test
    public void canRemoveGroup() throws NoSuchElementException {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("name", "header", "footer"));;
        }
        app.groups().removeGroup();
    }

}
