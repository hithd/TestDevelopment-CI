//The author of this module code is Xinghui Huang

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketTest {
    private Ticket ticket;
    private FlightStub flight;
    private Passenger passenger;

    @Before
    public void setUp() {
        flight = new FlightStub(); // 用FlightStub类模拟Flight行为
        passenger = new Passenger();
        passenger.setFirstName("Juju");
        passenger.setSecondName("Bond");
        passenger.setAge(30);
        passenger.setGender("Woman");
        passenger.setCardNumber("1234567890123456");

    }

    @Test
    public void testTicketStatusValues() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals("False", ticket.getExplicitStatus());
        ticket.setTicketStatus(true);
        assertEquals("True", ticket.getExplicitStatus());
    }

    @Test
    public void testDiscountForChildren() {
        passenger.setAge(10);
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(56.0, ticket.getPrice(), 0.01); // 50% discount + 12% tax
    }
    //新增年龄边际判断
    @Test
    public void testDiscountDownBoundary() {
        passenger.setAge(15);
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(112, ticket.getPrice(), 0.01); // 50% discount + 12% tax
    }
    @Test
    public void testDiscountUpBoundary() {
        passenger.setAge(59);
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(112, ticket.getPrice(), 0.01); // 50% discount + 12% tax
    }
    @Test
    public void testDiscountForElders() {
        passenger.setAge(60);
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(0.0, ticket.getPrice(), 0.01);
    }

    @Test
    public void testNoDiscountForAdults() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(112.0, ticket.getPrice(), 0.01); // Only 12% tax applied
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPrice() {
        ticket = new Ticket(1, -100.0, flight, false, passenger);
    }

    @Test
    public void testValidPrice() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertTrue(ticket.getPrice() > 0);
    }

    @Test
    public void testServiceTaxApplied() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(112.0, ticket.getPrice(), 0.01); // 12% tax applied
    }

    @Test
    public void testValidFlightAndPassengerInfo() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertNotNull(ticket.getFlight());
        assertNotNull(ticket.getPassenger());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNullPassenger() {
        ticket = new Ticket(1, 100.0, flight, false, null);
    }


    @Test
    public void testSettersAndGetters() {
        ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTicket_id(2);
        ticket.setPrice(200.0);
        ticket.setFlight(flight);
        ticket.setClassVip(true);
        ticket.setTicketStatus(true);
        ticket.setPassenger(passenger);

        assertEquals(2, ticket.getTicket_id());
        assertEquals(224.0, ticket.getPrice(), 0.01); // 200 + 12% tax

        assertTrue(ticket.getClassVip());
        assertTrue(ticket.ticketStatus());
        assertEquals(passenger, ticket.getPassenger());
    }
    @Test
    public void testSettersAndGettersWithId() {
        ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTicketId(2);
        ticket.setPrice(200.0);
        ticket.setFlight(flight);
        ticket.setClassVip(true);
        ticket.setTicketStatus(true);
        ticket.setPassenger(passenger);

        assertEquals(2, ticket.getTicket_id());
        assertEquals(224.0, ticket.getPrice(), 0.01); // 200 + 12% tax

        assertTrue(ticket.getClassVip());
        assertTrue(ticket.ticketStatus());
        assertEquals(passenger, ticket.getPassenger());
    }
    //新增ClassVip False判断
    @Test
    public void testClassVipFalse() {
        ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTicket_id(2);
        ticket.setPrice(200.0);
        ticket.setFlight(flight);
        ticket.setClassVip(false);
        ticket.setTicketStatus(true);
        ticket.setPassenger(passenger);

        assertEquals(2, ticket.getTicket_id());
        assertEquals(224.0, ticket.getPrice(), 0.01); // 200 + 12% tax

        assertFalse(ticket.getClassVip());
        assertTrue(ticket.ticketStatus());
        assertEquals(passenger, ticket.getPassenger());
    }
    @Test
    public void testToString() {
        ticket = new Ticket(1, 100.0, flight, true, passenger);
        String result = ticket.toString();
        assertTrue(result.contains("Price=112.00 KZT"));
        assertTrue(result.contains("Vip status=true"));
        assertTrue(result.contains("Ticket was purchased=False"));
    }
    //新增null判定
    @Test
    public void testToStringWithNoFlight() {
        ticket = new Ticket(1, 100.0, null, true, passenger);
        String result = ticket.toString();

        assertTrue(result.contains("Price=112.00 KZT"));
        assertTrue(result.contains("No flight assigned"));
        assertTrue(result.contains("Vip status=true"));
        assertFalse(result.contains("No passenger assigned"));
    }

    @Test
    public void testCancelTicket() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertFalse(ticket.cancelTicket()); // 初始状态为未购买，无法取消

        ticket.setTicketStatus(true); // 设置为已购买
        assertTrue(ticket.cancelTicket()); // 现在可以取消
        assertFalse(ticket.ticketStatus()); // 确认状态已更改为未购买

        assertFalse(ticket.cancelTicket()); // 再次尝试取消应该失败
    }

    @Test
    public void testCalculateRefund() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(0.0, ticket.calculateRefund(), 0.01); // 未购买状态，退款为0

        ticket.setTicketStatus(true); // 设置为已购买
        assertEquals(100.8, ticket.calculateRefund(), 0.01); // 退款应为价格的90%（含税价的90%）

        ticket.cancelTicket();
        assertEquals(0.0, ticket.calculateRefund(), 0.01); // 取消后，退款应为0
    }

    @Test
    public void testSetPassengerRecalculatesPrice() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(112.0, ticket.getPrice(), 0.01); // 初始价格（含税）

        Passenger childPassenger = new Passenger();
        childPassenger.setAge(10);
        ticket.setPassenger(childPassenger);
        assertEquals(56.0, ticket.getPrice(), 0.01); // 儿童价格（50%折扣 + 税）

        Passenger elderPassenger = new Passenger();
        elderPassenger.setAge(65);
        ticket.setPassenger(elderPassenger);
        assertEquals(0.0, ticket.getPrice(), 0.01); // 老年人价格（100%折扣）
    }

    @Test
    public void testOriginalPricePreserved() {
        ticket = new Ticket(1, 100.0, flight, false, passenger);
        assertEquals(112.0, ticket.getPrice(), 0.01); // 初始价格（含税）

        Passenger childPassenger = new Passenger();
        childPassenger.setAge(10);
        ticket.setPassenger(childPassenger);
        assertEquals(56.0, ticket.getPrice(), 0.01); // 儿童价格

        ticket.setPassenger(passenger); // 设回成年乘客
        assertEquals(112.0, ticket.getPrice(), 0.01); // 价格应该恢复到原始价格（含税）
    }
}