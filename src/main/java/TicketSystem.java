//The author of this module code is Kaihua Tian

import java.util.Scanner;

public class TicketSystem<T> {
    private Passenger passenger;
    private Ticket ticket;
    private Flight flight;
    private Scanner in;

    public TicketSystem() {
        this.passenger = new Passenger();
        this.ticket = new Ticket();
        this.flight = new Flight();
        this.in = new Scanner(System.in);
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public boolean validateFlight(Flight flight) {
        if (flight.getFlightID() <= 0) return false;
        if (isNullOrEmpty(flight.getDepartTo())) return false;
        if (isNullOrEmpty(flight.getDepartFrom())) return false;
        if (isNullOrEmpty(flight.getCode())) return false;
        if (isNullOrEmpty(flight.getCompany())) return false;
        if (flight.getDateFrom() == null) return false;
        if (flight.getDateTo() == null) return false;
        if (flight.getAirplane() == null) return false;
        return true;
    }

    public boolean validatePassenger(Passenger passenger) {
        if (isNullOrEmpty(passenger.getFirstName())) return false;
        if (isNullOrEmpty(passenger.getSecondName())) return false;
        if (passenger.getAge() <= 0) return false;
        if (isNullOrEmpty(passenger.getGender())) return false;
        if (isNullOrEmpty(passenger.getEmail())) return false;
        if (isNullOrEmpty(passenger.getPhoneNumber())) return false;
        if (isNullOrEmpty(passenger.getPassport())) return false;
        if (isNullOrEmpty(passenger.getCardNumber())) return false;
        if (passenger.getSecurityCode() <= 0) return false;
        return true;
    }

    public boolean validateTicket(Ticket ticket) {
        //if (ticket.getTicketId() <= 0) return false;
        if (ticket.getPrice() <= 0) return false;
        if (ticket.getFlight() == null || !validateFlight(ticket.getFlight())) return false;
        if (ticket.getPassenger() == null || !validatePassenger(ticket.getPassenger())) return false;
        return true;
    }

    public boolean validateCity(String city) {
        // You can add more city validation logic here, e.g., check against a list of valid cities.
        return city != null && !city.isEmpty();
    }

    public int calculateFinalPrice(Ticket ticket) {
        return (int) ticket.getPrice();
    }

    public void setScanner(Scanner scanner) {
        this.in = scanner;
    }
}
