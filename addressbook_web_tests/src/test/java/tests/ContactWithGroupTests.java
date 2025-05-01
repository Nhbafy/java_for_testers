package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ContactWithGroupTests extends TestBase {

    @Test
    void addContactToGroup() {
        var contactData = new ContactData()
                .withFirstName(Utils.randomString(10))
                .withLastName(Utils.randomString(10))
                .withAddress(Utils.randomString(10));
        var groupData = new GroupData()
                .withName(Utils.randomString(10))
                .withHeader(Utils.randomString(10))
                .withFooter(Utils.randomString(10));
        if (app.hbm().getContactsCount() == 0) {
            app.hbm().createContact(contactData);
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(groupData);
        }
        var group = app.hbm().getGroupList().get(0);
        var contact = app.hbm().getContactsList().get(0);
        app.contact().openHomePage();
        app.contact().selectContact(contact);
        app.contact().selectAddToGroup(group);
        app.contact().addToGroup();

        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(String.format("SELECT * FROM address_in_groups WHERE id = \"%s\" AND group_id = \"%s\"", contact.id(), group.id()))) {
            if (!result.next()) {
                throw new IllegalStateException("Связка не добавилась");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void removeContactFromGroup() {
        var contactData = new ContactData()
                .withFirstName(Utils.randomString(10))
                .withLastName(Utils.randomString(10))
                .withAddress(Utils.randomString(10));
        var groupData = new GroupData()
                .withName(Utils.randomString(10))
                .withHeader(Utils.randomString(10))
                .withFooter(Utils.randomString(10));
        if (app.hbm().getContactsCount() == 0) {
            app.hbm().createContact(contactData);
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(groupData);
        }

        var group = app.hbm().getGroupList().get(0);
        var contact = app.hbm().getContactsList().get(0);
        app.contact().openHomePage();
        app.contact().selectGroupFilter(group);
        app.contact().selectContact(contact);
        app.contact().removeFromGroup();

        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(String.format("SELECT * FROM address_in_groups WHERE id = \"%s\" AND group_id = \"%s\"", contact.id(), group.id()))) {
            if (result.next()) {
                throw new IllegalStateException("Связка не добавилась");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
