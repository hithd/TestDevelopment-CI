public class Ticket {
    private int ticketId;
    private double price;
    Flight flight;
    private boolean classVip;
    private boolean status;
    Passenger passenger;
    private static final double SERVICE_TAX_RATE = 0.12;

    public Ticket(int ticket_id, double price, Flight flight, boolean classVip, Passenger passenger) {
        this.ticketId = ticket_id;
        this.flight = flight;
        this.classVip = classVip;
        this.status = false;
        this.passenger = passenger;
        setPrice(price);
    }

    public Ticket() {
        // Default constructor
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicket_id(int ticketId) {
        this.ticketId = ticketId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        this.price = price;
        saleByAge(passenger.getAge());
        serviceTax();
    }

    public void saleByAge(int age) {
        if (age < 15) {
            price -= price * 0.5; // 50% sale for children under 15
        } else if (age >= 60) {
            price = 0; // 100% sale for elder people
        }
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean getClassVip() {
        return classVip;
    }

    public void setClassVip(boolean classVip) {
        this.classVip = classVip;
    }

    public boolean ticketStatus() {
        return status;
    }

    public void setTicketStatus(boolean status) {
        this.status = status;
    }

    public void serviceTax() {
        this.price *= (1 + SERVICE_TAX_RATE);
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    // New method to get explicit 'True' or 'False' status
    public String getExplicitStatus() {
        return status ? "True" : "False";
    }

    @Override
    public String toString() {
        return "Ticket{" + '\n' +
                "Price=" + String.format("%.2f", getPrice()) + " KZT, " + '\n' +
                getFlight() + '\n' +
                "Vip status=" + getClassVip() + '\n' +
                getPassenger() + '\n' +
                "Ticket was purchased=" + getExplicitStatus() + "\n}";
    }
}