package BMS;

public class Admin {
    private final String name;
    private final int password;

    public Admin(String name, int password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getPassword() {
        return password;
    }
}
