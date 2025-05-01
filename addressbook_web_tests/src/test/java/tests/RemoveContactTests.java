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
        if (app.hbm().getContactsCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstname", "address", "lastname"));
        }
        List<ContactData> oldContacts = app.hbm().getContactsList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contact().canRemoveContact(oldContacts.get(index));
        List<ContactData> newContacts = app.hbm().getContactsList();
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
        if (app.hbm().getContactsCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstname", "address", "lastname"));
        }
        app.contact().openHomePage();
        app.contact().removeAllContacts();
        app.contact().openHomePage();
        Assertions.assertEquals(0, app.hbm().getContactsCount());
    }
}
