public class Payment {
    private double amountPaid;
    private boolean isPaid;

    // Constructor method for initializing a Payment object
    public Payment() {
        this.amountPaid = 0;
        this.isPaid = false;
    }

    // Getter for amountPaid
    public double getAmountPaid() {
        return amountPaid;
    }

    // Method to make payment
    public void makePayment(double amount) {
        if (amount <= 0) {  // Check if the provided amount is less than or equal to 0
            System.out.println("Invalid payment amount.");
        } else {
            amountPaid += amount;
            System.out.println("Payment of RM " + amount + " received.");
            isPaid = true;  // Set isPaid to true indicating the payment is made
        }
    }

    // Getter for isPaid
    public boolean getIsPaid() {
        return isPaid;
    }
}