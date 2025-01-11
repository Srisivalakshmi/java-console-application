public class Transaction {

    private final String performedBy;// A private final field to store the name or ID of the person who performed the transaction (user or admin).
    private final String type;// A private final field to store the type of transaction (e.g., "withdraw", "deposit").
    private final double amount;// A private final field to store the amount involved in the transaction.

    // Constructor to initialize transaction object
    public Transaction(String performedBy, String type, double amount) {
        // Constructor to initialize the Transaction object with the specified values.

        this.performedBy = performedBy;// Assigning the value of performedBy passed as a parameter to the class's performedBy field.
        this.type = type;// Assigning the value of type passed as a parameter to the class's type field.
        this.amount = amount;// Assigning the value of amount passed as a parameter to the class's amount field.
    }

    // Getter for performedBy
    public String getPerformedBy() {// A public method to retrieve the value of the performedBy field.
        return performedBy;// Returning the value of the performedBy field.
    }

    // Getter for type
    public String getType() {// A public method to retrieve the value of the type field.
        return type;// Returning the value of the type field.
    }

    // Getter for amount
    public double getAmount() {// A public method to retrieve the value of the amount field.
        return amount;// Returning the value of the amount field.
    }
}
