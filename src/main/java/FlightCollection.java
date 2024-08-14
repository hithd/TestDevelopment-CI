import java.util.ArrayList;
import java.util.List;

public class FlightCollection {

	private static List<Flight> flights = new ArrayList<>();

	public static List<Flight> getFlights() {
		return flights;
	}

	public static void addFlights(List<Flight> flightsToAdd) {
		if (flightsToAdd == null || flightsToAdd.isEmpty()) {
			throw new IllegalArgumentException("Flights to add cannot be null or empty.");
		}
		for (Flight flight : flightsToAdd) {
			if (flight == null) {
				throw new IllegalArgumentException("Flight cannot be null.");
			}
			if (!Flight.getValidCities().contains(flight.getDepartTo()) ||
					!Flight.getValidCities().contains(flight.getDepartFrom())) {
				throw new IllegalArgumentException("Invalid city name.");
			}
			// 检查是否存在重复的 flightID
			for (Flight existingFlight : flights) {
				if (existingFlight.getFlightID() == flight.getFlightID()) {
					throw new IllegalArgumentException("Duplicate flightID: " + flight.getFlightID());
				}
			}
			flights.add(flight);
		}
	}


	public static void clearFlights() {
		flights.clear();
	}

	public static Flight getFlightInfo(String departTo, String departFrom) {
		if (departTo == null || departFrom == null || departTo.isEmpty() || departFrom.isEmpty()) {
			throw new IllegalArgumentException("City names cannot be null or empty.");
		}
		return flights.stream()
				.filter(f -> f.getDepartTo().equals(departTo) && f.getDepartFrom().equals(departFrom))
				.findFirst()
				.orElse(null);
	}

	public static Flight getFlightInfo(String city) {
		if (city == null || city.isEmpty()) {
			throw new IllegalArgumentException("City name cannot be null or empty.");
		}
		return flights.stream()
				.filter(f -> f.getDepartTo().equals(city) || f.getDepartFrom().equals(city))
				.findFirst()
				.orElse(null);
	}

	public static Flight getFlightInfo(int flightID) {
		return flights.stream()
				.filter(f -> f.getFlightID() == flightID)
				.findFirst()
				.orElse(null);
	}

	public static List<Flight> getFlightsByStatus(String status) {
		if (status == null || status.isEmpty()) {
			throw new IllegalArgumentException("Status cannot be null or empty.");
		}
		List<Flight> flightsByStatus = new ArrayList<>();
		for (Flight flight : flights) {
			if (flight.getStatus().equals(status)) {
				flightsByStatus.add(flight);
			}
		}
		return flightsByStatus;
	}
}
