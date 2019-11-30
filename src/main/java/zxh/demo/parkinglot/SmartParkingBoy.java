package zxh.demo.parkinglot;

import java.util.Comparator;
import java.util.List;

/**
 * SmartParkingBoy:
 *
 * @author zhangxuhai
 * @date 2019/11/30
 */
public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return parkingLots
                .stream()
                .sorted(Comparator.comparingInt(ParkingLot::getEmptySlots).reversed())
                .filter(p -> !p.isFull())
                .findFirst()
                .orElseThrow(ParkingLotFullException::new)
                .park(car);
    }
}
