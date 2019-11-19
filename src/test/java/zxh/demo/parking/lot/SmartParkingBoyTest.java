package zxh.demo.parking.lot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * SmartParkingBoyTest:
 * @author zhangxuhai
 * @date 2019/11/19
*/
public class SmartParkingBoyTest {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    ParkingLot p1;
    ParkingLot p2;

    @Before
    public void init() {
        p1 = new ParkingLot(5);
        p2 = new ParkingLot(5);
        parkingLots.add(p1);
        parkingLots.add(p2);
    }
    
    @Test
    public void should_park_to_second_parking_lot() {
        p1.park();
        p1.park();
        p1.park();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park();
        assertEquals(2, p1.emptySlotLeft());
        assertEquals(4, p2.emptySlotLeft());
    }

    @Test
    public void should_park_some_car_to_second_parking_lot() {
        p1.park();
        p1.park();
        p1.park();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park();
        smartParkingBoy.park();
        smartParkingBoy.park();
        assertEquals(2, p1.emptySlotLeft());
        assertEquals(2, p2.emptySlotLeft());
    }

    @Test
    public void should_park_some_car_by_order() {
        p1.park();
        p2.park();
        p1.park();
        p2.park();
        p1.park();
        p2.park();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park();
        smartParkingBoy.park();
        assertEquals(1, p1.emptySlotLeft());
        assertEquals(1, p2.emptySlotLeft());
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_say_no_if_all_full() {
        for (int i = 0; i < 5; i++) {
            p1.park();
            p2.park();
        }

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        expectedException.expect(ParkingLotFullException.class);
        smartParkingBoy.park();
    }

    @Test
    public void should_pick_car_from_empty_parking_lot() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = smartParkingBoy.park();
        assertEquals(ticket, smartParkingBoy.pick(ticket));
    }

    @Test
    public void should_say_no_to_invalid_ticket() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = Ticket.create();
        expectedException.expect(InvalidTicketException.class);
        smartParkingBoy.pick(ticket);
    }

    @Test
    public void should_say_no_to_used_ticket() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = smartParkingBoy.park();
        assertEquals(ticket, smartParkingBoy.pick(ticket));
        expectedException.expect(InvalidTicketException.class);
        smartParkingBoy.pick(ticket);
    }
}
