package model;

public record ContactData (String firstName,String lastName, String address){
    public ContactData() {
        this("", "", "");
    }

    public  ContactData withFirstName (String firstName){return new ContactData(firstName, this.lastName, this.address);}
    public  ContactData withLastName (String lastName){return new ContactData(this.firstName, lastName, this.address);}
    public  ContactData withAddress (String address){return new ContactData(this.firstName, this.lastName, address);}
}
