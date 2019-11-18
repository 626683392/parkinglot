package zxh.demo.parking.lot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * GraduateParkingBoyTest:
 * @author zhangxuhai
 * @date 2019/11/18
*/
public class GraduateParkingBoyTest {
    private List<ParkingLot> parkingLots;

    @Before
    public void init() {
        parkingLots = Arrays.asList(
                new ParkingLot(2),
                new ParkingLot(2),
                new ParkingLot(2)
        );
    }

    @Test
    public void should_park_car_by_parking_boy() {
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Ticket ticket = graduateParkingBoy.park();
        assertNotNull(ticket);
    }

    @Test
    public void should_park_some_cars_by_parking_boy() {
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        for (int i = 0; i < 5; i++) {
            assertNotNull(graduateParkingBoy.park());
        }
    }

    @Test
    public void should_park_some_cars_when_one_parking_lot_is_full() {
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        graduateParkingBoy.park();
        graduateParkingBoy.park();

        assertNotNull(graduateParkingBoy.park());
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_say_no_when_all_parking_lot_full() {
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        for (int i = 0; i < 6; i++) {
            graduateParkingBoy.park();
        }

        expectedException.expect(ParkingLotFullException.class);
        graduateParkingBoy.park();
    }
    
    @Test
    public void should_pick_car_from_some_parking_lot() {
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        for (int i = 0; i < 3; i++) {
            graduateParkingBoy.park();
        }

        Ticket ticket = graduateParkingBoy.park();
        assertEquals(ticket, graduateParkingBoy.pick(ticket));
    }

    @Test
    public void should_say_no_to_invalid_ticket() {
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        for (int i = 0; i < 3; i++) {
            graduateParkingBoy.park();
        }

        Ticket ticket = Ticket.create();
        expectedException.expect(InvalidTicketException.class);
        graduateParkingBoy.pick(ticket);
    }

    @Test
    public void should_say_no_to_used_ticket() {
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        for (int i = 0; i < 3; i++) {
            graduateParkingBoy.park();
        }

        Ticket ticket = graduateParkingBoy.park();
        assertEquals(ticket, graduateParkingBoy.pick(ticket));

        expectedException.expect(InvalidTicketException.class);
        graduateParkingBoy.pick(ticket);
    }
}
