package zxh.demo.parking.lot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * ParkingLotTest:
 * @author zhangxuhai
 * @date 2019/11/18
*/
public class ParkingLotTest {
    
    @Test
    public void should_park_1_car_to_parking_lot_with_1_slot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = parkingLot.park();
        assertNotNull(ticket);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_say_no_to_park_if_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park();

        expectedException.expect(ParkingLotFullException.class);
        parkingLot.park();
    }

    @Test
    public void should_park_10_car_to_parking_lot_with_10_slot() {
        int numOfCars = 10;
        ParkingLot parkingLot = new ParkingLot(numOfCars);
        for (int i = 0; i < numOfCars; i++) {
            assertNotNull(parkingLot.park());
        }
    }

    @Test
    public void should_pick_car_from_parking_lot_only_have_that_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = parkingLot.park();
        assertEquals(ticket, parkingLot.pick(ticket));
    }

    @Test
    public void should_pick_car_from_parking_lot_have_many_cars() {
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.park();
        parkingLot.park();
        Ticket myTicket = parkingLot.park();
        parkingLot.park();

        assertEquals(myTicket, parkingLot.pick(myTicket));
    }

    @Test
    public void should_say_no_to_pick_car_by_invalid_ticket() {
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLot.park();

        Ticket fakeTicket = Ticket.create();
        expectedException.expect(InvalidTicketException.class);
        parkingLot.pick(fakeTicket);
    }

    @Test
    public void should_say_no_to_pick_car_by_ticket_already_used() {
        ParkingLot parkingLot = new ParkingLot(10);
        Ticket ticket = parkingLot.park();
        parkingLot.pick(ticket);
        expectedException.expect(InvalidTicketException.class);
        parkingLot.pick(ticket);
    }
}
