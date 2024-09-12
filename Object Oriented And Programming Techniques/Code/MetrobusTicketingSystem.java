import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


public class MetrobusTicketingSystem {
    private static List<Transaction> transactions = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    // Initialize the admins list with admin credentials
    admins.add(new Admin("admin", "admin123"));
    
// Display the main menu and handle user choices
    
    while (true) {
        displayMenu();
        int choice = getChoice();
        switch (choice) {
            case 1:
                // Register new customer
                registerNewCustomer();
                break;
            case 2:
                // Login as customer
                Customer customer = login();
                if (customer != null) {
                    // Successfully logged in
                    customerMenu(customer);
                }
                break;
            case 3:
                // Admin login
                adminLogin();
                break;
            case 4:
                // Exit
                System.out.println("Exiting...");
                return; // Exit the program
            default:
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
        }
    }
}

// Display the main menu options
    private static void displayMenu() {
        System.out.println("----- Metrobus Ticketing System -----");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Admin Login");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

// Get the user's choice from the main menu
    private static int getChoice() {
        int choice = 0;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        return choice;
    }

// Register a new customer
    private static void registerNewCustomer() {
    System.out.println("----- Register New Customer -----");
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    Customer customer = new Customer(username, password); // Pass username and password to the Customer constructor
    customers.add(customer);

    // Display success message
    System.out.println("Account registered successfully!");
}

    
// Customer login process
    private static Customer login() {
        System.out.println("----- Customer Login -----");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Check if the user is a registered customer
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                System.out.println("Login successful as customer: " + username);
                return customer;
            }
        }

        System.out.println("Login failed. Incorrect username or password.");
        return null; // Return null if login fails
    }

// Admin login process
    private static void adminLogin() {
    System.out.println("----- Admin Login -----");
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    // Check if the user is a registered admin
    for (Admin admin : admins) {
        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            System.out.println("Login successful as admin: " + username);
            adminMenu();
            // Return after adminMenu() to prevent further execution in this method
            return;
        }
    }

    System.out.println("Login failed. Incorrect username or password.");
}



