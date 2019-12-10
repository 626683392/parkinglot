package zxh.demo.parkinglot;

import java.util.List;

/**
 * ParkingManager:
 * @author zhangxuhai
 * @date 2019/12/10
*/
public class ParkingManager {
    private List<? extends ParkingAble> parkingAbles;

    public ParkingManager(List<? extends ParkingAble> parkingAbles) {
        this.parkingAbles = parkingAbles;
    }

    public Ticket park(Car car) {
        return parkingAbles
                .stream()
                .filter(parkingAble -> !parkingAble.isFull())
                .findFirst()
                .orElseThrow(ParkingLotFullException::new).park(car);
    }

    public Car pick(Ticket ticket) {
        return PickUtil.pickFrom(parkingAbles, ticket);
    }
}
