// Define the Ticket interface to represent a ticket in the system
interface Ticket {
    String getType();
    double getPrice();
}

// OneDayTicket class
class OneDayTicket implements Ticket {
    private static final String TYPE = "One Day";
    private static final double PRICE = 6.0;

    // Modified constructor to accept no arguments
    public OneDayTicket() {
        // Constructor implementation
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public double getPrice() {
        return PRICE;
    }
}

// WeeklyTicket class
class WeeklyTicket implements Ticket {
    private static final String TYPE = "Weekly";
    private static final double PRICE = 18.0;

    // Modified constructor to accept no arguments
    public WeeklyTicket() {
        // Constructor implementation
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public double getPrice() {
        return PRICE;
    }
}

// MonthlyTicket class
class MonthlyTicket implements Ticket {
    private static final String TYPE = "Monthly";
    private static final double PRICE = 30.0;

    // Modified constructor to accept no arguments
    public MonthlyTicket() {
        // Constructor implementation
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public double getPrice() {
        return PRICE;
    }
}
