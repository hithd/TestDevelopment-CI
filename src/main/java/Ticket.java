////The author of this module code is Xinghui Huang
//
//public class Ticket {
//    private int ticketId;
//    private double price;
//    Flight flight;
//    private boolean classVip;
//    private boolean status;
//    Passenger passenger;
//    //将tax_rate独立出来便于修改
//    private static final double SERVICE_TAX_RATE = 0.12;
//
//    public Ticket(int ticket_id, double price, Flight flight, boolean classVip, Passenger passenger) {
//        this.ticketId = ticket_id;
//        this.flight = flight;
//        this.classVip = classVip;
//        this.status = false;
//        this.passenger = passenger;
//        setPrice(price);
//    }
//
//    public Ticket() {
//        // Default constructor
//    }
//
//    public int getTicket_id() {
//        return ticketId;
//    }
//
//    public void setTicket_id(int ticketId) {
//        this.ticketId = ticketId;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        if (price < 0) {
//            throw new IllegalArgumentException("Price must be non-negative");
//        }
//        this.price = price;
//        saleByAge(passenger.getAge());
//        serviceTax();
//    }
//
//    public void saleByAge(int age) {
//        if (age < 15) {
//            price -= price * 0.5; // 50%  under 15
//        } else if (age >= 60) {
//            price = 0; // 100%  elder people
//        }
//    }
//
//    public Flight getFlight() {
//        return flight;
//    }
//
//    public void setFlight(Flight flight) {
//        this.flight = flight;
//    }
//
//    public boolean getClassVip() {
//        return classVip;
//    }
//
//    public void setClassVip(boolean classVip) {
//        this.classVip = classVip;
//    }
//
//    public boolean ticketStatus() {
//        return status;
//    }
//
//    public void setTicketStatus(boolean status) {
//        this.status = status;
//    }
//
//    public void serviceTax() {
//        this.price *= (1 + SERVICE_TAX_RATE);
//    }
//
//    public Passenger getPassenger() {
//        return passenger;
//    }
//
//    public void setPassenger(Passenger passenger) {
//        this.passenger = passenger;
//    }
//
//    // A New method to get explicit 'True' or 'False' status
//    public String getExplicitStatus() {
//        return status ? "True" : "False";
//    }
//
//    @Override
//    public String toString() {
//        return "Ticket{" + '\n' +
//                "Price=" + String.format("%.2f", getPrice()) + " KZT, " + '\n' +
//                getFlight() + '\n' +
//                "Vip status=" + getClassVip() + '\n' +
//                getPassenger() + '\n' +
//                "Ticket was purchased=" + getExplicitStatus() + "\n}";
//    }
//}
public class Ticket {
    private int ticketId;
    private double price;
    private double originalPrice;  // 新增：保存原始价格
    Flight flight;
    private boolean classVip;
    private boolean status;
    private Passenger passenger;
    private static final double SERVICE_TAX_RATE = 0.12;
    private static final double CANCELLATION_FEE_RATE = 0.10;  // 新增：取消费率

    public Ticket(int ticket_id, double price, Flight flight, boolean classVip, Passenger passenger) {
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger cannot be null");
        }
        this.ticketId = ticket_id;
        this.flight = flight;
        this.classVip = classVip;
        this.status = false;
        this.passenger = passenger;
        setPrice(price);
    }

    public Ticket() {
        // Default constructor
    }

    public int getTicket_id() {
        return ticketId;
    }

    public void setTicket_id(int ticketId) {
        this.ticketId = ticketId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        this.originalPrice = price;  // 保存原始价格
        this.price = price;
        applyDiscounts();
    }
//新增，提供统一的价格设定
    private void applyDiscounts() {
        if (passenger != null) {
            saleByAge(passenger.getAge());
        }
        serviceTax();
    }

    public void saleByAge(int age) {
        if (age < 15) {
            price *= 0.5; // 50% discount for under 15
        } else if (age >= 60) {
            price = 0; // 100% discount for elder people
        }
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean getClassVip() {
        return classVip;
    }

    public void setClassVip(boolean classVip) {
        this.classVip = classVip;
    }

    public boolean ticketStatus() {
        return status;
    }

    public void setTicketStatus(boolean status) {
        this.status = status;
    }

    public void serviceTax() {
        this.price *= (1 + SERVICE_TAX_RATE);
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
        setPrice(originalPrice);  // 重新计算价格
    }

    public String getExplicitStatus() {
        return status ? "True" : "False";
    }

    // 新增：取消票务方法
    public boolean cancelTicket() {
        if (status) {
            status = false;
            return true;
        }
        return false;
    }

    // 新增：计算退款金额方法
    public double calculateRefund() {
        if (!status) {
            return 0;  // 票没有被购买，不能退款
        }
        return price * (1 - CANCELLATION_FEE_RATE);
    }
//修改，检查空值
    @Override
    public String toString() {
        return "Ticket{" + '\n' +
                "Price=" + String.format("%.2f", getPrice()) + " KZT, " + '\n' +
                (flight != null ? flight.toString() : "No flight assigned") + '\n' +
                "Vip status=" + getClassVip() + '\n' +
                (passenger != null ? passenger.toString() : "No passenger assigned") + '\n' +
                "Ticket was purchased=" + getExplicitStatus() + "\n}";
    }
}