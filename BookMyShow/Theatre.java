package BMS;

import java.util.HashMap;

class Theatre {
    private final String name;
    private final String location;
    private final HashMap<String, Screen> screens;

    public Theatre(String name, HashMap<String, Screen> screens, String location) {
        this.name = name;
        this.screens = screens;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public HashMap<String, Screen> getScreens() {
        return screens;
    }
}
