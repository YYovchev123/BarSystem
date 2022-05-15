package models;

public class User {
    private String name;
    private String phone;
    private String pin;
    private UserType type;

    public User(String name, String phone, String pin, UserType type) {
        this.name = name;
        this.phone = phone;
        this.pin = pin;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPin() {
        return pin;
    }

    public UserType getType() {
        return type;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
