package zxh.demo.parkinglot;

import java.util.List;

/**
 * ParkingBoy:
 * @author zhangxuhai
 * @date 2019/11/30
*/
public abstract class ParkingBoy implements ParkingAble {
    protected List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public Car pick(Ticket t) {
        return PickUtil.pickFrom(parkingLots, t);
    }

    @Override
    public boolean isFull() {
        return parkingLots.stream().allMatch(ParkingLot::isFull);
    }

    @Override
    public boolean contains(Ticket ticket) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.contains(ticket));
    }
}
