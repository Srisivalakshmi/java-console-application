public class Transaction {
    private String performedBy; // var to store that performed by either admin or user
    private String type; // var to store type of transaction
    private double amount; // var to store amount

    // constructor to initialize transaction objects
    public Transaction(String performedBy, String type, double amount) {
        this.performedBy = performedBy;// Assigns the performed by passed as parameter to static variable.
        this.type = type;// Assigns the type passed as parameter to static variable.
        this.amount = Double.parseDouble(String.valueOf(amount));// Assigns the amount passed as parameter to static variable.
    }

    //getter to performed by
    public String getPerformedBy() {
        return performedBy;
    }

    //getter to get type
    public String getType() {
        return type;
    }

    //getter to get amount
    public double getAmount() {
        return amount;
    }
}
