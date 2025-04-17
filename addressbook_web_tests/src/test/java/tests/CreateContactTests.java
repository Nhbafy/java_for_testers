package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class CreateContactTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var address : List.of("address", "")) {
            for (var firstName : List.of("first_name", "")) {
                for (var lastName : List.of("last_name", "")) {
                    result.add(new ContactData().withAddress(address).withFirstName(firstName).withLastName(lastName));
                }
            }
        }
        for (int i = 1; i < 6; i++) {
            result.add(new ContactData().withAddress(randomString(i * 5)).withFirstName(randomString(i * 5)).withLastName(randomString(i * 5)));
        }
        return result;
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
