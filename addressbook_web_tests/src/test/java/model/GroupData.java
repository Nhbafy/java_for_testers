package model;

public record GroupData(String id, String header, String footer, String name) {
    public GroupData() {
        this("", "", "", "");
    }

    public GroupData withId(String id) {
        return new GroupData(id, this.name, this.header, this.footer);
    }

    public GroupData withName(String name) {return new GroupData(this.id, this.header, this.footer, name);
    }

    public GroupData withHeader(String header) {
        return new GroupData(this.id, header, this.footer, this.name);
    }

    public GroupData withFooter(String footer) {
        return new GroupData(this.id, this.header, footer, this.name);
    }
}