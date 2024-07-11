
public class Airplane {
    private int airplaneID;
    private String airplaneModel;
    private int businessSitsNumber;
    private int economySitsNumber;
    private int crewSitsNumber;

    public Airplane() {}

    public Airplane(int airplaneID, String airplaneModel, int businessSitsNumber, int economySitsNumber, int crewSitsNumber) {
        setAirplaneID(airplaneID);
        setAirplaneModel(airplaneModel);
        setBusinessSitsNumber(businessSitsNumber);
        setEconomySitsNumber(economySitsNumber);
        setCrewSitsNumber(crewSitsNumber);
    }

    public int getAirplaneID() {
        return airplaneID;
    }

    public void setAirplaneID(int airplaneID) {
        if (airplaneID < 1 || airplaneID > 20) {
            throw new IllegalArgumentException("Airplane ID must be in the range [1, 20].");
        }
        this.airplaneID = airplaneID;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        if (!airplaneModel.equals("Boeing 737") && !airplaneModel.equals("Boeing 747") && !airplaneModel.equals("Airbus A320")) {
            throw new IllegalArgumentException("Invalid Airplane model.");
        }
        this.airplaneModel = airplaneModel;
    }

    public int getBusinessSitsNumber() {
        return businessSitsNumber;
    }

    public void setBusinessSitsNumber(int businessSitsNumber) {
        if (businessSitsNumber < 1 || businessSitsNumber > 300) {
            throw new IllegalArgumentException("Business seats number must be in the range [1, 300].");
        }
        this.businessSitsNumber = businessSitsNumber;
    }

    public int getEconomySitsNumber() {
        return economySitsNumber;
    }

    public void setEconomySitsNumber(int economySitsNumber) {
        if (economySitsNumber < 1 || economySitsNumber > 300) {
            throw new IllegalArgumentException("Economy seats number must be in the range [1, 300].");
        }
        this.economySitsNumber = economySitsNumber;
    }

    public int getCrewSitsNumber() {
        return crewSitsNumber;
    }

    public void setCrewSitsNumber(int crewSitsNumber) {
        if (crewSitsNumber < 1 || crewSitsNumber > 300) {
            throw new IllegalArgumentException("Crew seats number must be in the range [1, 300].");
        }
        this.crewSitsNumber = crewSitsNumber;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "ID=" + getAirplaneID() +
                ", model=" + getAirplaneModel() +
                ", business sits=" + getBusinessSitsNumber() +
                ", economy sits=" + getEconomySitsNumber() +
                ", crew sits=" + getCrewSitsNumber() +
                '}';
    }

    public static Airplane getAirPlaneInfo(int airplaneID) {
        // This method should be implemented to fetch airplane information based on airplaneID.
        // For example, you can fetch this information from a database or a collection.
        // Here is a mock implementation:
//        if (airplaneID == 1) {
//            return new Airplane(1, "Boeing 737", 20, 150, 10);
//        } else if (airplaneID == 2) {
//            return new Airplane(2, "Airbus A320", 25, 160, 12);
//        }
//        // Return null or throw an exception if airplaneID is not found
//        throw new IllegalArgumentException("Airplane with ID " + airplaneID + " not found.");
        return null;
    }
}
