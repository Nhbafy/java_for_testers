package model;

public record ContactData(String id,
                          String firstName,
                          String address,
                          String lastName,
                          String home,
                          String mobile,
                          String work,
                          String secondary,
                          String fax,
                          String email,
                          String email2,
                          String email3) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.address, this.lastName, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, this.address, lastName, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, address, this.lastName, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.address, this.lastName, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withMobilePhone(String mobile) {
        return new ContactData(this.id, this.firstName, this.address, this.lastName, this.home, mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withHomePhone(String home) {
        return new ContactData(this.id, this.firstName, this.address, this.lastName, home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withWorkPhone(String work) {
        return new ContactData(this.id, this.firstName, this.address, this.lastName, this.home, this.mobile, work, this.secondary, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withSecondaryPhone(String secondary) {
        return new ContactData(this.id, this.firstName, this.address, this.lastName, this.home, this.mobile, this.work, secondary, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withFax(String fax) {
        return new ContactData(this.id, this.firstName, this.address, this.lastName, this.home, this.mobile, this.work, this.secondary, fax, this.email, this.email2, this.email3);
    }
    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.address, this.lastName, this.home, this.mobile, this.work, this.secondary, this.fax, email, this.email2, this.email3);
    }
    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstName, this.address, this.lastName, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, email2, this.email3);
    }
    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstName, this.address, this.lastName, this.home, this.mobile, this.work, this.secondary, this.fax, this.email, this.email2, email3);
    }

}
