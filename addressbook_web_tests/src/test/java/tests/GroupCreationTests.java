package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase {
    public static List<GroupData> groupProvider() {
        List<GroupData> result = new ArrayList<>();
        for (var name : List.of("Group name", "")) {
            for (var header : List.of("Group header", "")) {
                for (var footer : List.of("Group footer", "")) {
                    result.add(new GroupData(name, header, footer));
                }
                ;
            }
        }
        for (int i = 1; i < 6; i++) {
            result.add(new GroupData(randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
    }


    public static List<GroupData> negativeGroupProvider() {
        List<GroupData> result = new ArrayList<>(List.of(new GroupData("ggg'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateGroup(GroupData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount, newGroupCount);
    }
}
