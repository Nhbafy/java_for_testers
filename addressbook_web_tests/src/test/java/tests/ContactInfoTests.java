package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactsList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contact().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testInfo() {
        if (app.hbm().getContactsCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(Utils.randomString(10))
                    .withAddress(Utils.randomString(10))
                    .withLastName(Utils.randomString(10)));
        }
        var contacts = app.hbm().getContactsList();
        var expectedPhones = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contact().getPhones(contacts.get(0));
        Assertions.assertEquals(expectedPhones.get(contacts.get(0).id()), phones, "incorrect phones in main");

        var expectedAddress = contacts.get(0).address();
        var address = app.contact().getAddress(contacts.get(0));
        Assertions.assertEquals(expectedAddress, address, "incorrect address in main");

        var expectedEmails = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var emails = app.contact().getEmails(contacts.get(0));
        Assertions.assertEquals(expectedEmails.get(contacts.get(0).id()), emails, "incorrect emails in main");

        app.contact().compareRows(contacts.get(0));

    }
}
