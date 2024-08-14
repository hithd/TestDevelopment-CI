//The author of this module code is Kaihua Tian

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Timestamp;

public class TicketCollectionTest {

    @Test
    public void testAddTickets() {
        ArrayList<Ticket> tickets_db = new ArrayList<>();
        Passenger passenger1 = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0512345678", "P12345", "1234567890123456", 123);
        Passenger passenger2 = new Passenger("Jane", "Smith", 12, "Woman", "jane.smith@example.com", "0587654321", "P54321", "6543210987654321", 321);

        Timestamp dateFrom = new Timestamp(System.currentTimeMillis());
        Timestamp dateTo = new Timestamp(System.currentTimeMillis() + 3600 * 1000);
        Airplane airplane = new Airplane(1, "Boeing 737", 10, 150, 6);

        Flight flight1 = new Flight(1, "New York", "Paris", "ABC123", "Delta", dateFrom, dateTo, airplane);
        Flight flight2 = new Flight(2, "Tokyo", "Paris", "XYZ789", "United", dateFrom, dateTo, airplane);

        Ticket ticket1 = new Ticket(1, 1000, flight1, false, passenger1);
        Ticket ticket2 = new Ticket(2, 1500, flight2, true, passenger2);

        tickets_db.add(ticket1);
        tickets_db.add(ticket2);

        TicketCollection.addTickets(tickets_db);
        assertEquals(2, TicketCollection.getTickets().size());
    }

    @Test
    public void testAddEmptyTicketList() {
        int initialSize = TicketCollection.getTickets().size();
        TicketCollection.addTickets(new ArrayList<>());
        assertEquals(initialSize, TicketCollection.getTickets().size());
    }

    @Test
    public void testAddTicketWithDuplicateId() {
        Passenger passenger = new Passenger("Jane", "Doe", 25, "Woman", "jane.doe@example.com", "0598765432", "P67890", "9876543210987654", 456);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Airplane airplane = new Airplane(1, "Airbus A320", 8, 160, 4);
        Flight flight = new Flight(2, "Los Angeles", "New York", "XYZ456", "American", now, new Timestamp(now.getTime() + 5000 * 1000), airplane);
        Ticket newTicket = new Ticket(1, 1200, flight, true, passenger); // Same ID as an existing ticket

        ArrayList<Ticket> tickets_db = new ArrayList<>();
        tickets_db.add(newTicket);

        TicketCollection.addTickets(tickets_db);
        assertEquals(2, TicketCollection.getTickets().size());
    }

    @Test
    public void testGetTicketInfo() {
        Passenger passenger1 = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "P12345", "1234567890123456", 123);

        Timestamp dateFrom = new Timestamp(System.currentTimeMillis());
        Timestamp dateTo = new Timestamp(System.currentTimeMillis() + 3600 * 1000);
        Airplane airplane = new Airplane(1, "Boeing 737", 10, 150, 6);

        Flight flight1 = new Flight(1, "New York", "Chicago", "ABC123", "Delta", dateFrom, dateTo, airplane);
        Ticket ticket1 = new Ticket(1, 1000, flight1, false, passenger1);

        ArrayList<Ticket> tickets_db = new ArrayList<>();
        tickets_db.add(ticket1);

        TicketCollection.addTickets(tickets_db);

        Ticket retrievedTicket = TicketCollection.getTicketInfo(1);
        assertNotNull(retrievedTicket);
        assertEquals(1, retrievedTicket.getTicket_id());
    }

    @Test
    public void testValidateTicket() {
        Passenger passenger1 = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "P12345", "1234567890123456", 123);

        Timestamp dateFrom = new Timestamp(System.currentTimeMillis());
        Timestamp dateTo = new Timestamp(System.currentTimeMillis() + 3600 * 1000);
        Airplane airplane = new Airplane(1, "Boeing 737", 10, 150, 6);

        Flight flight1 = new Flight(1, "New York", "Chicago", "ABC123", "Delta", dateFrom, dateTo, airplane);
        Ticket ticket1 = new Ticket(1, 1000, flight1, false, passenger1);

        ArrayList<Ticket> tickets_db = new ArrayList<>();
        tickets_db.add(ticket1);

        TicketCollection.addTickets(tickets_db);
        assertEquals(1, TicketCollection.getTickets().size());

        // Attempt to add a duplicate ticket
        TicketCollection.addTickets(tickets_db);
        assertEquals(1, TicketCollection.getTickets().size());
    }
}
