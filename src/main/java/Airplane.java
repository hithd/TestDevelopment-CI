//<<<<<<< Feiji
//=======
//The author of this module code is Jianxin Zhou

//>>>>>>> main
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
        if (!airplaneModel.equals("Boeing 737") && !airplaneModel.equals("Boeing 747") && !airplaneModel.equals("Airbus A320") && !airplaneModel.equals("Airbus A380") && !airplaneModel.equals("Boeing 777")) {
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
        int maxCrewSeats = Math.max(1, (int) ((businessSitsNumber + economySitsNumber) * 0.1)); // 保证至少有1个机组人员座位
        if (crewSitsNumber < 1 || crewSitsNumber > maxCrewSeats) {
            throw new IllegalArgumentException("Crew seats number must be in the range [1, " + maxCrewSeats + "].");
        }
        this.crewSitsNumber = crewSitsNumber;
    }


    public int getTotalSeatsNumber() {
        return businessSitsNumber + economySitsNumber + crewSitsNumber;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "ID=" + getAirplaneID() +
                ", model=" + getAirplaneModel() +
                ", business sits=" + getBusinessSitsNumber() +
                ", economy sits=" + getEconomySitsNumber() +
                ", crew sits=" + getCrewSitsNumber() +
                ", total sits=" + getTotalSeatsNumber() +
                '}';
    }

    public static Airplane getAirPlaneInfo(int airplaneID) {
        // 这个方法应实现基于airplaneID获取飞机信息的功能。这里是一个模拟实现。
        switch (airplaneID) {
            case 1:
                return new Airplane(1, "Boeing 737", 20, 150, 15);
            case 2:
                return new Airplane(2, "Airbus A320", 25, 160, 16);
            case 3:
                return new Airplane(3, "Boeing 747", 30, 180, 18);
            case 4:
                return new Airplane(4, "Airbus A380", 50, 200, 25);
            case 5:
                return new Airplane(5, "Boeing 777", 35, 170, 20);
            default:
                throw new IllegalArgumentException("Airplane with ID " + airplaneID + " not found.");
        }
    }
}
