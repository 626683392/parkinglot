package zxh.demo.parking.lot;

import java.util.List;

/**
 * ParkingBoy:
 * @author zhangxuhai
 * @date 2019/11/20
*/
public abstract class ParkingBoy implements ParkingAble {

    protected List<ParkingLot> parkingLots;

    ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public Ticket pick(Ticket ticket) {
        return pickByParkingAble(parkingLots, ticket);
    }

    @Override
    public boolean isFull() {
        return parkingLots.stream().allMatch(ParkingLot::isFull);
    }
}
