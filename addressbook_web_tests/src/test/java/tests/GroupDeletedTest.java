package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class GroupDeletedTest extends TestBase {
    @Test
    public void canRemoveGroup() throws NoSuchElementException {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "header", "footer", "name"));
        }
        int groupCount = app.groups().getCount();
        List<GroupData> oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup((oldGroups.get(index)));
        List<GroupData> newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void canRemoveAllGroups() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "header", "footer", "name"));
            ;
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }

}
