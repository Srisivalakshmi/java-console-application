package BMS;

import java.util.HashMap;
import java.util.ArrayList;

class Screen {
    private final String name;
    private final int seats;
    private final HashMap<Character, ArrayList<String>> seatingArrangement;

    public Screen(String name, int seats, HashMap<Character, ArrayList<String>> seatingArrangement) {
        this.name = name;
        this.seats = seats;
        this.seatingArrangement = seatingArrangement;
    }

    public String getName() {
        return name;
    }

    public int getSeats() {
        return seats;
    }

    public HashMap<Character, ArrayList<String>> getSeatingArrangement() {
        return seatingArrangement;
    }
}