package caseMD2.model;

public class User {
    private long id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;

    public User() {

    }

    public User(String username, String password, String name, String phone, String email, String address) {
        this.id = System.currentTimeMillis()/1000;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public User(String raw) {
        String[] strings = raw.split(",");
        this.id = Long.parseLong(strings[0].trim());
        this.username = strings[1].trim();
        this.password = strings[2].trim();
        this.name = strings[3].trim();
        this.phone = strings[4].trim();
        this.email = strings[5].trim();
        this.address = strings[6].trim();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return id + ", " + username + ", " + password + ", " + name + ", " + phone + ", " + email + ", " + address;

    }
}
