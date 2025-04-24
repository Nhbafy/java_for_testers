package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.GroupData;
import utils.Utils;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CreateContactTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        return new ArrayList<>(value);
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void createContact(ContactData contact) {
        List<ContactData> oldContacts = app.contact().getList();
        app.contact().createContact(contact);
        List<ContactData> newContacts = app.contact().getList();
        List<ContactData> expectedResult = new ArrayList<>(oldContacts);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        ContactData lastElement = newContacts.get(newContacts.size() - 1);
        expectedResult.add(contact.withId(lastElement.id())
                .withFirstName(lastElement.firstName())
                .withLastName(lastElement.lastName())
                .withAddress(lastElement.address()));
        expectedResult.sort(compareById);
        Assertions.assertEquals(newContacts, expectedResult);
    }
}
