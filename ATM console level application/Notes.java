package Notes;
public class Notes {
    private static int note;// Final variable to store the value of the note
    private int count;// Variable to store count,how many notes of this type are available.

    // Constructor to initialize Notes.Notes object with note and count
    protected Notes(int note, int count) {
        Notes.note = note;// Assigns the note passed as parameter to static variable.
        this.count = count;// Assigns the count passed as parameter to static variable.
    }

    //getter to note
    public static int getNote() {
        return note;
    }

    //getter to count
    public int getCount() {
        return count;
    }

    //setter to update count
    public void setCount(int count) {
        this.count = count;// to set this var as count
    }

    //getter for denomination
    public int getDenomination() {
        return note;
    }
}
