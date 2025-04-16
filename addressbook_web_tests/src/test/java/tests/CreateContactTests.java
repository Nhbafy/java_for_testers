package tests;

import model.ContactData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class CreateContactTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var address : List.of("address", "")) {
            for (var firstName : List.of("first name", "")) {
                for (var lastName : List.of("last name", "")) {
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
        app.contact().createContact(contact);
    }

}
