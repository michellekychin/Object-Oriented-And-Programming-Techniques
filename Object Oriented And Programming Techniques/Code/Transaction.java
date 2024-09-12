import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Transaction {
    private List<TicketPurchase> purchases;
    private String paymentType;
    private LocalDateTime dateTime;
    private Payment payment;
    private List<Ticket> tickets;

    // Constructor with paymentType argument
    public Transaction(List<TicketPurchase> purchases, String paymentType, Payment payment) {
        this.purchases = purchases;
        this.paymentType = paymentType;
        this.dateTime = LocalDateTime.now();
        this.payment = payment;
        this.tickets = new ArrayList<>(); // Initialize the tickets list
    }

    // Getter for purchases, dateTime, paymentType, and payment
    public List<TicketPurchase> getPurchases() {
        return purchases;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Payment getPayment() {
        return payment;
    }

    // Getter for tickets
    public List<Ticket> getTickets() {
        return tickets;
    }

    // Method to add a ticket to the transaction
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    // Method to remove a ticket from the transaction
    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Date and Time: ").append(dateTime).append("\n");
        builder.append("Payment Type: ").append(paymentType).append("\n");
        for (TicketPurchase purchase : purchases) {
            builder.append(purchase).append("\n");
        }
        return builder.toString();
    }

    // Inner class representing a ticket purchase
    static class TicketPurchase {
        private Ticket ticket;
        private int quantity;
        private LocalDateTime dateTime;

        public TicketPurchase(Ticket ticket, int quantity, LocalDateTime dateTime) {
            this.ticket = ticket;
            this.quantity = quantity;
            this.dateTime = dateTime;
        }

        public Ticket getTicket() {
            return ticket;
        }

        public int getQuantity() {
            return quantity;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public double getTotalCost() {
            return ticket.getPrice() * quantity;
        }

        @Override
        public String toString() {
            return "Ticket Type: " + ticket.getType() +
                    ", Quantity: " + quantity +
                    ", Total Cost: " + getTotalCost();
        }
    }
}
