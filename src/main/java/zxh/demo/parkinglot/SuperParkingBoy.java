package zxh.demo.parkinglot;

import java.util.Comparator;
import java.util.List;

/**
 * SuperParkingBoy:
 * @author zhangxuhai
 * @date 2019/11/30
*/
public class SuperParkingBoy extends ParkingBoy {
    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return parkingLots
                .stream()
                .max(Comparator.comparingDouble(ParkingLot::getEmptyRate))
                .orElseThrow(ParkingLotFullException::new)
                .park(car);
    }
}
