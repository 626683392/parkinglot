package zxh.demo.parking.lot;

import java.util.List;

/**
 * GraduateParkingBoy:
 * @author zhangxuhai
 * @date 2019/11/18
*/
public class GraduateParkingBoy extends ParkingBoy {
    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park() {
        return parkingLots
                .stream()
                .filter(p -> !p.isFull())
                .findFirst()
                .orElseThrow(ParkingLotFullException::new)
                .park();
    }
}
