//The author of this module code is Xinghui Huang

public class FlightStub extends Flight {
    private String flightNumber;
    private String departure;
    private String arrival;
    public FlightStub() {}
    public FlightStub(String flightNumber, String departure, String arrival) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                '}';
    }
}