package zxh.demo.parking.lot;

import java.util.Comparator;
import java.util.List;

/**
 * SmartParkingBoy:
 * @author zhangxuhai
 * @date 2019/11/19
*/
public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park() {
        return parkingLots
                .stream()
                .max(Comparator.comparingInt(ParkingLot::emptySlotLeft))
                .filter(parkingLot -> !parkingLot.isFull())
                .orElseThrow(ParkingLotFullException::new)
                .park();
    }

}
