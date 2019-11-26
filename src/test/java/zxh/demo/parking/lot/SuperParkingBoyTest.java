package zxh.demo.parking.lot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * SuperParkingLotTest:
 * @author zhangxuhai
 * @date 2019/11/25
*/
public class SuperParkingBoyTest {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    ParkingLot p1;
    ParkingLot p2;

    @Before
    public void init() {
        p1 = new ParkingLot(5);
        p2 = new ParkingLot(7);
        parkingLots.add(p1);
        parkingLots.add(p2);
    }

    @Test
    public void should_park_to_second_parking_lot() {
        p1.park();
        p1.park();
        p2.park();
        p2.park();
        p2.park();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        superParkingBoy.park();
        assertEquals(2, p1.emptySlotLeft());
        assertEquals(4, p2.emptySlotLeft());
    }

    @Test
    public void should_park_some_car_to_second_parking_lot() {
        p1.park();
        p1.park();
        p2.park();
        p2.park();
        p2.park();

        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        superParkingBoy.park();
        superParkingBoy.park();
        superParkingBoy.park();
        assertEquals(2, p1.emptySlotLeft());
        assertEquals(2, p2.emptySlotLeft());
    }

    @Test
    public void should_park_some_car_by_order() {
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        superParkingBoy.park();
        superParkingBoy.park();
        assertEquals(4, p1.emptySlotLeft());
        assertEquals(6, p2.emptySlotLeft());
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_say_no_if_all_full() {
        for (int i = 0; i < 5; i++) {
            p1.park();
            p2.park();
        }
        p2.park();
        p2.park();

        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        expectedException.expect(ParkingLotFullException.class);
        superParkingBoy.park();
    }

    @Test
    public void should_pick_car_from_empty_parking_lot() {
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Ticket ticket = superParkingBoy.park();
        assertEquals(ticket, superParkingBoy.pick(ticket));
    }

    @Test
    public void should_say_no_to_invalid_ticket() {
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Ticket ticket = Ticket.create();
        expectedException.expect(InvalidTicketException.class);
        superParkingBoy.pick(ticket);
    }

    @Test
    public void should_say_no_to_used_ticket() {
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Ticket ticket = superParkingBoy.park();
        assertEquals(ticket, superParkingBoy.pick(ticket));
        expectedException.expect(InvalidTicketException.class);
        superParkingBoy.pick(ticket);
    }
}
