package zxh.demo.parking.lot;

import java.util.Comparator;
import java.util.List;

/**
 * SuperParkingLot:
 * @author zhangxuhai
 * @date 2019/11/25
*/
public class SuperParkingLot extends ParkingBoy {
    SuperParkingLot(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park() {
        return parkingLots
                .stream()
                .max(Comparator.comparingDouble(p -> p.emptySlotLeft() / (double) p.getSize()))
                .orElseThrow(ParkingLotFullException::new)
                .park();
    }
}
