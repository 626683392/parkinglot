package zxh.demo.parkinglot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * SmartParkingBoyTest:
 * @author zhangxuhai
 * @date 2019/11/30
*/
public class SmartParkingBoyTest {
    private List<ParkingLot> parkingLots = new ArrayList<>();

    @Test
    public void should_return_ticket_when_park_car_by_parking_boy() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLots.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        assertNotNull(smartParkingBoy.park(new Car()));
    }

    @Test
    public void should_park_car_to_first_parking_lot_since_it_has_more_empty_slot() {
        ParkingLot p1 = new ParkingLot(5);
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(5);
        p2.park(new Car());
        parkingLots.add(p2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car myCar = new Car();
        Ticket t = smartParkingBoy.park(myCar);
        p1.pick(t);
    }

    @Test
    public void should_park_car_to_second_parking_lot_since_it_has_more_empty_slot() {
        ParkingLot p1 = new ParkingLot(5);
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(5);
        parkingLots.add(p2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car myCar = new Car();
        Ticket t = smartParkingBoy.park(myCar);
        p2.pick(t);
    }

    @Test
    public void should_park_some_car_alternatively_to_same_empty_parking_lots() {
        ParkingLot p1 = new ParkingLot(5);
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(5);
        p2.park(new Car());
        parkingLots.add(p2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Ticket t1 = smartParkingBoy.park(new Car());
        Ticket t2 = smartParkingBoy.park(new Car());
        Ticket t3 = smartParkingBoy.park(new Car());
        Ticket t4 = smartParkingBoy.park(new Car());

        p1.pick(t1);
        p1.pick(t3);

        p2.pick(t2);
        p2.pick(t4);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_say_no_to_park_car_if_all_parking_lot_full() {
        ParkingLot p1 = new ParkingLot(1);
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(1);
        p2.park(new Car());
        parkingLots.add(p2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        expectedException.expect(ParkingLotFullException.class);
        smartParkingBoy.park(new Car());
    }

    @Test
    public void should_pick_car_from_parking_lot() {
        ParkingLot p1 = new ParkingLot(1);
        parkingLots.add(p1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car myCar = new Car();
        Ticket t = smartParkingBoy.park(myCar);
        assertEquals(myCar, smartParkingBoy.pick(t));
    }

    @Test
    public void should_pick_car_from_many_parking_lot() {
        ParkingLot p1 = new ParkingLot(1);
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(2);
        p2.park(new Car());
        parkingLots.add(p2);
        ParkingLot p3 = new ParkingLot(3);
        p3.park(new Car());
        parkingLots.add(p3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car myCar = new Car();
        Ticket t = smartParkingBoy.park(myCar);
        assertEquals(myCar, smartParkingBoy.pick(t));
    }

    @Test
    public void should_say_no_to_pick_car_with_invalid_ticket() {
        ParkingLot p1 = new ParkingLot(1);
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(2);
        p2.park(new Car());
        parkingLots.add(p2);
        ParkingLot p3 = new ParkingLot(3);
        p3.park(new Car());
        parkingLots.add(p3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car myCar = new Car();
        smartParkingBoy.park(myCar);

        expectedException.expect(InvalidTicketException.class);
        smartParkingBoy.pick(new Ticket());
    }

    @Test
    public void should_say_no_to_pick_car_with_used_ticket() {
        ParkingLot p1 = new ParkingLot(1);
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(2);
        p2.park(new Car());
        parkingLots.add(p2);
        ParkingLot p3 = new ParkingLot(3);
        p3.park(new Car());
        parkingLots.add(p3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car myCar = new Car();
        Ticket t = smartParkingBoy.park(myCar);
        smartParkingBoy.pick(t);

        expectedException.expect(InvalidTicketException.class);
        smartParkingBoy.pick(t);
    }
}
