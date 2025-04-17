package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RemoveContactTests extends TestBase {

    @Test
    public void removeContact() {
        if (!app.contact().isContactPresent()) {
            app.contact().createContact(new ContactData().withAddress("address").withFirstName("firstname").withLastName("lastname"));
        }
        List<ContactData> oldContacts = app.contact().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contact().canRemoveContact(oldContacts.get(index));
        List<ContactData> newContacts = app.contact().getList();
        List<ContactData> expectedResult = new ArrayList<>(oldContacts);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedResult.remove(index);
        expectedResult.sort(compareById);

        Assertions.assertEquals(newContacts, expectedResult);
    }

    @Test
    void removeAllContacts() {
        if (!app.contact().isContactPresent()) {
            app.contact().createContact(new ContactData().withAddress("address").withFirstName("firstname").withLastName("lastname"));
        }
        app.contact().openHomePage();
        app.contact().removeAllContacts();
        app.contact().openHomePage();
        Assertions.assertEquals(0, app.contact().getCount());
    }
}
