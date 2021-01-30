package mm.etopup.com.database.entity;

public class Contact {
    String name;
    String phone;

    public Contact(String name,String phone)
    {
        this.name = name;
        this.phone = phone;
    }

    public Contact(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
