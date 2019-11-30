package zxh.demo.parkinglot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * ParkingLotTest:
 * @author zhangxuhai
 * @date 2019/11/30
*/
public class ParkingLotTest {
    @Test
    public void should_return_a_ticket_when_park_car_to_one_slot_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket t = parkingLot.park(new Car());
        assertNotNull(t);
    }

    @Test
    public void should_return_some_ticket_related_to_park_some_car_to_some_slot_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(3);
        Ticket t1 = parkingLot.park(new Car());
        Ticket t2 = parkingLot.park(new Car());
        Ticket t3 = parkingLot.park(new Car());

        assertNotEquals(t1, t2);
        assertNotEquals(t2, t3);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_say_no_when_park_car_to_no_slot_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());

        expectedException.expect(ParkingLotFullException.class);
        parkingLot.park(new Car());
    }

    @Test
    public void should_pick_car_from_parking_lot_which_only_parked_my_car() {
        ParkingLot parkingLot = new ParkingLot(1);

        Car myCar = new Car();
        Ticket t = parkingLot.park(myCar);
        Car car = parkingLot.pick(t);

        assertEquals(myCar, car);
    }

    @Test
    public void should_pick_my_car_from_parking_lot_parked_many_car() {
        ParkingLot parkingLot = new ParkingLot(3);

        Car myCar = new Car();
        parkingLot.park(new Car());
        Ticket t = parkingLot.park(myCar);
        parkingLot.park(new Car());

        Car car = parkingLot.pick(t);

        assertEquals(myCar, car);
    }

    @Test
    public void should_say_no_to_invalid_ticket_from_parking_lot_parked_my_car() {
        ParkingLot parkingLot = new ParkingLot(3);

        Car myCar = new Car();
        parkingLot.park(myCar);

        Ticket t = new Ticket();
        expectedException.expect(InvalidTicketException.class);
        parkingLot.pick(t);
    }

    @Test
    public void should_say_no_to_a_used_ticket_from_parking_lot_parked_my_car() {
        ParkingLot parkingLot = new ParkingLot(3);

        Car myCar = new Car();
        Ticket t = parkingLot.park(myCar);
        parkingLot.pick(t);

        expectedException.expect(InvalidTicketException.class);
        parkingLot.pick(t);
    }
}
