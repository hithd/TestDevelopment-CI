
import java.util.ArrayList;
import java.util.List;

public class FlightCollection {

	private static ArrayList<Flight> flights = new ArrayList<>();

	public static List<Flight> getFlights() {
		return flights;
	}

	public static void addFlights(ArrayList<Flight> flightsToAdd) {
		if (flightsToAdd == null || flightsToAdd.isEmpty()) {
			throw new IllegalArgumentException("Flights to add cannot be null or empty.");
		}
		for (Flight flight : flightsToAdd) {
			if (!Flight.getValidCities().contains(flight.getDepartTo()) || !Flight.getValidCities().contains(flight.getDepartFrom())) {
				throw new IllegalArgumentException("Invalid city name.");
			}
			flights.add(flight);
		}
	}

	public static Flight getFlightInfo(String city1, String city2) {
		for (Flight flight : flights) {
			if (flight.getDepartTo().equals(city1) && flight.getDepartFrom().equals(city2)) {
				return flight;
			}
		}
		return null;
	}

	public static Flight getFlightInfo(String city) {
		for (Flight flight : flights) {
			if (flight.getDepartTo().equals(city) || flight.getDepartFrom().equals(city)) {
				return flight;
			}
		}
		return null;
	}

	public static Flight getFlightInfo(int flightID) {
		for (Flight flight : flights) {
			if (flight.getFlightID() == flightID) {
				return flight;
			}
		}
		return null;
	}
}


