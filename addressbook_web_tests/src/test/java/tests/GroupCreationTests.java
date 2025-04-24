package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {
    public static List<GroupData> groupProvider() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>() {
        });
        return new ArrayList<>(value);
    }


    public static List<GroupData> negativeGroupProvider() {
        List<GroupData> result = new ArrayList<>(List.of(new GroupData("", "", "", "ggg'")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateGroup(GroupData group) {
        List<GroupData> oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        List<GroupData> newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.add(group.withId(newGroups.get(newGroups.size()-1).id()).withFooter("").withHeader(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newGroups);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        List<GroupData> oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        List<GroupData> newGroups = app.groups().getList();
        Assertions.assertEquals(oldGroups, newGroups);
    }
}
