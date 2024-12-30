public class Transaction {
    private String performedBy;
    private String type;
    private double amount;

    public Transaction(String performedBy, String type, double amount) {
        this.performedBy = performedBy;
        this.type = type;
        this.amount = Double.parseDouble(String.valueOf(amount));
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

}
