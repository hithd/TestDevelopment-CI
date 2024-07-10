package org.example;

import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class TicketSystem<T> {
    Passenger passenger = new Passenger();
    Ticket ticket = new Ticket();
    Flight flight = new Flight();
    Scanner in = new Scanner(System.in);

    public TicketSystem() {
        passenger = new Passenger();
        ticket = new Ticket();
        flight = new Flight();
        in = new Scanner(System.in);
    }

    public void showTicket() {
        try {
            System.out.println("You have bought a ticket for flight " + ticket.flight.getDepartFrom() + " - " + ticket.flight.getDepartTo() + "\n\nDetails:");
            System.out.println(this.ticket.toString());
        } catch (NullPointerException e) {
            return;
        }
    }

    public void buyTicket(int ticket_id) throws Exception {
        int flight_id = 0;

        // Select ticket where ticket_id="+ticket_id"
        Ticket validTicket = TicketCollection.getTicketInfo(ticket_id);

        // If there is a valid ticket id was input then we buy it, otherwise show message
        if (validTicket == null) {
            System.out.println("This ticket does not exist.");
            return;
        } else {
            flight_id = validTicket.getFlight().getFlightID();

            try {
                System.out.println("Enter your First Name: ");
                String firstName = in.nextLine();
                passenger.setFirstName(firstName);

                System.out.println("Enter your Second name:");
                String secondName = in.nextLine();
                passenger.setSecondName(secondName); // Setting passengers info

                System.out.println("Enter your age:");
                Integer age = in.nextInt();
                in.nextLine();
                passenger.setAge(age);

                System.out.println("Enter your gender: ");
                String gender = in.nextLine();
                passenger.setGender(gender);

                //添加验证person四个参数是否都被指定的验证
                passenger.validatePersonFields();

                System.out.println("Enter your e-mail address");
                String email = in.nextLine();
                passenger.setEmail(email);

                System.out.println("Enter your phone number (+7):");
                String phoneNumber = in.nextLine();
                passenger.setPhoneNumber(phoneNumber);

                System.out.println("Enter your passport number:");
                String passportNumber = in.nextLine();
                passenger.setPassport(passportNumber);

                //验证passenger所有参数是否被指定
                passenger.validatePassengerFields();

                System.out.println("Do you want to purchase?\n 1-YES 0-NO");
                int purch = in.nextInt();
                if (purch == 0) {
                    return;
                } else {
                    flight = FlightCollection.getFlightInfo(flight_id);

                    int airplane_id = flight.getAirplane().getAirplaneID();

                    Airplane airplane = Airplane.getAirPlaneInfo(airplane_id);

                    ticket = TicketCollection.getTicketInfo(ticket_id);

                    ticket.setPassenger(passenger);
                    ticket.setTicket_id(ticket_id);
                    ticket.setFlight(flight);
                    ticket.setPrice(ticket.getPrice());
                    ticket.setClassVip(ticket.getClassVip());
                    ticket.setTicketStatus(true);

                    if (ticket.getClassVip()) {
                        airplane.setBusinessSitsNumber(airplane.getBusinessSitsNumber() - 1);
                    } else {
                        airplane.setEconomySitsNumber(airplane.getEconomySitsNumber() - 1);
                    }
                }
                System.out.println("Your bill: " + ticket.getPrice() + "\n");

                System.out.println("Enter your card number:");
                String cardNumber = in.next();
                passenger.setCardNumber(cardNumber);

                System.out.println("Enter your security code:");
                Integer securityCode = in.nextInt();
                passenger.setSecurityCode(securityCode);

            } catch (PatternSyntaxException patternException) {
                patternException.printStackTrace();
            }
        }
    }

    public void buyTicket(int ticket_id_first, int ticket_id_second) throws Exception {
        int flight_id_first = 0;
        int flight_id_second = 0;

        System.out.println(ticket_id_first + " " + ticket_id_second);

        Ticket validTicketFirst = TicketCollection.getTicketInfo(ticket_id_first);
        Ticket validTicketSecond = TicketCollection.getTicketInfo(ticket_id_second);

        System.out.println("Processing...");

        if (validTicketFirst == null || validTicketSecond == null) {
            System.out.println("This ticket does not exist.");
            return;
        } else {
            flight_id_first = validTicketFirst.getFlight().getFlightID();
            flight_id_second = validTicketSecond.getFlight().getFlightID();

            try {
                System.out.println("Enter your First Name: ");
                String firstName = in.nextLine();
                passenger.setFirstName(firstName);

                System.out.println("Enter your Second name:");
                String secondName = in.nextLine();
                passenger.setSecondName(secondName); // Setting passengers info

                System.out.println("Enter your age:");
                Integer age = in.nextInt();
                in.nextLine();
                passenger.setAge(age);

                System.out.println("Enter your gender: ");
                String gender = in.nextLine();
                passenger.setGender(gender);

                System.out.println("Enter your e-mail address");
                String email = in.nextLine();
                passenger.setEmail(email);

                System.out.println("Enter your phone number (+7):");
                String phoneNumber = in.nextLine();
                passenger.setPhoneNumber(phoneNumber);

                System.out.println("Enter your passport number:");
                String passportNumber = in.nextLine();
                passenger.setPassport(passportNumber);

                System.out.println("Do you want to purchase?\n 1-YES 0-NO");
                int purch = in.nextInt();
                if (purch == 0) {
                    return;
                } else {
                    Flight flight_first = FlightCollection.getFlightInfo(flight_id_first);
                    int airplane_id_first = flight_first.getAirplane().getAirplaneID();
                    Airplane airplane_first = Airplane.getAirPlaneInfo(airplane_id_first);

                    Flight flight_second = FlightCollection.getFlightInfo(flight_id_second);
                    int airplane_id_second = flight_second.getAirplane().getAirplaneID();
                    Airplane airplane_second = Airplane.getAirPlaneInfo(airplane_id_second);

                    Ticket ticket_first = TicketCollection.getTicketInfo(ticket_id_first);
                    Ticket ticket_second = TicketCollection.getTicketInfo(ticket_id_second);

                    ticket_first.setPassenger(passenger);
                    ticket_first.setTicket_id(ticket_id_first);
                    ticket_first.setFlight(flight_first);
                    ticket_first.setPrice(ticket_first.getPrice());
                    ticket_first.setClassVip(ticket_first.getClassVip());
                    ticket_first.setTicketStatus(true);

                    if (ticket_first.getClassVip()) {
                        airplane_first.setBusinessSitsNumber(airplane_first.getBusinessSitsNumber() - 1);
                    } else {
                        airplane_first.setEconomySitsNumber(airplane_first.getEconomySitsNumber() - 1);
                    }

                    System.out.println("--*-*-");

                    ticket_second.setPassenger(passenger);
                    ticket_second.setTicket_id(ticket_id_second);
                    ticket_second.setFlight(flight_second);
                    ticket_second.setPrice(ticket_second.getPrice());
                    ticket_second.setClassVip(ticket_second.getClassVip());
                    ticket_second.setTicketStatus(true);

                    if (ticket_second.getClassVip()) {
                        airplane_second.setBusinessSitsNumber(airplane_second.getBusinessSitsNumber() - 1);
                    } else {
                        airplane_second.setEconomySitsNumber(airplane_second.getEconomySitsNumber() - 1);
                    }

                    System.out.println("--*-*-");

                    ticket.setPrice(ticket_first.getPrice() + ticket_second.getPrice());

                    System.out.println("Your bill: " + ticket.getPrice() + "\n");

                    System.out.println("Enter your card number:");
                    String cardNumber = in.next();
                    passenger.setCardNumber(cardNumber);

                    System.out.println("Enter your security code:");
                    Integer securityCode = in.nextInt();
                    passenger.setSecurityCode(securityCode);
                }
            } catch (PatternSyntaxException patternException) {
                patternException.printStackTrace();
            }
        }
    }

    public void chooseTicket(String city1, String city2) throws Exception {
        int counter = 1;
        int idFirst = 0;
        int idSecond = 0;

        Flight flight = new Flight();

        // Search for direct flight from city1 to city2
        flight = FlightCollection.getFlightInfo(city1, city2);

        if (flight != null) {
            TicketCollection.getAllTickets();

            System.out.println("\nEnter ID of ticket you want to choose:");
            int ticket_id = in.nextInt();

            // Validate ticker here
            // Buy ticket here
            buyTicket(ticket_id);
        } else {
            // In case there is no direct ticket from city1 to city2
            Flight depart_to = FlightCollection.getFlightInfo(city2);
            String connectCity = depart_to.getDepartFrom();
            Flight flightConnectingTwoCities = FlightCollection.getFlightInfo(city1, connectCity);

            if (flightConnectingTwoCities != null) {
                System.out.println("There is special way to go there. And it is transfer way, like above. Way №" + counter);
                idFirst = depart_to.getFlightID();
                idSecond = flightConnectingTwoCities.getFlightID();
            }

            counter++;
            buyTicket(idFirst, idSecond); // Pass two tickets and buy them

            if (counter == 1) {
                System.out.println("There is no possible variants.");
            }
            return;
        }
    }

    public boolean validateFlight(Flight flight) {
        if (flight.getFlightID() <= 0) return false;
        if (flight.getDepartTo() == null || flight.getDepartTo().isEmpty()) return false;
        if (flight.getDepartFrom() == null || flight.getDepartFrom().isEmpty()) return false;
        if (flight.getCode() == null || flight.getCode().isEmpty()) return false;
        if (flight.getCompany() == null || flight.getCompany().isEmpty()) return false;
        if (flight.getDateFrom() == null) return false;
        if (flight.getDateTo() == null) return false;
        if (flight.getAirplane() == null) return false;
        return true;
    }

    public boolean validatePassenger(Passenger passenger) {
        if (passenger.getFirstName() == null || passenger.getFirstName().isEmpty()) return false;
        if (passenger.getSecondName() == null || passenger.getSecondName().isEmpty()) return false;
        if (passenger.getAge() <= 0) return false;
        if (passenger.getGender() == null || passenger.getGender().isEmpty()) return false;
        if (passenger.getEmail() == null || passenger.getEmail().isEmpty()) return false;
        if (passenger.getPhoneNumber() == null || passenger.getPhoneNumber().isEmpty()) return false;
        if (passenger.getPassport() == null || passenger.getPassport().isEmpty()) return false;
        if (passenger.getCardNumber() == null || passenger.getCardNumber().isEmpty()) return false;
        if (passenger.getSecurityCode() <= 0) return false;
        return true;
    }

    public boolean validateTicket(Ticket ticket) {
        if (ticket.getTicketId() <= 0) return false;
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

    public Passenger gatherPassengerInfo() {
        Passenger passenger = new Passenger();

        System.out.println("Enter your First Name: ");
        passenger.setFirstName(in.nextLine());

        System.out.println("Enter your Second name:");
        passenger.setSecondName(in.nextLine());

        System.out.println("Enter your age:");
        passenger.setAge(in.nextInt());
        in.nextLine(); // consume newline

        System.out.println("Enter your gender: ");
        passenger.setGender(in.nextLine());

        System.out.println("Enter your e-mail address");
        passenger.setEmail(in.nextLine());

        System.out.println("Enter your phone number (+7):");
        passenger.setPhoneNumber(in.nextLine());

        System.out.println("Enter your passport number:");
        passenger.setPassport(in.nextLine());

        return passenger;
    }

    public String buyTicketAndValidate(int ticket_id) throws Exception {
        Ticket validTicket = TicketCollection.getTicketInfo(ticket_id);

        if (validTicket == null) {
            return "This ticket does not exist.";
        } else if (validTicket.ticketStatus()) {
            return "This ticket is already booked.";
        } else {
            passenger = gatherPassengerInfo();

            System.out.println("Do you want to purchase?\n 1-YES 0-NO");
            int purch = in.nextInt();
            if (purch == 0) {
                return "Ticket booking cancelled.";
            }

            // Continue with booking process
            flight = validTicket.getFlight();
            ticket = validTicket;

            ticket.setPassenger(passenger);
            ticket.setTicketStatus(true);

            System.out.println("Your bill: " + ticket.getPrice() + "\n");

            System.out.println("Enter your card number:");
            passenger.setCardNumber(in.next());

            System.out.println("Enter your security code:");
            passenger.setSecurityCode(in.nextInt());

            return "Ticket booked successfully.";
        }
    }
}
