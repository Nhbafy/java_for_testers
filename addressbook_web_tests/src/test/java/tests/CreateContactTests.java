package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class CreateContactTests extends TestBase {

    @Test
    public void createContact() {
        app.contactHelper().createContact(new ContactData().withAddress("address").withFirstName("sgdsdgsgd").withLastName("lastname"));
    }

}
