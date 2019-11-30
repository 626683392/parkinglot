package zxh.demo.parkinglot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * SuperParkingBoyTest:
 * @author zhangxuhai
 * @date 2019/11/30
*/
public class SuperParkingBoyTest {
    private List<ParkingLot> parkingLots = new ArrayList<>();

    @Test
    public void should_return_ticket_when_park_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLots.add(parkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        assertNotNull(superParkingBoy.park(new Car()));
    }
    
    @Test
    public void should_park_to_first_parking_lot_since_it_has_higher_empty_rate() {
        ParkingLot p1 = new ParkingLot(7);
        p1.park(new Car());
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(5);
        p2.park(new Car());
        p2.park(new Car());
        parkingLots.add(p2);

        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Ticket t = superParkingBoy.park(new Car());

        p1.pick(t);
    }

    @Test
    public void should_park_to_second_parking_lot_since_it_has_higher_empty_rate() {
        ParkingLot p1 = new ParkingLot(5);
        p1.park(new Car());
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(7);
        p2.park(new Car());
        p2.park(new Car());
        parkingLots.add(p2);

        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Ticket t = superParkingBoy.park(new Car());

        p2.pick(t);
    }

    @Test
    public void should_park_to_first_parking_lot_since_two_have_same_empty_rate() {
        ParkingLot p1 = new ParkingLot(5);
        p1.park(new Car());
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(5);
        p2.park(new Car());
        p2.park(new Car());
        parkingLots.add(p2);

        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Ticket t = superParkingBoy.park(new Car());

        p1.pick(t);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_say_no_when_park_car_since_all_parking_lot_is_full() {
        ParkingLot p1 = new ParkingLot(2);
        p1.park(new Car());
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(2);
        p2.park(new Car());
        p2.park(new Car());
        parkingLots.add(p2);

        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        expectedException.expect(ParkingLotFullException.class);
        superParkingBoy.park(new Car());
    }

    @Test
    public void should_pick_car_from_parking_lot() {
        ParkingLot p1 = new ParkingLot(1);
        parkingLots.add(p1);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        Car myCar = new Car();
        Ticket t = superParkingBoy.park(myCar);
        assertEquals(myCar, superParkingBoy.pick(t));
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
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        Car myCar = new Car();
        Ticket t = superParkingBoy.park(myCar);
        assertEquals(myCar, superParkingBoy.pick(t));
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
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        Car myCar = new Car();
        superParkingBoy.park(myCar);

        expectedException.expect(InvalidTicketException.class);
        superParkingBoy.pick(new Ticket());
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
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        Car myCar = new Car();
        Ticket t = superParkingBoy.park(myCar);
        superParkingBoy.pick(t);

        expectedException.expect(InvalidTicketException.class);
        superParkingBoy.pick(t);
    }
}
