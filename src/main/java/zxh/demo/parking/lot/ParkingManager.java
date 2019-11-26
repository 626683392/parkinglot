package zxh.demo.parking.lot;

import java.util.List;

/**
 * ParkingManaer:
 * @author zhangxuhai
 * @date 2019/11/26
*/
public class ParkingManager implements ParkingAble {
    private List<ParkingAble> parkingAbles;

    public ParkingManager(List<ParkingAble> parkingAbles) {
        this.parkingAbles = parkingAbles;
    }

    @Override
    public Ticket park() {
        return parkingAbles
                .stream()
                .filter(p -> !p.isFull())
                .findFirst()
                .orElseThrow(ParkingLotFullException::new)
                .park();
    }

    @Override
    public Ticket pick(Ticket ticket) {
        return pickByParkingAble(parkingAbles, ticket);
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
