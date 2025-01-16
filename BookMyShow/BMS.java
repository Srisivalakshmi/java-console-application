package BMS;

import java.util.ArrayList;
import java.util.HashMap;

class BMS {
    private static final ArrayList<User> userList = new ArrayList<>();
    private static final ArrayList<Admin> adminList = new ArrayList<>();
    private static final ArrayList<Theatre> theatreList = new ArrayList<>();
    private static final HashMap<String, Theatre> theatreMap = new HashMap<>();

    public static HashMap<String, Theatre> getTheatreMap() {
        return theatreMap;
    }

    public static ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static ArrayList<Theatre> getTheatreList() {
        return theatreList;
    }
}
