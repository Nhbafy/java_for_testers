package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class RemoveContactTests extends TestBase {

    @Test
    public void removeContact() {
        if (!app.contactHelper().isContactPresent()) {
            app.contactHelper().createContact(new ContactData().withAddress("address").withFirstName("firstname").withLastName("lastname"));
        }
        app.contactHelper().openHomePage();
        app.contactHelper().canRemoveContact();
    }

}
