package BMS;

class User {
    private final String name;
    private final int password;

    public User(String name, int password) {
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

