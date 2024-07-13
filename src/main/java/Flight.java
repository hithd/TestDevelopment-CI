//The author of this module code is Jianxin Zhou

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

//public class Flight {
//    private int flightID;
//    private String departTo;
//    private String departFrom;
//    private String code;
//    private String company;
//    private Timestamp dateFrom;
//    private Timestamp dateTo;
//    private Airplane airplane;
//
//    // List of valid cities (for demonstration purposes, you may want to fetch this from a database)
//    private static final List<String> validCities = Arrays.asList("New York", "Los Angeles", "San Francisco", "Chicago",
//            "Paris", "Berlin", "London", "Tokyo");
//
//    public Flight() {
//
//    }
//
//    public Flight(int flightID, String departTo, String departFrom, String code, String company,
//                  String dateFromStr, String dateToStr, Airplane airplane) throws ParseException {
//        // Validate inputs
//        if (departTo == null || departFrom == null || code == null || company == null ||
//                dateFromStr == null || dateToStr == null || airplane == null) {
//            throw new IllegalArgumentException("All fields are required.");
//        }
//
//        if (!validCities.contains(departTo) || !validCities.contains(departFrom)) {
//            throw new IllegalArgumentException("Invalid city name.");
//        }
//
//        if (departTo.equals(departFrom)) {
//            throw new IllegalArgumentException("Departure and destination cities cannot be the same.");
//        }
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//        dateFormat.setLenient(false);
//
//        try {
//            this.dateFrom = new Timestamp(dateFormat.parse(dateFromStr).getTime());
//            this.dateTo = new Timestamp(dateFormat.parse(dateToStr).getTime());
//        } catch (ParseException e) {
//            throw new IllegalArgumentException("Date must be in DD/MM/YY format and time in HH:MM:SS format.");
//        }
//
//        // Assign values to fields
//        this.flightID = flightID;
//        this.departTo = departTo;
//        this.departFrom = departFrom;
//        this.code = code;
//        this.company = company;
//        this.airplane = airplane;
//    }
//
//    public int getFlightID() {
//        return flightID;
//    }
//
//    public void setFlightID(int flightID) {
//        this.flightID = flightID;
//    }
//
//    public String getDepartTo() {
//        return departTo;
//    }
//
//    public void setDepartTo(String departTo) {
//        this.departTo = departTo;
//    }
//
//    public String getDepartFrom() {
//        return departFrom;
//    }
//
//    public void setDepartFrom(String departFrom) {
//        this.departFrom = departFrom;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getCompany() {
//        return company;
//    }
//
//    public void setCompany(String company) {
//        this.company = company;
//    }
//
//    public Timestamp getDateFrom() {
//        return dateFrom;
//    }
//
//    public void setDateFrom(Timestamp dateFrom) {
//        this.dateFrom = dateFrom;
//    }
//
//    public Timestamp getDateTo() {
//        return dateTo;
//    }
//
//    public void setDateTo(Timestamp dateTo) {
//        this.dateTo = dateTo;
//    }
//
//    public Airplane getAirplane() {
//        return airplane;
//    }
//
//    public void setAirplane(Airplane airplane) {
//        this.airplane = airplane;
//    }
//
//    public static List<String> getValidCities() {
//        return validCities;
//    }
//
//    @Override
//    public String toString() {
//        return "Flight{" +
//                "flightID=" + flightID +
//                ", departTo='" + departTo + '\'' +
//                ", departFrom='" + departFrom + '\'' +
//                ", code='" + code + '\'' +
//                ", company='" + company + '\'' +
//                ", dateFrom=" + dateFrom +
//                ", dateTo=" + dateTo +
//                ", airplane=" + airplane +
//                '}';
//    }
//}
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class Flight {
    private int flightID;
    private String departTo;
    private String departFrom;
    private String code;
    private String company;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private Airplane airplane;

    // List of valid cities (for demonstration purposes, you may want to fetch this from a database)
    private static final List<String> validCities = Arrays.asList("New York", "Los Angeles", "San Francisco", "Chicago", "Paris", "Berlin", "London", "Tokyo");

    public Flight() {

    }

    public Flight(int flightID, String departTo, String departFrom, String code, String company,
                  Timestamp dateFrom, Timestamp dateTo, Airplane airplane) {
        // Validate inputs
        if (departTo == null || departFrom == null || code == null || company == null ||
                dateFrom == null || dateTo == null || airplane == null) {
            throw new IllegalArgumentException("All fields are required.");
        }

        if (!validCities.contains(departTo) || !validCities.contains(departFrom)) {
            throw new IllegalArgumentException("Invalid city name.");
        }

        if (departTo.equals(departFrom)) {
            throw new IllegalArgumentException("Departure and destination cities cannot be the same.");
        }

        if (dateFrom.after(dateTo)) {
            throw new IllegalArgumentException("Departure date cannot be after arrival date.");
        }

        // Assign values to fields
        this.flightID = flightID;
        this.departTo = departTo;
        this.departFrom = departFrom;
        this.code = code;
        this.company = company;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.airplane = airplane;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getDepartTo() {
        return departTo;
    }

    public void setDepartTo(String departTo) {
        this.departTo = departTo;
    }

    public String getDepartFrom() {
        return departFrom;
    }

    public void setDepartFrom(String departFrom) {
        this.departFrom = departFrom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public static List<String> getValidCities() {
        return validCities;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID=" + flightID +
                ", departTo='" + departTo + '\'' +
                ", departFrom='" + departFrom + '\'' +
                ", code='" + code + '\'' +
                ", company='" + company + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", airplane=" + airplane +
                '}';
    }
}





