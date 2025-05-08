package tests;

import manager.JdbcHelper;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
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
        var groupList = app.hbm().getGroupList();
        var contactList = app.hbm().getContactsList();
        ContactData contact = null;
        GroupData group = null;
        for (GroupData value : groupList) {
            for (ContactData data : contactList) {
                if (!JdbcHelper.checkBunch(value, data)) {
                    group = value;
                    contact = data;
                    break;
                }
            }
            if (contact != null) break;
        }
        if (contact == null) {
            app.hbm().createContact(contactData);
            groupList = app.hbm().getGroupList();
            contactList = app.hbm().getContactsList();
            for (GroupData value : groupList) {
                for (ContactData data : contactList) {
                    if (!JdbcHelper.checkBunch(value, data)) {
                        group = value;
                        contact = data;
                        break;
                    }
                }
                if (contact != null) break;
            }
        }
        app.contact().openHomePage();
        app.contact().selectContact(contact);
        app.contact().selectAddToGroup(group);
        app.contact().addToGroup();
        Assertions.assertTrue(JdbcHelper.checkBunch(group, contact), "Связка не добавилась");

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
        if (!JdbcHelper.checkBunch(group, contact)) {
            JdbcHelper.createContactGroupBunch(group,contact);
        }
        app.contact().openHomePage();
        app.contact().selectGroupFilter(group);
        app.contact().selectContact(contact);
        app.contact().removeFromGroup();
        Assertions.assertFalse(JdbcHelper.checkBunch(group, contact), "Связка не удалилась");
    }
}
