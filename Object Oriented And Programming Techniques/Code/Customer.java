import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Ticket> purchasedTickets;

    public Customer(String username, String password) {
        super(username, password);
        this.purchasedTickets = new ArrayList<>();
    }
    
    public List<Ticket> getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(List<Ticket> purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }

    public void addPurchasedTicket(Ticket ticket) {
        purchasedTickets.add(ticket);
    }

    public void removePurchasedTicket(Ticket ticket) {
        purchasedTickets.remove(ticket);
    }

    public void displayPurchasedTickets() {
        System.out.println("Purchased Tickets:");
        for (Ticket ticket : purchasedTickets) {
            System.out.println(ticket.getType());
        }
    }
}
