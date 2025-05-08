package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    public void modifyContact() {
        if (app.hbm().getContactsCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstname", "address", "lastname", "", "", "", "", "", "", "", ""));
        }
        app.contact().openHomePage();
        List<ContactData> oldContacts = app.hbm().getContactsList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("11111111").withAddress(oldContacts.get(index).address()).withLastName(oldContacts.get(index).lastName());
        app.contact().modifyContact(testData, index);
        app.contact().openHomePage();
        var newContacts = app.contact().getList();
        var expectedResult = new ArrayList<>(oldContacts);
        expectedResult.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedResult.sort(compareById);
        Assertions.assertEquals(newContacts, expectedResult);
    }
}
