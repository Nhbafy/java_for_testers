package model;

public record ContactData(String id, String firstName, String address, String lastName) {
    public ContactData() {
        this("", "", "", "");
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.address, this.lastName);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, this.address, lastName);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, address, this.lastName);
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.address, this.lastName);
    }

}