// Handle customer menu options
    private static void customerMenu(Customer customer) {
    boolean exit = false;

    while (!exit) {
        displayCustomerMenu();
        int choice = getChoice();
        switch (choice) {
            case 1:
                // Purchase ticket
                purchaseTicket(customer);
                break;
            case 2:
                // View bus operation
                viewBusOperation();
                break;
            case 3:
                // Logout
                exit = true;
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}


// Display the customer menu options
    private static void displayCustomerMenu() {
        System.out.println("----- Customer Menu -----");
        System.out.println("1. Purchase Ticket");
        System.out.println("2. View Bus Operation");
        System.out.println("3. Logout");
        System.out.print("Enter your choice: ");
    }


// Handle admin menu options
    private static void adminMenu() {
        boolean exit = false;

        while (!exit) {
            displayAdminMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    // View all transactions
                    viewAllTransactions();
                    break;
                case 2:
                    exit = true;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


// Display the admin menu options
    private static void displayAdminMenu() {
        System.out.println("----- Admin Menu -----");
        System.out.println("1. View All Transactions");
        System.out.println("2. Logout");
        System.out.print("Enter your choice: ");
    }


// Purchase ticket process
    // Purchase ticket process
private static void purchaseTicket(Customer customer) {
    List<Transaction.TicketPurchase> purchases = new ArrayList<>();
    boolean purchaseMore = true;

    while (purchaseMore) {
        displayAvailableTickets();
        System.out.print("Enter the number of the ticket you want to purchase: ");
        int ticketChoice = getChoice();
        if (ticketChoice < 1 || ticketChoice > 3) {
            System.out.println("Invalid ticket choice. Please enter a number between 1 and 3.");
            continue;
        }
        System.out.print("Enter the quantity: ");
        int quantity = getChoice();
        double ticketPrice = getTicketPrice(ticketChoice);
        double totalCost = ticketPrice * quantity;

        // Confirm the purchase
        boolean confirmed = confirmPurchase(ticketChoice, quantity, totalCost);

        if (confirmed) {
            System.out.println("Ticket(s) added to cart successfully!");
            // Add the ticket purchase to the list of purchases
            purchases.add(new Transaction.TicketPurchase(createTicket(ticketChoice, ticketPrice), quantity, LocalDateTime.now()));
        } else {
            System.out.println("Ticket(s) not added to cart.");
        }

        System.out.print("Would you like to purchase more tickets? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        purchaseMore = response.equals("yes");
    }

    // Display total cost
    double totalCost = calculateTotalCost(purchases);
    System.out.println("Total Cost: " + totalCost);

    // Payment process
    while (true) {
        System.out.println("Please select payment type:");
        System.out.println("1. Debit");
        System.out.println("2. Credit");
        System.out.println("3. Back");
        System.out.print("Enter your choice: ");
        int paymentChoice = getChoice();

        String paymentType;
        switch (paymentChoice) {
            case 1:
                paymentType = "Debit";
                break;
            case 2:
                paymentType = "Credit";
                break;
            case 3:
                return; // Go back to the customer menu
            default:
                System.out.println("Invalid choice. Please select a valid payment method.");
                continue; // Continue the loop to prompt for valid input
        }

        // Create a new Payment object
        Payment payment = new Payment();

        // Create a new Transaction object with the selected paymentType and list of purchases
        Transaction transaction = new Transaction(purchases, paymentType, payment); // Corrected line

        switch (paymentChoice) {
            case 1:
                System.out.print("Enter payment amount: ");
                double debitAmount = Double.parseDouble(scanner.nextLine());
                if (debitAmount >= totalCost) {
                    System.out.println("Payment successful!");
                    double debitChange = debitAmount - totalCost;
                    System.out.println("Change: " + debitChange);

                    // Add transaction
                    transactions.add(transaction);

                    // Generate and display receipt
                    generateReceipt(customer, purchases, totalCost, paymentType);
                    return; // Exit the payment loop
                } else {
                    System.out.println("Insufficient funds. Please retry or choose another payment method.");
                }
                break;
            case 2:
                System.out.print("Enter payment amount: ");
                double creditAmount = Double.parseDouble(scanner.nextLine());
                if (creditAmount >= totalCost) {
                    System.out.println("Payment successful!");
                    double creditChange = creditAmount - totalCost;
                    System.out.println("Change: " + creditChange);

                    // Add transaction
                    transactions.add(transaction);

                    // Generate and display receipt
                    generateReceipt(customer, purchases, totalCost, paymentType);
                    return; // Exit the payment loop
                } else {
                    System.out.println("Insufficient funds. Please retry or choose another payment method.");
                }
                break;
        }
    }
}




    // In the createTicket() method of MetrobusTicketingSystem class
private static Ticket createTicket(int ticketChoice, double ticketPrice) {
    switch (ticketChoice) {
        case 1:
            return new OneDayTicket(); // Create OneDayTicket without any arguments
        case 2:
            return new WeeklyTicket(); // Create WeeklyTicket without any arguments
        case 3:
            return new MonthlyTicket(); // Create MonthlyTicket without any arguments
        default:
            return null; // Invalid ticket choice
    }
}



// Display available tickets
    private static void displayAvailableTickets() {
        System.out.println("Available Tickets:");
        System.out.println("1. One Day");
        System.out.println("2. Weekly");
        System.out.println("3. Monthly");
    }

    private static double getTicketPrice(int ticketChoice) {
        switch (ticketChoice) {
            case 1:
                return 06.0; // Price for One Day ticket
            case 2:
                return 18.0; // Price for Weekly ticket
            case 3:
                return 30.0; // Price for Monthly ticket
            default:
                return 0.0; // Return 0.0 for invalid ticket choice
        }
    }

    private static boolean confirmPurchase(int ticketChoice, int quantity, double totalCost) {
        // Display the details of the selected ticket, quantity, and total cost
        String ticketType;
        switch (ticketChoice) {
            case 1:
                ticketType = "One Day";
                break;
            case 2:
                ticketType = "Weekly";
                break;
            case 3:
                ticketType = "Monthly";
                break;
            default:
                ticketType = "";
        }
        System.out.println("Ticket Type: " + ticketType);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Cost: " + totalCost);

        // Ask for confirmation
        System.out.print("Confirm purchase? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        // Check user's response
        return confirmation.equals("yes");
    }

    private static double calculateTotalCost(List<Transaction.TicketPurchase> purchases) {
        double totalCost = 0;
        for (Transaction.TicketPurchase purchase : purchases) {
            totalCost += purchase.getTotalCost();
        }
        return totalCost;
    }

    private static void generateReceipt(Customer customer, List<Transaction.TicketPurchase> purchases, double totalCost, String paymentType) {
    StringBuilder receiptBuilder = new StringBuilder();
    LocalDateTime dateTime = LocalDateTime.now();

    // Header
    receiptBuilder.append("--------------- Metrobus Receipt ---------------\n");
    receiptBuilder.append("Date and Time: ").append(dateTime).append("\n");
    receiptBuilder.append("Customer: ").append(customer.getUsername()).append("\n");
    receiptBuilder.append("------------------------------------------------\n");
    receiptBuilder.append("For More Information. Please Contact Us At:\n");
    receiptBuilder.append("Contact Number: ").append("+1234567890").append("\n"); // Add a contact number
    receiptBuilder.append("Email: ").append("MetrobusServices@gmail.com").append("\n"); // Add an email
    receiptBuilder.append("------------------------------------------------\n");
    receiptBuilder.append("Payment Type: ").append(paymentType).append("  **** **** ****\n"); // Add payment type
    
    // Table header
    receiptBuilder.append(String.format("%-20s %-10s %-10s %-10s\n", "Ticket Type", "Price", "Quantity", "Total"));

    // Table body
    for (Transaction.TicketPurchase purchase : purchases) {
        Ticket ticket = purchase.getTicket();
        receiptBuilder.append(String.format("%-20s %-10.2f %-10d %-10.2f\n", ticket.getType(), ticket.getPrice(), purchase.getQuantity(), purchase.getTotalCost()));
    }
    
    
    // Total cost
    receiptBuilder.append("\nTotal Cost: ").append(totalCost).append("\n");
    receiptBuilder.append("------------------------------------------------\n");

    // Thank you message
    receiptBuilder.append("\nThank you for purchasing tickets from Metrobus!\n\n\n");

    // Display receipt
    System.out.println(receiptBuilder.toString());
}




    private static void viewAllTransactions() {
    System.out.println("----- All Transactions -----");
    System.out.println("+-------------------------+-------------------------+-------------------------+---------------+--------------+");
    System.out.println("| Date and Time           | Ticket Type             | Quantity                | Payment Type  | Total Cost   |");
    System.out.println("+-------------------------+-------------------------+-------------------------+---------------+--------------+");

    for (Transaction transaction : transactions) {
        LocalDateTime dateTime = transaction.getDateTime();
        for (Transaction.TicketPurchase purchase : transaction.getPurchases()) {
            Ticket ticket = purchase.getTicket();
            System.out.printf("| %-23s | %-23s | %-23d | %-13s | %-12.2f |\n", dateTime, ticket.getType(), purchase.getQuantity(), transaction.getPaymentType(), purchase.getTotalCost());
            dateTime = null; // Only display date and time for the first row
        }
    }

    System.out.println("+-------------------------+-------------------------+-------------------------+---------------+--------------+");
}








    private static void viewBusOperation() {
    System.out.println("----- Bus Operation -----");
    System.out.println("Route: Penampang to Alamesra and Back");
    System.out.println("Departure Times: ");
    System.out.println("Morning: 7:00 AM, 8:30 AM, 10:00 AM");
    System.out.println("Afternoon: 12:00 PM, 2:00 PM, 4:00 PM");
    System.out.println("Evening: 6:00 PM, 7:30 PM, 9:00 PM");
    System.out.println();

    System.out.println("Route: Kepayan to Alamesra and Back");
    System.out.println("Departure Times: ");
    System.out.println("Morning: 6:45 AM, 8:15 AM, 9:45 AM");
    System.out.println("Afternoon: 11:45 AM, 1:45 PM, 3:45 PM");
    System.out.println("Evening: 5:45 PM, 7:15 PM, 8:45 PM");
    System.out.println();

    System.out.println("Route: Inanam to Alamesra and Back");
    System.out.println("Departure Times: ");
    System.out.println("Morning: 6:30 AM, 8:00 AM, 9:30 AM");
    System.out.println("Afternoon: 11:30 AM, 1:30 PM, 3:30 PM");
    System.out.println("Evening: 5:30 PM, 7:00 PM, 8:30 PM");
    System.out.println();

    System.out.println("Route: Town to Alamesra and Back");
    System.out.println("Departure Times: ");
    System.out.println("Morning: 7:15 AM, 8:45 AM, 10:15 AM");
    System.out.println("Afternoon: 12:15 PM, 2:15 PM, 4:15 PM");
    System.out.println("Evening: 6:15 PM, 7:45 PM, 9:15 PM");
    System.out.println();
}




}

