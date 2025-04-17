package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RemoveContactTests extends TestBase {

    @Test
    public void removeContact() {
        if (!app.contact().isContactPresent()) {
            app.contact().createContact(new ContactData().withAddress("address").withFirstName("firstname").withLastName("lastname"));
        }
        app.contact().canRemoveContact();
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
