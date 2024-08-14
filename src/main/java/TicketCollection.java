//The author of this module code is Kaihua Tian

package org.example;

import java.util.ArrayList;

public class TicketCollection {

    public static ArrayList<Ticket> tickets = new ArrayList<>();

    public static ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public static void addTickets(ArrayList<Ticket> tickets_db) {
        for (Ticket ticket : tickets_db) {
            if (validateTicket(ticket)) {
                TicketCollection.tickets.add(ticket);
            }
        }
    }

    public static void getAllTickets() {
        // display all available tickets from the Ticket collection
    }

    public static Ticket getTicketInfo(int ticket_id) {
        // SELECT a ticket where ticket id = ticket_id
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId() == ticket_id) {
                return ticket;
            }
        }
        return null;
    }

    static boolean validateTicket(Ticket ticket) {
        if (ticket == null) {
            return false;
        }
        for (Ticket t : tickets) {
            if (t.getTicketId() == ticket.getTicketId()) {
                return false; // Ticket with the same ID already exists
            }
        }
        return true;
    }
}



