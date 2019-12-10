package zxh.demo.parkinglot;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * ParkingManagerTest:
 * @author zhangxuhai
 * @date 2019/12/10
*/
public class ParkingManagerTest {
    private List<ParkingLot> parkingLots;
    private List<ParkingBoy> parkingBoys;

    @Before
    public void init() {
        parkingLots = new ArrayList<>();
        parkingBoys = new ArrayList<>();
    }

    @Test
    public void should_park_to_parking_lot_when_no_parking_boy() {
        parkingLots.add(new ParkingLot(1));
        ParkingManager parkingManager = new ParkingManager(parkingLots);
        Ticket ticket = parkingManager.park(new Car());

        assertNotNull(ticket);
    }

    @Test(expected = ParkingLotFullException.class)
    public void should_say_no_to_parking_lot_full_when_no_parking_boy() {
        ParkingLot p1 = new ParkingLot(1);
        parkingLots.add(p1);
        p1.park(new Car());
        ParkingLot p2 = new ParkingLot(1);
        parkingLots.add(p2);
        p2.park(new Car());

        ParkingManager parkingManager = new ParkingManager(parkingLots);
        parkingManager.park(new Car());
    }

    @Test
    public void should_park_by_parking_boy_when_have_one() {
        ParkingLot p1 = new ParkingLot(1);
        parkingLots.add(p1);
        ParkingLot p2 = new ParkingLot(1);
        parkingLots.add(p2);
        parkingBoys.add(new GraduateParkingBoy(parkingLots));

        ParkingManager parkingManager = new ParkingManager(parkingBoys);
        Ticket ticket = parkingManager.park(new Car());

        assertNotNull(ticket);
    }

    @Test
    public void should_park_by_parking_boy_when_have_some() {
        List<ParkingLot> parkingLots1 = new ArrayList<>();
        ParkingLot p1 = new ParkingLot(1);
        parkingLots1.add(p1);
        p1.park(new Car());
        ParkingLot p2 = new ParkingLot(1);
        parkingLots1.add(p2);
        p2.park(new Car());
        parkingBoys.add(new GraduateParkingBoy(parkingLots1));

        List<ParkingLot> parkingLots2 = new ArrayList<>();
        ParkingLot p3 = new ParkingLot(1);
        parkingLots2.add(p3);
        ParkingLot p4 = new ParkingLot(1);
        parkingLots2.add(p4);
        parkingBoys.add(new SuperParkingBoy(parkingLots2));

        ParkingManager parkingManager = new ParkingManager(parkingBoys);
        Ticket ticket = parkingManager.park(new Car());

        assertNotNull(ticket);
    }
    
    @Test(expected = ParkingLotFullException.class)
    public void should_say_no_to_parking_boy_all_full() {
        List<ParkingLot> parkingLots1 = new ArrayList<>();
        ParkingLot p1 = new ParkingLot(1);
        parkingLots1.add(p1);
        p1.park(new Car());
        ParkingLot p2 = new ParkingLot(1);
        parkingLots1.add(p2);
        p2.park(new Car());
        parkingBoys.add(new GraduateParkingBoy(parkingLots1));

        List<ParkingLot> parkingLots2 = new ArrayList<>();
        ParkingLot p3 = new ParkingLot(1);
        parkingLots2.add(p3);
        p3.park(new Car());
        ParkingLot p4 = new ParkingLot(1);
        parkingLots2.add(p4);
        p4.park(new Car());
        parkingBoys.add(new SuperParkingBoy(parkingLots2));

        ParkingManager parkingManager = new ParkingManager(parkingBoys);
        parkingManager.park(new Car());
    }

    @Test
    public void should_pick_my_car_from_parking_lot_when_no_parking_boy() {
        parkingLots.add(new ParkingLot(1));
        ParkingManager parkingManager = new ParkingManager(parkingLots);
        Car myCar = new Car();
        Ticket ticket = parkingManager.park(myCar);

        assertThat(parkingManager.pick(ticket), is(myCar));
    }
    
    @Test(expected = InvalidTicketException.class)
    public void should_say_no_to_pick_car_with_invalid_ticket_when_have_no_parking_boy() {
        parkingLots.add(new ParkingLot(1));
        ParkingManager parkingManager = new ParkingManager(parkingLots);
        Car myCar = new Car();
        parkingManager.park(myCar);

        parkingManager.pick(new Ticket());
    }
    
    @Test
    public void should_pick_my_car_from_parking_boy_when_have_some() {
        List<ParkingLot> parkingLots1 = new ArrayList<>();
        ParkingLot p1 = new ParkingLot(1);
        parkingLots1.add(p1);
        p1.park(new Car());
        ParkingLot p2 = new ParkingLot(1);
        parkingLots1.add(p2);
        p2.park(new Car());
        parkingBoys.add(new GraduateParkingBoy(parkingLots1));

        List<ParkingLot> parkingLots2 = new ArrayList<>();
        ParkingLot p3 = new ParkingLot(1);
        parkingLots2.add(p3);
        ParkingLot p4 = new ParkingLot(1);
        parkingLots2.add(p4);
        parkingBoys.add(new SuperParkingBoy(parkingLots2));

        ParkingManager parkingManager = new ParkingManager(parkingBoys);
        Car myCar = new Car();
        Ticket ticket = parkingManager.park(myCar);

        assertThat(parkingManager.pick(ticket), is(myCar));        
    }

    @Test(expected = InvalidTicketException.class)
    public void should_say_no_to_pick_car_with_invalid_ticket_when_have_some_parking_boy() {
        List<ParkingLot> parkingLots1 = new ArrayList<>();
        ParkingLot p1 = new ParkingLot(1);
        parkingLots1.add(p1);
        p1.park(new Car());
        ParkingLot p2 = new ParkingLot(1);
        parkingLots1.add(p2);
        p2.park(new Car());
        parkingBoys.add(new GraduateParkingBoy(parkingLots1));

        List<ParkingLot> parkingLots2 = new ArrayList<>();
        ParkingLot p3 = new ParkingLot(1);
        parkingLots2.add(p3);
        ParkingLot p4 = new ParkingLot(1);
        parkingLots2.add(p4);
        parkingBoys.add(new SuperParkingBoy(parkingLots2));

        ParkingManager parkingManager = new ParkingManager(parkingBoys);
        Car myCar = new Car();
        parkingManager.park(myCar);

        parkingManager.pick(new Ticket());
    }
}
