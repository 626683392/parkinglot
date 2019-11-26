package zxh.demo.parking.lot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * ParkingManagerTest:
 * @author zhangxuhai
 * @date 2019/11/26
*/
public class ParkingManagerTest {
    private List<ParkingAble> parkingAbles = new ArrayList<>();
    
    @Test
    public void should_park_car_to_parking_lot() {
        parkingAbles.add(new ParkingLot(5));
        ParkingManager parkingManager = new ParkingManager(parkingAbles);
        assertNotNull(parkingManager.park());
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_say_no_if_parking_lot_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park();
        parkingAbles.add(parkingLot);
        ParkingManager parkingManager = new ParkingManager(parkingAbles);
        expectedException.expect(ParkingLotFullException.class);
        parkingManager.park();
    }

    @Test
    public void should_park_car_by_parking_boy() {
        ParkingLot parkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(parkingLot));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(parkingLot));
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(Collections.singletonList(parkingLot));
        parkingAbles.add(graduateParkingBoy);
        parkingAbles.add(smartParkingBoy);
        parkingAbles.add(superParkingBoy);
        ParkingManager parkingManager = new ParkingManager(parkingAbles);
        assertNotNull(parkingManager.park());
    }

    @Test
    public void should_park_car_by_parking_boy_if_some_full() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(parkingLot1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(parkingLot1));
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(Collections.singletonList(parkingLot2));
        parkingLot1.park();
        parkingAbles.add(graduateParkingBoy);
        parkingAbles.add(smartParkingBoy);
        parkingAbles.add(superParkingBoy);
        ParkingManager parkingManager = new ParkingManager(parkingAbles);
        assertNotNull(parkingManager.park());
    }

    @Test
    public void should_say_no_if_parking_boy_say_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(parkingLot));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(parkingLot));
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(Collections.singletonList(parkingLot));
        parkingLot.park();
        parkingAbles.add(graduateParkingBoy);
        parkingAbles.add(smartParkingBoy);
        parkingAbles.add(superParkingBoy);
        ParkingManager parkingManager = new ParkingManager(parkingAbles);
        expectedException.expect(ParkingLotFullException.class);
        parkingManager.park();
    }

    @Test
    public void should_pick_car_from_parking_lot() {
        parkingAbles.add(new ParkingLot(5));
        ParkingManager parkingManager = new ParkingManager(parkingAbles);
        Ticket ticket = parkingManager.park();
        assertEquals(ticket, parkingManager.pick(ticket));
    }

    @Test
    public void should_say_no_to_invalid_ticket_from_parking_lot() {
        parkingAbles.add(new ParkingLot(5));
        ParkingManager parkingManager = new ParkingManager(parkingAbles);
        expectedException.expect(InvalidTicketException.class);
        parkingManager.pick(Ticket.create());
    }

    @Test
    public void should_say_no_to_used_ticket_from_parking_lot() {
        parkingAbles.add(new ParkingLot(5));
        ParkingManager parkingManager = new ParkingManager(parkingAbles);
        Ticket ticket = parkingManager.park();
        parkingManager.pick(ticket);
        expectedException.expect(InvalidTicketException.class);
        parkingManager.pick(ticket);
    }

    @Test
    public void should_pick_car_from_parking_boy() {
        parkingAbles.add(new GraduateParkingBoy(Collections.singletonList(new ParkingLot(5))));
        ParkingManager parkingManager = new ParkingManager(parkingAbles);
        Ticket ticket = parkingManager.park();
        assertEquals(ticket, parkingManager.pick(ticket));
    }

    @Test
    public void should_say_no_to_invalid_ticket_from_parking_boy() {
        parkingAbles.add(new GraduateParkingBoy(Collections.singletonList(new ParkingLot(5))));
        ParkingManager parkingManager = new ParkingManager(parkingAbles);
        expectedException.expect(InvalidTicketException.class);
        parkingManager.pick(Ticket.create());
    }

    @Test
    public void should_say_no_to_used_ticket_from_parking_boy() {
        parkingAbles.add(new GraduateParkingBoy(Collections.singletonList(new ParkingLot(5))));
        ParkingManager parkingManager = new ParkingManager(parkingAbles);
        Ticket ticket = parkingManager.park();
        parkingManager.pick(ticket);
        expectedException.expect(InvalidTicketException.class);
        parkingManager.pick(ticket);
    }
}
