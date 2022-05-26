package caseMD2.model;

public class Order {
    private long id;
    private String name;
    private String phone;
    private String address;

    private Order() {
    }

    public Order (String record){
        String[] field = record.split(",");
        id = Long.parseLong(field[0].trim());
        name = field[1].trim();
        phone = field[2].trim();
        address = field[3].trim();
    }

    public Order(long id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return address;
    }

    public void setEmail(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return id + "," + name + "," + phone + "," + address;
    }
}
