package zxh.demo.parkinglot;

import java.util.List;

/**
 * GraduateParkingBoy:
 * @author zhangxuhai
 * @date 2019/11/30
*/
public class GraduateParkingBoy extends ParkingBoy {
    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return parkingLots
                .stream()
                .filter(p -> !p.isFull())
                .findFirst()
                .orElseThrow(ParkingLotFullException::new)
                .park(car);
    }
}
