package manager.hbm;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import utils.Utils;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String address;

    public String middlename = Utils.randomString(10);
    public String nickname = Utils.randomString(10);
    public String company = Utils.randomString(10);
    public String title = Utils.randomString(10);
    public String home = Utils.randomString(10);
    public String mobile = Utils.randomString(10);
    public String work = Utils.randomString(10);
    public String fax = Utils.randomString(10);
    public String phone2 = Utils.randomString(10);
    public String email = Utils.randomString(10);
    public String email2 = Utils.randomString(10);
    public String email3 = Utils.randomString(10);
    public String homepage = Utils.randomString(10);


    public ContactRecord(){}
    public ContactRecord(int id, String firstname, String lastname, String address){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }
}
