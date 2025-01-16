package BMS;

import java.util.ArrayList;
import java.util.HashMap;

class Utility {
    public static HashMap<Character, ArrayList<String>> generateSeatingPatterns(int noOfSeats, String gridPattern) {
        String[] gridSegments = gridPattern.split("\\*");
        int totalSeatsPerRow = 0;

        for (String segment : gridSegments) {
            totalSeatsPerRow += Integer.parseInt(segment);
        }

        if (noOfSeats % totalSeatsPerRow != 0) {
            System.out.println("Invalid grid pattern or seat count.");
            return null;
        }

        HashMap<Character, ArrayList<String>> seatingArrangement = new HashMap<>();
        char rowLabel = 'A';

        while (noOfSeats > 0) {
            ArrayList<String> row = new ArrayList<>();
            for (String segment : gridSegments) {
                int seatsInSegment = Integer.parseInt(segment);
                for (int i = 0; i < seatsInSegment; i++) {
                    row.add("<>");
                }
                row.add("_");
            }
            row.remove(row.size() - 1);
            seatingArrangement.put(rowLabel++, row);
            noOfSeats -= totalSeatsPerRow;
        }

        return seatingArrangement;
    }
}