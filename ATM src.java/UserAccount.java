public class UserAccount {
        private String userId;
        private String pin;
        private double balance;

        public UserAccount(String userId, String pin, double balance) {
            this.userId = userId;
            this.pin = pin;
            this.balance = balance;
        }

        public String getUserId() {
            return userId;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && balance >= amount) {
                balance -= amount;
            }
        }
    }


