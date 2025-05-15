package tests;

import manager.JdbcHelper;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Utils;

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
        var contactList = app.jdbc().getContactsWithoutGroups();
        if (contactList.size()==0){
            app.hbm().createContact(contactData);
            contactList = app.jdbc().getContactsWithoutGroups();
        }
        app.contact().openHomePage();
        app.contact().selectContact(contactList.get(0));
        app.contact().selectAddToGroup(groupList.get(0));
        app.contact().addToGroup();
        Assertions.assertTrue(JdbcHelper.checkBunch(groupList.get(0), contactList.get(0)), "Связка не добавилась");
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
