package manager;

import model.ContactData;
import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public static boolean checkBunch(GroupData group, ContactData contact) {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(String.format("SELECT * FROM address_in_groups WHERE id = \"%s\" AND group_id = \"%s\"", contact.id(), group.id()))) {
            return result.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createContactGroupBunch(GroupData group, ContactData contact) {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root",""))
        {
            String sql = String.format("INSERT INTO address_in_groups VALUES (0,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")",
                    contact.id(), group.id(),java.time.LocalDateTime.now(),java.time.LocalDateTime.now(),java.time.LocalDateTime.now());
            conn.createStatement().executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list"))
        {
            while (result.next())
            {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public void checkConsistency() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT * FROM address_in_groups AG LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL"))
        {
            if (result.next())
            {
                throw new IllegalStateException("DB is corrupted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ContactData> getContactsWithoutGroups() {
        var idList = new ArrayList<ContactData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT *, ab.id as contactId FROM address_in_groups aig right join addressbook ab on ab.id = aig.id where aig.id is null")) {
            while (result.next()) {
                idList.add(new ContactData()
                        .withId(result.getString("contactId")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idList;
    }
}
