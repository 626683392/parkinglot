package zxh.demo.parkinglot;

import java.util.Comparator;
import java.util.List;

/**
 * SmartParkingBoy:
 *
 * @author zhangxuhai
 * @date 2019/11/30
 */
public class SmartParkingBoy {
    private List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return parkingLots
                .stream()
                .sorted(Comparator.comparingInt(ParkingLot::getEmptySlots).reversed())
                .filter(p -> !p.isFull())
                .findFirst()
                .orElseThrow(ParkingLotFullException::new)
                .park(car);
    }

    public Car pick(Ticket t) {
        return parkingLots
                .stream()
                .filter(p -> p.contains(t))
                .findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pick(t);
    }
}
