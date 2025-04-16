package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class GroupDeletedTest extends TestBase {
    @Test
    public void canRemoveGroup() throws NoSuchElementException {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("name", "header", "footer"));
            ;
        }
        int groupCount = app.groups().getCount();
        app.groups().removeGroup();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);
    }

    @Test
    public void canRemoveAllGroups() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("name", "header", "footer"));
            ;
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }

}
