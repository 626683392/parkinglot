package zxh.demo.parkinglot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * GraduateParkingBoyTest:
 * @author zhangxuhai
 * @date 2019/11/30
*/
public class GraduateParkingBoyTest {
    private List<ParkingLot> parkingLots = new ArrayList<>();

    @Test
    public void should_return_ticket_when_park_car_by_parking_boy_who_hold_one_parking_lot() {
        parkingLots.add(new ParkingLot(1));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Ticket t = graduateParkingBoy.park(new Car());
        assertNotNull(t);
    }

    @Test
    public void should_park_two_car_to_one_parking_lot_which_have_many_slot_by_parking_boy() {
        ParkingLot p1 = new ParkingLot(2);
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(2);
        parkingLots.add(p2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        Ticket t1 = graduateParkingBoy.park(new Car());
        Ticket t2 = graduateParkingBoy.park(new Car());

        p1.pick(t1);
        p1.pick(t2);
    }

    @Test
    public void should_park_two_car_to_two_parking_lot_respectively_by_parking_boy() {
        ParkingLot p1 = new ParkingLot(2);
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(2);
        p2.park(new Car());
        parkingLots.add(p2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        Ticket t1 = graduateParkingBoy.park(new Car());
        Ticket t2 = graduateParkingBoy.park(new Car());

        p1.pick(t1);
        p2.pick(t2);
    }

    @Test
    public void should_park_two_car_to_second_parking_lot_since_first_is_full_by_parking_boy() {
        ParkingLot p1 = new ParkingLot(2);
        p1.park(new Car());
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(3);
        p2.park(new Car());
        parkingLots.add(p2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        Ticket t1 = graduateParkingBoy.park(new Car());
        Ticket t2 = graduateParkingBoy.park(new Car());

        p2.pick(t1);
        p2.pick(t2);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_say_no_to_park_car_to_two_full_parking_lot_by_parking_boy() {
        ParkingLot p1 = new ParkingLot(1);
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(1);
        p2.park(new Car());
        parkingLots.add(p2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        expectedException.expect(ParkingLotFullException.class);
        graduateParkingBoy.park(new Car());
    }

    @Test
    public void should_pick_my_car_by_ticket() {
        ParkingLot p1 = new ParkingLot(1);
        parkingLots.add(p1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        Car myCar = new Car();
        Ticket t = graduateParkingBoy.park(myCar);
        Car car = graduateParkingBoy.pick(t);

        assertEquals(myCar, car);
    }

    @Test
    public void should_pick_my_car_from_many_parking_lot() {
        ParkingLot p1 = new ParkingLot(1);
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(1);
        parkingLots.add(p2);
        ParkingLot p3 = new ParkingLot(1);
        p3.park(new Car());
        parkingLots.add(p3);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car myCar = new Car();
        Ticket t = graduateParkingBoy.park(myCar);

        assertEquals(myCar, graduateParkingBoy.pick(t));
    }

    @Test
    public void should_say_no_to_invalid_ticket_from_many_parking_lot() {
        ParkingLot p1 = new ParkingLot(1);
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(1);
        parkingLots.add(p2);
        ParkingLot p3 = new ParkingLot(1);
        p3.park(new Car());
        parkingLots.add(p3);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car myCar = new Car();
        graduateParkingBoy.park(myCar);

        expectedException.expect(InvalidTicketException.class);
        graduateParkingBoy.pick(new Ticket());
    }

    @Test
    public void should_say_no_to_used_ticket_from_many_parking_lot() {
        ParkingLot p1 = new ParkingLot(1);
        p1.park(new Car());
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(1);
        parkingLots.add(p2);
        ParkingLot p3 = new ParkingLot(1);
        p3.park(new Car());
        parkingLots.add(p3);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car myCar = new Car();
        Ticket t= graduateParkingBoy.park(myCar);
        graduateParkingBoy.pick(t);

        expectedException.expect(InvalidTicketException.class);
        graduateParkingBoy.pick(t);
    }
}
